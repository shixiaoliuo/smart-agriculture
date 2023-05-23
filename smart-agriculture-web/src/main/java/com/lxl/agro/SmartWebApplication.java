package com.lxl.agro;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Project : smart-agriculture-parent
 * Package : com.lxl.argo
 * Description : SmartWebApplication
 * Author : LiuXinLei
 * createDate : 2023/5/20 10:45
 */
@SpringBootApplication
@Slf4j
@EnableTransactionManagement
public class SmartWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(SmartWebApplication.class, args);
        log.info("启动成功！");
    }
}
