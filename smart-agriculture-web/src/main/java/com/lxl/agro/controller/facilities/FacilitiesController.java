package com.lxl.agro.controller.facilities;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lxl.agro.common.ResultInfo;
import com.lxl.agro.pojo.Facilities;
import com.lxl.agro.pojo.FacilitiesConfig;
import com.lxl.agro.service.sys.FacilitiesConfigService;
import com.lxl.agro.service.sys.FacilitiesService;

import org.springframework.web.bind.annotation.*;

/**
 * Project : smart-agriculture-parent
 * Package : com.lxl.agro.controller.facilities
 * Description : FacilitiesController
 * Author : LiuXinLei
 * createDate : 2023/5/22 17:17
 */
@RestController
@RequestMapping("/facilities/facilities")
@CrossOrigin(origins = "http://localhost:9001")
public class FacilitiesController {

    private final FacilitiesService facilitiesService;

    public FacilitiesController(FacilitiesService facilitiesService) {
        this.facilitiesService = facilitiesService;
    }

    @GetMapping("/page")
    public ResultInfo showPage(int page, int pageSize, String name) {
        Page<Facilities> pageInfo = new Page<>(page, pageSize);
        LambdaQueryWrapper<Facilities> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(name != null, Facilities::getCategoryName, name);
        facilitiesService.page(pageInfo, wrapper);
        return ResultInfo.success(pageInfo);
    }

    @PostMapping
    public ResultInfo save(@RequestBody Facilities facilities) {
        if (facilitiesService.save(facilities)) {
            return ResultInfo.success("添加成功！");
        }
        return ResultInfo.error("添加失败！");
    }

    @PutMapping
    public ResultInfo modify(@RequestBody Facilities facilities) {
        LambdaUpdateWrapper<Facilities> wrapper = new LambdaUpdateWrapper<>();
        Long id = facilities.getId();
        wrapper.eq(id != null, Facilities::getId, id);
        if (facilitiesService.update(facilities, wrapper)) {
            return ResultInfo.success("更新成功！");
        }
        return ResultInfo.error("更新失败！");
    }

    @DeleteMapping
    public ResultInfo remove(Long id) {
        if (facilitiesService.removeById(id)) {
            return ResultInfo.success("删除成功！");
        }
        return ResultInfo.error("删除失败！");
    }

    @GetMapping("/selectOptions")
    public ResultInfo showOptions(){
        return ResultInfo.success(facilitiesService.selectOptions());
    }

    //http://127.0.0.1:8080/facilities/facilities/selectOptionsByCompanyId?companyId=1630733260198899714

//    @CrossOrigin
    @GetMapping("/selectOptionsByCompanyId")
    public ResultInfo showOptionsByCompanyId(Long companyId){
        return ResultInfo.success(facilitiesService.selectOptionsByCompanyId(companyId));
    }


}
