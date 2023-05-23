package com.lxl.agro.controller.crop;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lxl.agro.common.ResultInfo;
import com.lxl.agro.pojo.Crop;
import com.lxl.agro.pojo.SmartDeviceDataLogs;
import com.lxl.agro.pojo.SysUser;
import com.lxl.agro.service.sys.CropService;
import org.springframework.web.bind.annotation.*;

/**
 * Project : smart-agriculture-parent
 * Package : com.lxl.agro.controller.crop
 * Description : CropController
 * Author : LiuXinLei
 * createDate : 2023/5/22 22:24
 */
@RestController
@RequestMapping("/crop/crop")
public class CropController {

    private final CropService cropService;

    public CropController(CropService cropService) {
        this.cropService = cropService;
    }


    @GetMapping("/page")
    public ResultInfo showPage(int page, int pageSize, String name, Long facilitiesId) {
        Page<Crop> pageInfo = new Page<>(page, pageSize);
        LambdaQueryWrapper<Crop> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(facilitiesId != null, Crop::getFacilitiesId, facilitiesId)
                .like(name != null, Crop::getName, name);
        cropService.page(pageInfo, wrapper);
        return ResultInfo.success(pageInfo);
    }

    @PostMapping
    public ResultInfo save(@RequestBody Crop crop) {
        if (cropService.save(crop)) {
            return ResultInfo.success("保存成功！");
        }
        return ResultInfo.error("保存失败！");
    }

    @PutMapping
    public ResultInfo modify(@RequestBody Crop crop) {
        if (cropService.updateById(crop)) {
            return ResultInfo.success("更新成功！");
        }
        return ResultInfo.error("更新失败！");
    }


    @DeleteMapping
    public ResultInfo remove(Long id) {
        if (cropService.removeById(id)) {
            return ResultInfo.success("删除成功！");
        }
        return ResultInfo.error("删除失败！");
    }


}
