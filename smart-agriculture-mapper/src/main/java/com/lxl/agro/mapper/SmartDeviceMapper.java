package com.lxl.agro.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lxl.agro.pojo.SmartDevice;
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
public interface SmartDeviceMapper extends BaseMapper<SmartDevice> {

    List<OptionVo> selectOptions();

    //根据大棚的id查询下面的设备信息
//   List<OptionVo> selectOptions(@Param("facilitiesId")String facilitiesId);
    //修改设置状态

    Integer updateFlagById(@Param("flag")Integer flag,@Param("id")Long id);


    List<SmartDevice> selectByfacilitiesId(@Param("facilitiesId")String facilitiesId);

    List<OptionVo> selectByFacilitiesId(Long facilitiesId);
}
