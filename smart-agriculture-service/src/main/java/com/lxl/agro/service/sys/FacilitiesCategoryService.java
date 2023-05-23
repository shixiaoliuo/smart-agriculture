package com.lxl.agro.service.sys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lxl.agro.pojo.FacilitiesCategory;
import com.lxl.agro.vo.OptionVo;

import java.util.List;

/**
 * Project : smart-agriculture-parent
 * Package : com.lxl.agro.service.sys
 * Description : FacilitiesCategoryService
 * Author : LiuXinLei
 * createDate : 2023/5/22 16:24
 */
public interface FacilitiesCategoryService extends IService<FacilitiesCategory> {
    List<OptionVo> selectOptions();
}
