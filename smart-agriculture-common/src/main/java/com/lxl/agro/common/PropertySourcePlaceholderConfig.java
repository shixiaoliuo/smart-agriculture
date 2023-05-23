package com.lxl.agro.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * @Author：xudeming
 * @Package：com.qf.agro.common
 * @Project：smart-agriculture-parent
 * @name：PropertySourcePlaceholderConfig
 * @Date：2023/3/6 10:03
 * @Filename：PropertySourcePlaceholderConfig
 */
@Configuration
public class PropertySourcePlaceholderConfig {
    @Bean
    public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(){
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        configurer.setIgnoreUnresolvablePlaceholders(true);
        return configurer;
    }

}
