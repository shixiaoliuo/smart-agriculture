package com.lxl.agro.service.sys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lxl.agro.pojo.SmartDevice;
import com.lxl.agro.vo.OptionVo;

import java.util.List;

/**
 * Project : smart-agriculture-parent
 * Package : com.lxl.agro.service.sys
 * Description : SmartDeviceService
 * Author : LiuXinLei
 * createDate : 2023/5/22 21:19
 */
public interface SmartDeviceService extends IService<SmartDevice> {
    boolean updateStateById(Long deviceId);

    List<OptionVo> selectOptionsByFacilitiesId(Long facilitiesId);
}
