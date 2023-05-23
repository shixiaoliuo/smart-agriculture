package com.lxl.agro.controller.sys;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lxl.agro.common.ResultInfo;
import com.lxl.agro.pojo.SysLog;
import com.lxl.agro.pojo.SysRole;
import com.lxl.agro.service.sys.SysLogService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Project : smart-agriculture-parent
 * Package : com.lxl.agro.controller.sys
 * Description : SysLogController
 * Author : LiuXinLei
 * createDate : 2023/5/22 15:04
 */
@RestController
@RequestMapping("/sys/log")
public class SysLogController {

    private final SysLogService sysLogService;

    public SysLogController(SysLogService sysLogService) {
        this.sysLogService = sysLogService;
    }

    @GetMapping("/page")
    public ResultInfo showPage(int page, int pageSize, String name) {
        Page<SysLog> pageInfo = new Page<>(page, pageSize);
        LambdaQueryWrapper<SysLog> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(name != null, SysLog::getUsername, name);
        sysLogService.page(pageInfo, wrapper);
        return ResultInfo.success(pageInfo);
    }

}
