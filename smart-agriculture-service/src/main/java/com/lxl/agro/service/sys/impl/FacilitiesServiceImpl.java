package com.lxl.agro.service.sys.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lxl.agro.mapper.FacilitiesConfigMapper;
import com.lxl.agro.mapper.FacilitiesMapper;
import com.lxl.agro.pojo.Facilities;
import com.lxl.agro.service.sys.FacilitiesService;
import com.lxl.agro.vo.OptionVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Project : smart-agriculture-parent
 * Package : com.lxl.agro.service.sys.impl
 * Description : FacilitiesServiceImpl
 * Author : LiuXinLei
 * createDate : 2023/5/22 17:19
 */
@Service
public class FacilitiesServiceImpl extends ServiceImpl<FacilitiesMapper, Facilities> implements FacilitiesService {

    private final FacilitiesMapper facilitiesMapper;

    public FacilitiesServiceImpl(FacilitiesMapper facilitiesMapper) {
        this.facilitiesMapper = facilitiesMapper;
    }


    @Override
    public List<OptionVo> selectOptions() {
        return facilitiesMapper.selectOptions();
    }

    @Override
    public List<OptionVo> selectOptionsByCompanyId(Long companyId) {
        return facilitiesMapper.selectOptionsByCompanyId(companyId);
    }
}
