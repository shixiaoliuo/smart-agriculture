package com.lxl.agro.service.sys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lxl.agro.common.ResultInfo;
import com.lxl.agro.vo.THTBCountVo;

/**
 * Project : smart-agriculture-parent
 * Package : com.lxl.agro.service.sys
 * Description : THTBService
 * Author : LiuXinLei
 * createDate : 2023/5/23 10:53
 */
public interface HVService {




    /**
     * 通过定时任务拉取光照传感器的数据
     * @param
     * @return
     */
    ResultInfo pullDataBySchedule();
}
