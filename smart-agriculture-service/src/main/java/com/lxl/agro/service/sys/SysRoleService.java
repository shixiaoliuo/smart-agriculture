package com.lxl.agro.service.sys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lxl.agro.pojo.SysRole;

import java.util.List;

/**
 * Project : smart-agriculture-parent
 * Package : com.lxl.agro.service.sys
 * Description : SysRoleService
 * Author : LiuXinLei
 * createDate : 2023/5/21 20:10
 */
public interface SysRoleService extends IService<SysRole> {
    List<Long> getByUserId(Long userId);

    boolean modifyUserRole(Long userId, List<Long> roleIds);
}
