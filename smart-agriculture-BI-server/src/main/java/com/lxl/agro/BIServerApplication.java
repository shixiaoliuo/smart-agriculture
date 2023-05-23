package com.lxl.agro;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

/**
 * Project : smart-agriculture-parent
 * Package : com.lxl.agro
 * Description : BIServeApplication
 * Author : LiuXinLei
 * createDate : 2023/5/23 10:48
 */
@SpringBootApplication
@EnableTransactionManagement
@Slf4j
@EnableScheduling //允许支持定时器了
public class BIServerApplication {
    /**
     * 启动类
     *
     * @param args
     */
    public static void main(String[] args) {
        //1.启动
        SpringApplication.run(BIServerApplication.class, args);
        //2.输出
        log.info("项目启动成功");
    }

    @Bean
    public RestTemplate loadBalanced1() {
        return new RestTemplate();
    }
}
