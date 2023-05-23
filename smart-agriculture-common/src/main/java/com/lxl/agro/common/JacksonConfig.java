package com.lxl.agro.common;


/**
 * @Author：xudeming
 * @Package：com.qf.agro.common
 * @Project：smart-agriculture-parent
 * @name：JacksonConfig
 * @Date：2023/2/27 11:06
 * @Filename：JacksonConfig

@Configurable
public class JacksonConfig {
    @Bean
    public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        //全局配置序列化返回json处理
        SimpleModule simpleModule = new SimpleModule();
        //json Long ==>String
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        objectMapper.registerModule(simpleModule);
        return objectMapper;
    }
} */
