package com.lxl.agro.controller.sys;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lxl.agro.common.ResultInfo;
import com.lxl.agro.pojo.Company;
import com.lxl.agro.pojo.SysRole;
import com.lxl.agro.service.sys.SysRoleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Project : smart-agriculture-parent
 * Package : com.lxl.agro.controller.sys
 * Description : SysRoleController
 * Author : LiuXinLei
 * createDate : 2023/5/21 20:10
 */
@RestController
@RequestMapping("/sys/role")
public class SysRoleController {

    private final SysRoleService sysRoleService;


    public SysRoleController(SysRoleService sysRoleService) {
        this.sysRoleService = sysRoleService;
    }


    @GetMapping("/list")
    public ResultInfo showList() {
        return ResultInfo.success(sysRoleService.list());
    }

    @GetMapping("/userRole")
    public ResultInfo showUserRole(Long userId) {
        List<Long> list = sysRoleService.getByUserId(userId);
        return ResultInfo.success(list);
    }

    @PostMapping("/userRole")
    public ResultInfo modifyUserRole(@RequestBody Map<String, Object> map) {
        String userId = (String) map.get("userId");
        List<Long> roleIds = (List<Long>) map.get("roleIds");
        if (sysRoleService.modifyUserRole(Long.parseLong(userId), roleIds)) {
            return ResultInfo.success("更新成功！");
        }
        return ResultInfo.error("更新失败！");
    }

    @GetMapping("/page")
    public ResultInfo showPage(int page, int pageSize, String name) {
        Page<SysRole> pageInfo = new Page<>(page, pageSize);
        LambdaQueryWrapper<SysRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(name != null, SysRole::getName, name);
        sysRoleService.page(pageInfo, wrapper);
        return ResultInfo.success(pageInfo);
    }

    @PostMapping
    public ResultInfo save(@RequestBody SysRole sysRole) {
        if (sysRoleService.save(sysRole)) {
            return ResultInfo.success("添加成功！");
        }
        return ResultInfo.error("添加失败！");
    }

    @PutMapping
    public ResultInfo modify(@RequestBody SysRole sysRole) {
        LambdaUpdateWrapper<SysRole> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(sysRole.getId() != null, SysRole::getId, sysRole.getId());
        if (sysRoleService.update(sysRole, wrapper)) {
            return ResultInfo.success("添加成功！");
        }
        return ResultInfo.error("添加失败！");
    }

    @DeleteMapping
    public ResultInfo remove(Long id) {
        if (sysRoleService.removeById(id)) {
            return ResultInfo.success("删除成功！");
        }
        return ResultInfo.error("删除失败！");
    }
}
