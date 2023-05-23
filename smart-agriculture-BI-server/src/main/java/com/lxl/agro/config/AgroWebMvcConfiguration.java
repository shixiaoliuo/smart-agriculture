package com.lxl.agro.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AgroWebMvcConfiguration implements WebMvcConfigurer {


    /**
     * 指定静态资源位置
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /**
         * 指定查找静态资源的位置
         */
        registry.addResourceHandler("/BI/**").addResourceLocations("classpath:/BI/");
    }

}
