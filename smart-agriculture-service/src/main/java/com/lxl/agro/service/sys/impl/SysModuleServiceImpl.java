package com.lxl.agro.service.sys.impl;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lxl.agro.common.SysUserHolder;
import com.lxl.agro.mapper.SysModuleMapper;
import com.lxl.agro.mapper.SysRoleMapper;
import com.lxl.agro.pojo.SysModule;
import com.lxl.agro.pojo.SysUser;
import com.lxl.agro.service.sys.SysModuleService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Project : smart-agriculture-parent
 * Package : com.lxl.agro.service.sys.impl
 * Description : SysModelServiceImpl
 * Author : LiuXinLei
 * createDate : 2023/5/20 15:59
 */
@Service
public class SysModuleServiceImpl extends ServiceImpl<SysModuleMapper, SysModule> implements SysModuleService {

    private final SysModuleMapper sysModuleMapper;
    private final SysRoleMapper sysRoleMapper;

    private final RedisTemplate redisTemplate;

    public SysModuleServiceImpl(SysModuleMapper sysModuleMapper, SysRoleMapper sysRoleMapper, RedisTemplate redisTemplate) {
        this.sysModuleMapper = sysModuleMapper;
        this.sysRoleMapper = sysRoleMapper;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public List<SysModule> getByUserId(Long userId) {
        String cacheMenu = (String) redisTemplate.opsForValue().get(userId.toString());
        List<SysModule> list = JSONObject.parseObject(cacheMenu, new TypeReference<List<SysModule>>() {
        });
        if (list != null) {
            return list;
        }

        List<SysModule> modules = sysModuleMapper.selectByUserId(userId);
        for (SysModule module : modules) {
            LambdaQueryWrapper<SysModule> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(SysModule::getParentId, module.getId());
            List<SysModule> children = sysModuleMapper.selectList(wrapper);
            module.setChildren(children);
        }

        String menu = JSONObject.toJSONString(modules);
        redisTemplate.opsForValue().set(userId.toString(), menu);
        return modules;
    }

    @Override
    public List<Long> getByRoleId(Long roleId) {
        return sysModuleMapper.selectByRoleId(roleId);
    }

    @Override
    public List<SysModule> getList() {
        List<SysModule> list = sysModuleMapper.selectParentId();
        for (SysModule sysModule : list) {
            List<SysModule> clist = sysRoleMapper.selectByParentId(sysModule.getId());
            sysModule.setChildren(clist);
        }
        return list;
    }

    @Override
    public Page<SysModule> getPage(Integer page, Integer pageSize, String name) {
        Page<SysModule> pageInfo = new Page<>(page, pageSize);
        LambdaQueryWrapper<SysModule> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(name != null, SysModule::getName, name);
        return sysModuleMapper.selectPage(pageInfo, wrapper);
    }

    @Override
    @Transactional
    public boolean modifyRoleModule(Long roleId, List<Long> moduleIds) {

//        List<SysModule> list = sysRoleMapper.selectByRoleId(roleId);
        sysRoleMapper.deleteByRoleId(roleId);
        int count = sysRoleMapper.insertRoleModule(roleId, moduleIds);

        List<Long> list = sysRoleMapper.selectUserIdByRoleId(roleId);
        for (Long userId : list) {
            redisTemplate.delete(userId.toString());
        }
        return count > 0;
    }
}
