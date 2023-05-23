package com.lxl.agro.controller.company;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lxl.agro.common.ResultInfo;
import com.lxl.agro.pojo.Company;
import com.lxl.agro.service.sys.CompanyService;
import org.springframework.web.bind.annotation.*;

/**
 * Project : smart-agriculture-parent
 * Package : com.lxl.agro.controller.sys
 * Description : CompanyController
 * Author : LiuXinLei
 * createDate : 2023/5/20 20:08
 */
@RestController
@RequestMapping("/company")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/page")
    public ResultInfo showPage(int page, int pageSize, String name) {
        Page pageInfo = new Page(page, pageSize);
        LambdaQueryWrapper<Company> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(name != null, Company::getName, name);
        companyService.page(pageInfo, wrapper);
        return ResultInfo.success(pageInfo);
    }

    @PostMapping
    public ResultInfo save(@RequestBody Company company) {
        if (companyService.save(company)) {
            return ResultInfo.success("保存成功！");
        }
        return ResultInfo.error("保存失败！");
    }

    @PutMapping
    public ResultInfo modify(@RequestBody Company company) {
        Long id = company.getId();
        LambdaUpdateWrapper<Company> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(id != null, Company::getId, id);
        if (companyService.update(company, wrapper)) {
            return ResultInfo.success("更新成功！");
        }
        return ResultInfo.error("更新失败！");
    }


    @DeleteMapping
    public ResultInfo remove(Long id) {
        LambdaUpdateWrapper<Company> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(id != null, Company::getId, id);
        if (companyService.remove(wrapper)) {
            return ResultInfo.success("删除成功！");
        }
        return ResultInfo.error("删除失败！");
    }

    @GetMapping("/selectOptions")
    public ResultInfo showOptions() {
        return ResultInfo.success(companyService.selectOptions());
    }


}
