package com.lxl.agro.service.sys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lxl.agro.pojo.Company;
import com.lxl.agro.pojo.SysModule;
import com.lxl.agro.vo.OptionVo;

import java.util.List;

/**
 * Project : smart-agriculture-parent
 * Package : com.lxl.agro.service.sys
 * Description : CompanyService
 * Author : LiuXinLei
 * createDate : 2023/5/20 20:09
 */
public interface CompanyService extends IService<Company> {

    List<OptionVo> selectOptions();
}
