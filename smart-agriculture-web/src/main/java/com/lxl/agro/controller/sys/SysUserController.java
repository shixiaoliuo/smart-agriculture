package com.lxl.agro.controller.sys;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lxl.agro.common.ResultInfo;
import com.lxl.agro.common.SysUserHolder;
import com.lxl.agro.pojo.Company;
import com.lxl.agro.pojo.SysRole;
import com.lxl.agro.pojo.SysUser;
import com.lxl.agro.service.sys.SysUserService;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Project : smart-agriculture-parent
 * Package : com.lxl.agro.controller
 * Description : SysUserController
 * Author : LiuXinLei
 * createDate : 2023/5/20 15:15
 */
@RestController
@RequestMapping("/sys/user")
@CrossOrigin
public class SysUserController {

    private final SysUserService sysUserService;

    public SysUserController(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    @PostMapping("/login")
    public ResultInfo login(@RequestBody Map<String, String> map) {
        String username = map.get("username");
        String password = map.get("password");
        return sysUserService.login(username, password);
    }

    @GetMapping("/page")
    public ResultInfo showPage(int page, int pageSize, String name) {
        Page pageInfo = new Page(page, pageSize);
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(name != null, SysUser::getUsername, name);
        sysUserService.page(pageInfo, wrapper);
        return ResultInfo.success(pageInfo);
    }

    @PostMapping
    public ResultInfo save(@RequestBody SysUser sysUser) {
        if (sysUserService.save(sysUser)) {
            return ResultInfo.success("保存成功！");
        }
        return ResultInfo.error("保存失败！");
    }

    @PutMapping
    public ResultInfo modify(@RequestBody SysUser sysUser) {
        Long id = sysUser.getId();
        LambdaUpdateWrapper<SysUser> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(id != null, SysUser::getId, id);
        if (sysUserService.update(sysUser, wrapper)) {
            return ResultInfo.success("更新成功！");
        }
        return ResultInfo.error("更新失败！");
    }


    @DeleteMapping
    public ResultInfo remove(Long id) {
        LambdaUpdateWrapper<SysUser> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(id != null, SysUser::getId, id);
        if (sysUserService.remove(wrapper)) {
            return ResultInfo.success("删除成功！");
        }
        return ResultInfo.error("删除失败！");
    }

    @PostMapping("/checkUsername")
    public ResultInfo checkUsername(@RequestBody Map<String, String> map) {
        String username = map.get("username");
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(username != null, SysUser::getUsername, username);
        SysUser one = sysUserService.getOne(wrapper);
        if (one == null) {
            return ResultInfo.success("用户名可用！");
        }
        return ResultInfo.error("用户名不可用！");
    }

    @PostMapping("/logout")
    public ResultInfo logout() {
        SysUser sysUser = SysUserHolder.get();
        if (sysUser != null) {
            Long id = sysUser.getId();
            return sysUserService.logout(id);
        }
        return ResultInfo.error("登出失败！");
    }

}
