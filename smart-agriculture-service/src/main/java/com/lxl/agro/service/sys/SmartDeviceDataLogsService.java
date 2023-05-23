package com.lxl.agro.service.sys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lxl.agro.common.ResultInfo;
import com.lxl.agro.pojo.SmartDeviceDataLogs;

/**
 * Project : smart-agriculture-parent
 * Package : com.lxl.agro.service.sys
 * Description : SmartDeviceDataLogsService
 * Author : LiuXinLei
 * createDate : 2023/5/22 21:59
 */
public interface SmartDeviceDataLogsService extends IService<SmartDeviceDataLogs> {
    ResultInfo selectPage(int page, int pageSize, String name, Long facilitiesId, Long deviceId, Integer day);

    ResultInfo exportToExcel(Long facilitiesId, Long deviceId, Integer day);
}
