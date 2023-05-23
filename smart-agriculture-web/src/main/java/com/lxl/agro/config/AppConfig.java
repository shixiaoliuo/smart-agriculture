package com.lxl.agro.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Project : smart-agriculture-parent
 * Package : com.lxl.agro.config
 * Description : AppConfig
 * Author : LiuXinLei
 * createDate : 2023/5/23 11:15
 */
@Configuration
public class AppConfig {


    @Bean
    public MybatisPlusInterceptor createMybatisPlusInterceptor(){
        //1.创建拦截器
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        //2.添加分页拦截器
        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        //3.返回
        return mybatisPlusInterceptor;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
