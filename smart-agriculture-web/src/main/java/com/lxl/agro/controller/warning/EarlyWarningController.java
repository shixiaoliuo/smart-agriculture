package com.lxl.agro.controller.warning;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lxl.agro.common.ResultInfo;
import com.lxl.agro.pojo.EarlyWarning;
import com.lxl.agro.pojo.SmartDeviceDataLogs;
import com.lxl.agro.service.sys.EarlyWarningService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Project : smart-agriculture-parent
 * Package : com.lxl.agro.controller.warning
 * Description : EarlyWarningController
 * Author : LiuXinLei
 * createDate : 2023/5/22 22:13
 */
@RestController
@RequestMapping("/warning/warning")
public class EarlyWarningController {

    private final EarlyWarningService earlyWarningService;

    public EarlyWarningController(EarlyWarningService earlyWarningService) {
        this.earlyWarningService = earlyWarningService;
    }

    @GetMapping("/page")
    public ResultInfo showPage(int page, int pageSize, String name, Long facilitiesId, Integer day) {
        Page<EarlyWarning> pageInfo = new Page<>(page, pageSize);
        LambdaQueryWrapper<EarlyWarning> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(facilitiesId != null, EarlyWarning::getFacilitiesId, facilitiesId)
                .like(name != null, EarlyWarning::getDeviceName, name);
        earlyWarningService.page(pageInfo, wrapper);
        return ResultInfo.success(pageInfo);
    }

}
