package com.lxl.agro.config;

import com.lxl.agro.service.sys.HVService;
import com.lxl.agro.service.sys.THTBService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Project : smart-agriculture-parent
 * Package : com.lxl.agro.config
 * Description : ScheduleConfig
 * Author : LiuXinLei
 * createDate : 2023/5/23 10:52
 */
@Component
public class ScheduleConfig {

    //温湿度传感器
    private final THTBService thtbService;

    //光照传感器

    private final HVService hvService;

    public ScheduleConfig(THTBService thtbService, HVService hvService) {
        this.thtbService = thtbService;
        this.hvService = hvService;
    }


    //温湿度传感器
    @Async("asyncTaskExecutor")
    @Scheduled(cron = "0/30 * * * * ?")
    public void taskPULLTHTB() {
        System.out.println("温湿度传感器（这句话每30秒打印一次）"+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        thtbService.pullDataBySchedule();
    }

    //光照度传感器
    @Async("asyncTaskExecutor")
    @Scheduled(cron = "0/60 * * * * ?")
    public void taskPULLHV() {
        System.out.println("光照度传感器（这句话每60秒打印一次）"+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        hvService.pullDataBySchedule();
    }

}
