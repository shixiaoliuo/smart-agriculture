package com.lxl.agro.service.sys.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lxl.agro.mapper.SysModuleMapper;
import com.lxl.agro.mapper.SysRoleMapper;
import com.lxl.agro.pojo.SysModule;
import com.lxl.agro.pojo.SysRole;
import com.lxl.agro.service.sys.SysModuleService;
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

    public SysModuleServiceImpl(SysModuleMapper sysModuleMapper, SysRoleMapper sysRoleMapper) {
        this.sysModuleMapper = sysModuleMapper;
        this.sysRoleMapper = sysRoleMapper;
    }

    @Override
    public List<SysModule> getByUserId(Long userId) {
        List<SysModule> modules = sysModuleMapper.selectByUserId(userId);
        for (SysModule module : modules) {
            LambdaQueryWrapper<SysModule> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(SysModule::getParentId, module.getId());
            List<SysModule> children = sysModuleMapper.selectList(wrapper);
            module.setChildren(children);
        }
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
    @Transactional
    public boolean modifyRoleModule(Long roleId, List<Long> moduleIds) {
        List<SysModule> list = sysRoleMapper.selectByRoleId(roleId);
        sysRoleMapper.deleteByRoleId(roleId);
        int count = sysRoleMapper.insertRoleModule(roleId, moduleIds);
        return count > 0;
    }
}
