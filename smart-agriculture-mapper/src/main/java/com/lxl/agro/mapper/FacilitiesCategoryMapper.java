package com.lxl.agro.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lxl.agro.pojo.FacilitiesCategory;
import com.lxl.agro.vo.OptionVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface FacilitiesCategoryMapper extends BaseMapper<FacilitiesCategory> {

    List<OptionVo> selectFacilitiesCategoryOptions();

}
