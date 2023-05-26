package com.lxl.agro.service.sys;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lxl.agro.pojo.SysModule;
import com.lxl.agro.pojo.SysRole;

import java.util.List;

/**
 * Project : smart-agriculture-parent
 * Package : com.lxl.agro.service.sys
 * Description : SysModuleService
 * Author : LiuXinLei
 * createDate : 2023/5/20 15:58
 */
public interface SysModuleService extends IService<SysModule> {
    List<SysModule> getByUserId(Long id);

    List<Long> getByRoleId(Long roleId);

    boolean modifyRoleModule(Long roleId, List<Long> moduleIds);

    List<SysModule> getList();

    Page<SysModule> getPage(Integer page, Integer pageSize, String name);
}
