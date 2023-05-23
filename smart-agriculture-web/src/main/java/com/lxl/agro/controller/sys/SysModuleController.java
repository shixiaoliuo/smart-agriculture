package com.lxl.agro.controller.sys;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lxl.agro.common.ResultInfo;
import com.lxl.agro.common.SysUserHolder;
import com.lxl.agro.pojo.SysModule;
import com.lxl.agro.pojo.SysRole;
import com.lxl.agro.pojo.SysUser;
import com.lxl.agro.service.sys.SysModuleService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Project : smart-agriculture-parent
 * Package : com.lxl.agro.controller.sys
 * Description : SysModuleController
 * Author : LiuXinLei
 * createDate : 2023/5/22 9:51
 */
@RestController
@RequestMapping("/sys/module")
public class SysModuleController {

    private final SysModuleService sysModuleService;

    public SysModuleController(SysModuleService sysModuleService) {
        this.sysModuleService = sysModuleService;
    }


    @GetMapping("/menu")
    public ResultInfo showMenuList() {
        SysUser sysUser = SysUserHolder.get();
        List<SysModule> list = sysModuleService.getByUserId(sysUser.getId());
        return ResultInfo.success(list);
    }

    @GetMapping("/roleModule")
    public ResultInfo showRoleModule(Long roleId) {
        List<Long> list = sysModuleService.getByRoleId(roleId);
        return ResultInfo.success(list);
    }


    @GetMapping("/list")
    public ResultInfo showList() {
        return ResultInfo.success(sysModuleService.getList());
    }

    @PostMapping("/roleModule")
    public ResultInfo modifyRoleModule(@RequestBody Map<String, Object> map) {
        String roleId = (String) map.get("roleId");
        List<Long> moduleIds = (List<Long>) map.get("moduleIds");
        if (sysModuleService.modifyRoleModule(Long.parseLong(roleId), moduleIds)) {
            return ResultInfo.success("更新成功！");
        }
        return ResultInfo.error("更新失败！");

    }


    @GetMapping("/page")
    public ResultInfo showPage(Integer page, Integer pageSize, String name) {
        Page<SysModule> pageInfo = new Page<>(page, pageSize);
        LambdaQueryWrapper<SysModule> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(name != null, SysModule::getName, name);
        sysModuleService.page(pageInfo, wrapper);
        return ResultInfo.success(pageInfo);
    }

}
