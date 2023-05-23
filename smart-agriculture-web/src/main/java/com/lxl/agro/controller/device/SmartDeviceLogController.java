package com.lxl.agro.controller.device;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lxl.agro.common.ResultInfo;
import com.lxl.agro.pojo.SmartDevice;
import com.lxl.agro.pojo.SmartDeviceLog;
import com.lxl.agro.service.sys.SmartDeviceLogService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Project : smart-agriculture-parent
 * Package : com.lxl.agro.controller.device
 * Description : SmartDeviceLogController
 * Author : LiuXinLei
 * createDate : 2023/5/22 21:53
 */
@RestController
@RequestMapping("/device/deviceLog")
public class SmartDeviceLogController {

    private final SmartDeviceLogService smartDeviceLogService;

    public SmartDeviceLogController(SmartDeviceLogService smartDeviceLogService) {
        this.smartDeviceLogService = smartDeviceLogService;
    }

    @GetMapping("/page")
    public ResultInfo showPage(int page, int pageSize, String name, Long facilitiesId) {
        Page<SmartDeviceLog> pageInfo = new Page<>(page, pageSize);
        LambdaQueryWrapper<SmartDeviceLog> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(facilitiesId != null, SmartDeviceLog::getFacilitiesId, facilitiesId)
                .like(name != null, SmartDeviceLog::getDeviceName, name);
        smartDeviceLogService.page(pageInfo, wrapper);
        return ResultInfo.success(pageInfo);
    }


}
