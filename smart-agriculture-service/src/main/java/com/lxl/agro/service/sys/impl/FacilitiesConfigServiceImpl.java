package com.lxl.agro.service.sys.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lxl.agro.mapper.FacilitiesConfigMapper;
import com.lxl.agro.pojo.FacilitiesConfig;
import com.lxl.agro.service.sys.FacilitiesConfigService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Project : smart-agriculture-parent
 * Package : com.lxl.agro.service.sys.impl
 * Description : FacilitiesConfigServiceImpl
 * Author : LiuXinLei
 * createDate : 2023/5/22 20:09
 */
@Service
public class FacilitiesConfigServiceImpl extends ServiceImpl<FacilitiesConfigMapper, FacilitiesConfig> implements FacilitiesConfigService {
}
