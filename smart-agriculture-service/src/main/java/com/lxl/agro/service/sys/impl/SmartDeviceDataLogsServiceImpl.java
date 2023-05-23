package com.lxl.agro.service.sys.impl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lxl.agro.common.ResultInfo;
import com.lxl.agro.mapper.SmartDeviceDataLogsMapper;
import com.lxl.agro.pojo.SmartDeviceDataLogs;
import com.lxl.agro.service.sys.SmartDeviceDataLogsService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Project : smart-agriculture-parent
 * Package : com.lxl.agro.service.sys.impl
 * Description : SmartDeviceDataLogsServiceImpl
 * Author : LiuXinLei
 * createDate : 2023/5/22 21:59
 */
@Service
public class SmartDeviceDataLogsServiceImpl extends ServiceImpl<SmartDeviceDataLogsMapper, SmartDeviceDataLogs> implements SmartDeviceDataLogsService {

    private final SmartDeviceDataLogsMapper smartDeviceDataLogsMapper;

    public SmartDeviceDataLogsServiceImpl(SmartDeviceDataLogsMapper smartDeviceDataLogsMapper) {
        this.smartDeviceDataLogsMapper = smartDeviceDataLogsMapper;
    }


    @Override
    public ResultInfo selectPage(int page, int pageSize, String name, Long facilitiesId, Long deviceId, Integer day) {
        String formattedDateTime = null;
        if (day != null) {
            Instant timestamp = Instant.now();

            // 将时间戳转换为LocalDateTime对象
            LocalDateTime dateTime = LocalDateTime.ofInstant(timestamp, ZoneId.systemDefault());

            // 将LocalDateTime对象减去day天
            LocalDateTime previousDay = dateTime.minusDays(day);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            formattedDateTime = previousDay.format(formatter);
        }


        Page<SmartDeviceDataLogs> pageInfo = new Page<>(page, pageSize);
        LambdaQueryWrapper<SmartDeviceDataLogs> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(facilitiesId != null, SmartDeviceDataLogs::getFacilitiesId, facilitiesId)
                .eq(deviceId != null, SmartDeviceDataLogs::getDeviceId, deviceId)
                .like(name != null, SmartDeviceDataLogs::getDeviceName, name)
                .gt(day != null, SmartDeviceDataLogs::getCreateTime, formattedDateTime);
        smartDeviceDataLogsMapper.selectPage(pageInfo, wrapper);
        return ResultInfo.success(pageInfo);
    }

    @Override
    public ResultInfo exportToExcel(Long facilitiesId, Long deviceId, Integer day) {

        String formattedDateTime = null;
        if (day != null) {
            Instant timestamp = Instant.now();
            LocalDateTime dateTime = LocalDateTime.ofInstant(timestamp, ZoneId.systemDefault());
            LocalDateTime previousDay = dateTime.minusDays(day);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            formattedDateTime = previousDay.format(formatter);
        }

        LambdaQueryWrapper<SmartDeviceDataLogs> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(facilitiesId != null, SmartDeviceDataLogs::getFacilitiesId, facilitiesId)
                .eq(deviceId != null, SmartDeviceDataLogs::getDeviceId, deviceId)
                .gt(day != null, SmartDeviceDataLogs::getCreateTime, formattedDateTime);

        List<SmartDeviceDataLogs> smartDeviceDataLogs = smartDeviceDataLogsMapper.selectList(wrapper);

        ExportParams exportParams = new ExportParams();
        exportParams.setTitle("统计分析");
        exportParams.setSheetName("数据统计");
        exportParams.setType(ExcelType.XSSF);

        Workbook sheets = ExcelExportUtil.exportExcel(exportParams, SmartDeviceDataLogs.class, smartDeviceDataLogs);
        FileOutputStream fos = null;
        Instant timestamp = Instant.now();
        LocalDateTime dateTime = LocalDateTime.ofInstant(timestamp, ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String format = dateTime.format(formatter);
        String target = "统计数据" + format;
        try {
            fos = new FileOutputStream(target + ".xlsx");
            sheets.write(fos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return ResultInfo.success("导出成功！");
    }
}
