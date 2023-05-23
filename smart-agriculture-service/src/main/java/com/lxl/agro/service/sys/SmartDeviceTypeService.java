package com.lxl.agro.service.sys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lxl.agro.pojo.SmartDeviceType;
import com.lxl.agro.vo.OptionVo;

import java.util.List;

/**
 * Project : smart-agriculture-parent
 * Package : com.lxl.agro.service.sys
 * Description : SmartDeviceTypeService
 * Author : LiuXinLei
 * createDate : 2023/5/22 20:57
 */
public interface SmartDeviceTypeService extends IService<SmartDeviceType> {
    List<OptionVo> selectOptions();
}
