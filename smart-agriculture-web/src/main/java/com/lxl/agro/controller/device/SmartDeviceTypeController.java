package com.lxl.agro.controller.device;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lxl.agro.common.ResultInfo;
import com.lxl.agro.pojo.FacilitiesConfig;
import com.lxl.agro.pojo.SmartDeviceType;
import com.lxl.agro.service.sys.SmartDeviceTypeService;
import org.springframework.web.bind.annotation.*;

/**
 * Project : smart-agriculture-parent
 * Package : com.lxl.agro.controller.device
 * Description : SmartDeviceTypeController
 * Author : LiuXinLei
 * createDate : 2023/5/22 20:56
 */
@RestController
@RequestMapping("/device/type")
public class SmartDeviceTypeController {

    private final SmartDeviceTypeService smartDeviceTypeService;

    public SmartDeviceTypeController(SmartDeviceTypeService smartDeviceTypeService) {
        this.smartDeviceTypeService = smartDeviceTypeService;
    }

    @GetMapping("/page")
    public ResultInfo showPage(int page, int pageSize, String name) {
        Page<SmartDeviceType> pageInfo = new Page<>(page, pageSize);
        LambdaQueryWrapper<SmartDeviceType> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(name != null, SmartDeviceType::getTypeName, name);
        smartDeviceTypeService.page(pageInfo, wrapper);
        return ResultInfo.success(pageInfo);
    }

    @PostMapping
    public ResultInfo save(@RequestBody SmartDeviceType smartDeviceType) {
        if (smartDeviceTypeService.save(smartDeviceType)) {
            return ResultInfo.success("添加成功！");
        }
        return ResultInfo.error("添加失败！");
    }

    @PutMapping
    public ResultInfo modify(@RequestBody SmartDeviceType smartDeviceType) {
        if (smartDeviceTypeService.updateById(smartDeviceType)) {
            return ResultInfo.success("修改成功！");
        }
        return ResultInfo.error("修改失败！");
    }

    @DeleteMapping
    public ResultInfo remove(Long id) {
        if (smartDeviceTypeService.removeById(id)) {
            return ResultInfo.success("删除成功！");
        }
        return ResultInfo.error("删除失败！");
    }

    @GetMapping("/selectOptions")
    public ResultInfo showOptions(){
        return ResultInfo.success(smartDeviceTypeService.selectOptions());
    }



}
