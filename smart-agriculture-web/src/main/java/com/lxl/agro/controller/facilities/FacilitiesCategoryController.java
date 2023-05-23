package com.lxl.agro.controller.facilities;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lxl.agro.common.ResultInfo;
import com.lxl.agro.pojo.FacilitiesCategory;
import com.lxl.agro.service.sys.FacilitiesCategoryService;
import org.springframework.web.bind.annotation.*;

/**
 * Project : smart-agriculture-parent
 * Package : com.lxl.agro.controller.facilities
 * Description : CategoryController
 * Author : LiuXinLei
 * createDate : 2023/5/22 16:23
 */
@RestController
@RequestMapping("/facilities/category")
public class FacilitiesCategoryController {

    private final FacilitiesCategoryService facilitiesCategoryService;

    public FacilitiesCategoryController(FacilitiesCategoryService facilitiesCategoryService) {
        this.facilitiesCategoryService = facilitiesCategoryService;
    }

    @GetMapping("/page")
    public ResultInfo showPage(int page, int pageSize, String name) {
        Page<FacilitiesCategory> pageInfo = new Page<>(page, pageSize);
        LambdaQueryWrapper<FacilitiesCategory> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(name != null, FacilitiesCategory::getCategoryName, name);
        facilitiesCategoryService.page(pageInfo, wrapper);
        return ResultInfo.success(pageInfo);
    }

    @PostMapping
    public ResultInfo save(@RequestBody FacilitiesCategory facilitiesCategory) {
        return ResultInfo.success(facilitiesCategoryService.save(facilitiesCategory));
    }

    @PutMapping
    public ResultInfo modify(@RequestBody FacilitiesCategory facilitiesCategory) {
        LambdaUpdateWrapper<FacilitiesCategory> wrapper = new LambdaUpdateWrapper<>();
        Long id = facilitiesCategory.getId();
        wrapper.eq(id != null, FacilitiesCategory::getId, id);
        return ResultInfo.success(facilitiesCategoryService.update(facilitiesCategory, wrapper));
    }

    @DeleteMapping
    public ResultInfo remove(Long id) {
        LambdaUpdateWrapper<FacilitiesCategory> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(id != null, FacilitiesCategory::getId, id);
        if (facilitiesCategoryService.remove(wrapper)) {
            return ResultInfo.success("删除成功！");
        }
        return ResultInfo.error("删除成功！");
    }

    @GetMapping("/options")
    public ResultInfo showOptions() {
        return ResultInfo.success(facilitiesCategoryService.selectOptions());
    }



}
