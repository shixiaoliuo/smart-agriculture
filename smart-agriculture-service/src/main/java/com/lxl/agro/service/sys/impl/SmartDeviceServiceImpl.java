package com.lxl.agro.service.sys.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lxl.agro.mapper.SmartDeviceMapper;
import com.lxl.agro.pojo.SmartDevice;
import com.lxl.agro.service.sys.SmartDeviceService;
import com.lxl.agro.vo.OptionVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Project : smart-agriculture-parent
 * Package : com.lxl.agro.service.sys.impl
 * Description : SmartDeviceServiceImpl
 * Author : LiuXinLei
 * createDate : 2023/5/22 21:20
 */
@Service
public class SmartDeviceServiceImpl extends ServiceImpl<SmartDeviceMapper, SmartDevice> implements SmartDeviceService {

    private final SmartDeviceMapper smartDeviceMapper;

    public SmartDeviceServiceImpl(SmartDeviceMapper smartDeviceMapper) {
        this.smartDeviceMapper = smartDeviceMapper;
    }

    @Override
    public boolean updateStateById(Long deviceId) {
        SmartDevice smartDevice = smartDeviceMapper.selectById(deviceId);
        Integer state = smartDevice.getState();
        Integer reState = null;
        if (state == 1) {
            reState = 0;
            smartDevice.setState(reState);
        } else {
            reState = 1;
            smartDevice.setState(reState);
        }
        LambdaUpdateWrapper<SmartDevice> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(deviceId != null, SmartDevice::getId, deviceId);
        smartDeviceMapper.update(smartDevice, wrapper);
        return true;
    }

    @Override
    public List<OptionVo> selectOptionsByFacilitiesId(Long facilitiesId) {
        return smartDeviceMapper.selectByFacilitiesId(facilitiesId);
    }
}
