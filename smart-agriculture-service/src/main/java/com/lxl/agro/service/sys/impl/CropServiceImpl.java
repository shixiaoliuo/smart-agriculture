package com.lxl.agro.service.sys.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lxl.agro.mapper.CropMapper;
import com.lxl.agro.pojo.Crop;
import com.lxl.agro.service.sys.CropService;
import org.springframework.stereotype.Service;

/**
 * Project : smart-agriculture-parent
 * Package : com.lxl.agro.service.sys.impl
 * Description : CropServiceImpl
 * Author : LiuXinLei
 * createDate : 2023/5/22 22:26
 */
@Service
public class CropServiceImpl extends ServiceImpl<CropMapper, Crop> implements CropService {
}
