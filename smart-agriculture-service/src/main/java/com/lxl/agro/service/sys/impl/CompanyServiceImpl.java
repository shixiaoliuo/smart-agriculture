package com.lxl.agro.service.sys.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lxl.agro.mapper.CompanyMapper;
import com.lxl.agro.pojo.Company;
import com.lxl.agro.service.sys.CompanyService;
import com.lxl.agro.vo.OptionVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Project : smart-agriculture-parent
 * Package : com.lxl.agro.service.sys.impl
 * Description : CompanyServiceImpl
 * Author : LiuXinLei
 * createDate : 2023/5/20 20:09
 */
@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements CompanyService {

    private final CompanyMapper companyMapper;

    public CompanyServiceImpl(CompanyMapper companyMapper) {
        this.companyMapper = companyMapper;
    }


    @Override
    public List<OptionVo> selectOptions() {
        return companyMapper.selectOptions();
    }
}
