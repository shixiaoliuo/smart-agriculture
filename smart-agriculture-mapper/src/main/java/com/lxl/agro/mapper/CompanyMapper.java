package com.lxl.agro.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lxl.agro.pojo.Company;
import com.lxl.agro.vo.OptionVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @company：千峰教育
 * @author：zhy
 * @date：2023-02-17
 */
@Mapper
public interface CompanyMapper extends BaseMapper<Company> {
    List<OptionVo> selectOptions();
}
