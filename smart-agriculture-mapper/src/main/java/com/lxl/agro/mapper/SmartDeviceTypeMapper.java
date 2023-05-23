package com.lxl.agro.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lxl.agro.pojo.SmartDeviceType;
import com.lxl.agro.vo.OptionVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 *
 * @company：千峰教育
 * @date：2023-02-17
 */
@Mapper
public interface SmartDeviceTypeMapper extends BaseMapper<SmartDeviceType> {

    List<OptionVo> selectOptions();
}
