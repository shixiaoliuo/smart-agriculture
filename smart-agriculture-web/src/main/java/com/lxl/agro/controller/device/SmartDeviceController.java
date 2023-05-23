package com.lxl.agro.controller.device;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lxl.agro.common.ResultInfo;
import com.lxl.agro.pojo.SmartDevice;
import com.lxl.agro.service.sys.SmartDeviceService;
import org.omg.CORBA.LongHolder;
import org.springframework.web.bind.annotation.*;

/**
 * Project : smart-agriculture-parent
 * Package : com.lxl.agro.controller.device
 * Description : SmartDeviceController
 * Author : LiuXinLei
 * createDate : 2023/5/22 21:19
 */
@RestController
@RequestMapping("/device/device")
public class SmartDeviceController {

    private final SmartDeviceService smartDeviceService;


    public SmartDeviceController(SmartDeviceService smartDeviceService) {
        this.smartDeviceService = smartDeviceService;
    }

    @GetMapping("/page")
    public ResultInfo showPage(int page, int pageSize, String name, Long facilitiesId) {
        Page<SmartDevice> pageInfo = new Page<>(page, pageSize);
        LambdaQueryWrapper<SmartDevice> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(facilitiesId != null, SmartDevice::getFacilitiesId, facilitiesId)
                .like(name != null, SmartDevice::getDeviceName, name);
        smartDeviceService.page(pageInfo, wrapper);
        return ResultInfo.success(pageInfo);
    }

    @PostMapping
    public ResultInfo save(@RequestBody SmartDevice smartDevice) {
        if (smartDeviceService.save(smartDevice)) {
            return ResultInfo.success("添加成功！");
        }
        return ResultInfo.error("添加失败！");
    }

    @PutMapping
    public ResultInfo modify(@RequestBody SmartDevice smartDevice) {
        if (smartDeviceService.updateById(smartDevice)) {
            return ResultInfo.success("修改成功！");
        }
        return ResultInfo.error("修改失败！");
    }

    @DeleteMapping
    public ResultInfo remove(Long id) {
        if (smartDeviceService.removeById(id)) {
            return ResultInfo.success("删除成功！");
        }
        return ResultInfo.error("删除失败！");
    }

    //http://localhost:8080/device/device/updateStateById?deviceId=1660641050720509953
    @PutMapping("/updateStateById")
    public ResultInfo modifyStateById(Long deviceId){
        if (smartDeviceService.updateStateById(deviceId)) {
            return ResultInfo.success("更新成功！");
        }
        return ResultInfo.error("更新失败！");
    }

    //http://localhost:8080/device/device/selectOptions?facilitiesId=1630906724352364546
    @GetMapping("/selectOptions")
    public ResultInfo showOptions(Long facilitiesId){
        return ResultInfo.success(smartDeviceService.selectOptionsByFacilitiesId(facilitiesId));
    }

}
