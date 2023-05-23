package com.lxl.agro.service.sys.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lxl.agro.mapper.SmartDeviceMapper;
import com.lxl.agro.mapper.SmartDeviceTypeMapper;
import com.lxl.agro.pojo.SmartDeviceType;
import com.lxl.agro.service.sys.SmartDeviceTypeService;
import com.lxl.agro.vo.OptionVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Project : smart-agriculture-parent
 * Package : com.lxl.agro.service.sys.impl
 * Description : SmartDeviceTypeServiceImpl
 * Author : LiuXinLei
 * createDate : 2023/5/22 20:58
 */
@Service
public class SmartDeviceTypeServiceImpl extends ServiceImpl<SmartDeviceTypeMapper, SmartDeviceType> implements SmartDeviceTypeService {

    private final SmartDeviceMapper smartDeviceMapper;

    public SmartDeviceTypeServiceImpl(SmartDeviceMapper smartDeviceMapper) {
        this.smartDeviceMapper = smartDeviceMapper;
    }

    @Override
    public List<OptionVo> selectOptions() {
        return smartDeviceMapper.selectOptions();
    }
}
