package com.lxl.agro.controller.facilities;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lxl.agro.common.ResultInfo;
import com.lxl.agro.pojo.FacilitiesConfig;
import com.lxl.agro.service.sys.FacilitiesConfigService;
import org.springframework.web.bind.annotation.*;

/**
 * Project : smart-agriculture-parent
 * Package : com.lxl.agro.controller.facilities
 * Description : ConfigController
 * Author : LiuXinLei
 * createDate : 2023/5/22 19:54
 */
@RestController
@RequestMapping("/facilities/config")
public class FacilitiesConfigController {

    private final FacilitiesConfigService facilitiesConfigService;

    public FacilitiesConfigController(FacilitiesConfigService facilitiesConfigService) {
        this.facilitiesConfigService = facilitiesConfigService;
    }

    @GetMapping("/page")
    public ResultInfo showPage(int page, int pageSize, String name, Long facilitiesId) {
        Page<FacilitiesConfig> pageInfo = new Page<>(page, pageSize);
        LambdaQueryWrapper<FacilitiesConfig> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(facilitiesId != null, FacilitiesConfig::getFacilitiesId, facilitiesId)
                .like(name != null, FacilitiesConfig::getFacilitiesName, name);
        facilitiesConfigService.page(pageInfo, wrapper);
        return ResultInfo.success(pageInfo);
    }

    @PostMapping
    public ResultInfo save(@RequestBody FacilitiesConfig facilitiesConfig) {
        if (facilitiesConfigService.save(facilitiesConfig)) {
            return ResultInfo.success("添加成功！");
        }
        return ResultInfo.error("添加失败！");
    }


    @PutMapping
    public ResultInfo modify(@RequestBody FacilitiesConfig facilitiesConfig) {
        if (facilitiesConfigService.updateById(facilitiesConfig)) {
            return ResultInfo.success("更新成功！");
        }
        return ResultInfo.error("更新失败！");
    }

}
