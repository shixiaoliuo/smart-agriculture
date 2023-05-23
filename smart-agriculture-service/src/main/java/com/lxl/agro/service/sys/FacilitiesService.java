package com.lxl.agro.service.sys;

import cn.hutool.core.lang.Opt;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lxl.agro.pojo.Facilities;
import com.lxl.agro.vo.OptionVo;

import java.util.List;

/**
 * Project : smart-agriculture-parent
 * Package : com.lxl.agro.service.sys
 * Description : FacilitiesService
 * Author : LiuXinLei
 * createDate : 2023/5/22 17:19
 */
public interface FacilitiesService extends IService<Facilities> {
    List<OptionVo> selectOptions();

    List<OptionVo> selectOptionsByCompanyId(Long companyId);
}
