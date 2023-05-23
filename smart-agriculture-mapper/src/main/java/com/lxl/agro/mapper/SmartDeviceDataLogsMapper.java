package com.lxl.agro.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lxl.agro.pojo.SmartDeviceDataLogs;
import com.lxl.agro.vo.OptionVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author：xudeming
 * @Package：com.qf.agro.mapper
 * @Project：smart-agriculture-parent
 * @name：SmartDeviceDataLogs
 * @Date：2023/3/2 11:28
 * @Filename：SmartDeviceDataLogs
 */
@Mapper
public interface SmartDeviceDataLogsMapper extends BaseMapper<SmartDeviceDataLogs> {

  List<OptionVo>  selectByDeviceIdAndLimitNumAndTypeId(
          @Param("deviceId")String deviceId,@Param("typeId")String typeId,@Param("pageNum")Integer pageNum);
}
