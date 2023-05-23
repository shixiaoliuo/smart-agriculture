package com.lxl.agro.service.sys;

import com.lxl.agro.common.ResultInfo;

public interface THTBService {




    /**
     * 通过定时任务拉取温湿度传感器的数据
     * @param
     * @return
     */
    ResultInfo pullDataBySchedule();
}
