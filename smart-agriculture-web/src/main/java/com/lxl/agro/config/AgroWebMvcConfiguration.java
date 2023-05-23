package com.lxl.agro.config;

import com.lxl.agro.interceptors.CheckLoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Project : smart-agriculture-parent
 * Package : com.lxl.argo
 * Description : AgroWebMvcConfiguration
 * Author : LiuXinLei
 * createDate : 2023/5/20 11:59
 */
@Configuration
public class AgroWebMvcConfiguration implements WebMvcConfigurer {

    private final CheckLoginInterceptor checkLoginInterceptor;

    public AgroWebMvcConfiguration(CheckLoginInterceptor checkLoginInterceptor) {
        this.checkLoginInterceptor = checkLoginInterceptor;
    }

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
        registry.addResourceHandler("/backend/**").addResourceLocations("classpath:/backend/");
    }

    /**
     * 手动添加拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //创建不用拦截的路径数组                                                                       //文档需要释放的静态资源
        String[] pathArray = new String[]{
                "/backend/**",
                "/sys/user/login",
//                "/sys/user/logout",
                "/error",
                "/doc.html",
                "/webjars/**",
                "/swagger-resources",
                "/v2/api-docs"};
        /*
         * 注册拦截器
         *
         *  .addPathPatterns("/**") 指定拦截的规则
         * .excludePathPatterns(pathArray) 指定排除的路径
         */
        registry.addInterceptor(checkLoginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(pathArray);
    }
}
