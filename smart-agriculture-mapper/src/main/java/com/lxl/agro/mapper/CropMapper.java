package com.lxl.agro.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lxl.agro.pojo.Crop;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Author：xudeming
 * @Package：com.qf.agro.mapper
 * @Project：smart-agriculture-parent
 * @name：CropMapper
 * @Date：2023/3/1 15:09
 * @Filename：CropMapper
 */
@Mapper
public interface CropMapper extends BaseMapper<Crop> {

}
