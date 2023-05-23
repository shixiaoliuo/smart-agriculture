package com.lxl.agro.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lxl.agro.pojo.Facilities;
import com.lxl.agro.vo.OptionVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * @company：千峰教育
 * @date：2023-02-17
 */
@Mapper
public interface FacilitiesMapper extends BaseMapper<Facilities> {

    List<OptionVo> selectOptions();

    List<OptionVo> selectOptionsByCompanyId(@Param("companyId")Long companyId);


}
