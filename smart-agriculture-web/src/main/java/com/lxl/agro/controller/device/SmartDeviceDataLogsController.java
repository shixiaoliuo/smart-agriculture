package com.lxl.agro.controller.device;

import com.lxl.agro.common.ResultInfo;

import com.lxl.agro.service.sys.SmartDeviceDataLogsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Project : smart-agriculture-parent
 * Package : com.lxl.agro.controller.device
 * Description : SmartDeviceDataLogsController
 * Author : LiuXinLei
 * createDate : 2023/5/22 21:58
 */
@RestController
@RequestMapping("/device/dataLogs")
public class SmartDeviceDataLogsController {

    private final SmartDeviceDataLogsService smartDeviceDataLogsService;

    public SmartDeviceDataLogsController(SmartDeviceDataLogsService smartDeviceDataLogsService) {
        this.smartDeviceDataLogsService = smartDeviceDataLogsService;
    }

    //http://localhost:8080/device/dataLogs/page?page=1&pageSize=5&facilitiesId=1630457575358128130&deviceId=1631215625111179266&resultCode=200
    @GetMapping("/page")
    public ResultInfo showPage(int page, int pageSize, String name, Long facilitiesId, Long deviceId, Integer day) {

        return smartDeviceDataLogsService.selectPage(page, pageSize, name, facilitiesId, deviceId, day);
    }

    @GetMapping("/export")
    public ResultInfo export(Long facilitiesId, Long deviceId, Integer day){
        return smartDeviceDataLogsService.exportToExcel(facilitiesId,deviceId,day);
    }


}
