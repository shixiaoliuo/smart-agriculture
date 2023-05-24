package com.lxl.agro.service.sys.impl;

import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lxl.agro.common.Constant;
import com.lxl.agro.common.CustomException;
import com.lxl.agro.common.ResultInfo;
import com.lxl.agro.common.SysUserHolder;
import com.lxl.agro.mapper.SysUserMapper;
import com.lxl.agro.pojo.SysModule;
import com.lxl.agro.pojo.SysUser;
import com.lxl.agro.service.sys.SysModuleService;
import com.lxl.agro.service.sys.SysUserService;
import com.lxl.agro.util.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * Project : smart-agriculture-parent
 * Package : com.lxl.agro.service.sys.impl
 * Description : SysUserServiceImpl
 * Author : LiuXinLei
 * createDate : 2023/5/20 14:19
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    private final SysUserMapper sysUserMapper;

    private final RedisTemplate redisTemplate;

    private final SysModuleService sysModuleService;

    public SysUserServiceImpl(SysUserMapper sysUserMapper, RedisTemplate redisTemplate, SysModuleService sysModuleService) {
        this.sysUserMapper = sysUserMapper;
        this.redisTemplate = redisTemplate;
        this.sysModuleService = sysModuleService;
    }

    @Override
    public ResultInfo login(String username, String password) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return ResultInfo.error("登陆失败！");
        }

        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getUsername, username);
        List<SysUser> sysUsers = sysUserMapper.selectList(queryWrapper);
        if (sysUsers == null || sysUsers.size() == 0) {
            return ResultInfo.error("用户名或密码错误，请重新输入");
        }

        if (sysUsers.size() > 1) {
            throw new CustomException("系统问题");
        }

        SysUser sysUser = sysUsers.get(0);

        if (!StringUtils.equals(sysUser.getPassword(), SecureUtil.md5(password))) {
            return ResultInfo.error("用户名或密码错误，请重新输入");
        }
        String token = JwtUtil.createJWT(sysUser.getId().toString(), JSON.toJSONString(sysUser), 3600000L);

        List<SysModule> modules = sysModuleService.getByUserId(sysUser.getId());
//        String menu = JSONObject.toJSONString(modules);
//        redisTemplate.opsForValue().set(sysUser.getId().toString(), menu);


        return ResultInfo.success(token);
    }

    @Override
    public ResultInfo logout(Long id) {
        redisTemplate.delete(String.valueOf(id));
        SysUserHolder.remove();
        return ResultInfo.success("退出成功");
    }
}
