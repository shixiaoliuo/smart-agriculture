package com.lxl.agro.service.sys.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lxl.agro.mapper.SysRoleMapper;
import com.lxl.agro.pojo.SysRole;
import com.lxl.agro.service.sys.SysRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Project : smart-agriculture-parent
 * Package : com.lxl.agro.service.sys.impl
 * Description : SysRoleServiceImpl
 * Author : LiuXinLei
 * createDate : 2023/5/21 20:11
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    private final SysRoleMapper sysRoleMapper;

    public SysRoleServiceImpl(SysRoleMapper sysRoleMapper) {
        this.sysRoleMapper = sysRoleMapper;
    }

    @Override
    public List<Long> getByUserId(Long userId) {
        return sysRoleMapper.selectByUserId(userId);
    }

    @Override
    @Transactional
    public boolean modifyUserRole(Long userId, List<Long> roleIds) {
        sysRoleMapper.deleteByUserId(userId);
        int count = sysRoleMapper.insertUserRole(userId, roleIds);
        return count > 0;
    }
}
