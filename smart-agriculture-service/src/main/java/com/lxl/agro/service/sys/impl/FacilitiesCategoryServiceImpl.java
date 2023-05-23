package com.lxl.agro.service.sys.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lxl.agro.mapper.FacilitiesCategoryMapper;
import com.lxl.agro.pojo.FacilitiesCategory;
import com.lxl.agro.service.sys.FacilitiesCategoryService;
import com.lxl.agro.vo.OptionVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Project : smart-agriculture-parent
 * Package : com.lxl.agro.service.sys.impl
 * Description : FacilitiesCategoryServiceImpl
 * Author : LiuXinLei
 * createDate : 2023/5/22 16:25
 */
@Service
public class FacilitiesCategoryServiceImpl extends ServiceImpl<FacilitiesCategoryMapper, FacilitiesCategory> implements FacilitiesCategoryService {

    private final FacilitiesCategoryMapper facilitiesCategoryMapper;

    public FacilitiesCategoryServiceImpl(FacilitiesCategoryMapper facilitiesCategoryMapper) {
        this.facilitiesCategoryMapper = facilitiesCategoryMapper;
    }

    @Override
    public List<OptionVo> selectOptions() {
        return facilitiesCategoryMapper.selectFacilitiesCategoryOptions();
    }
}
