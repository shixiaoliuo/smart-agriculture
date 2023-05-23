package com.lxl.agro.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.lxl.agro.common.SysUserHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        metaObject.setValue("createTime",new Date());
        metaObject.setValue("updateTime",new Date());
        if(SysUserHolder.get() != null) {
            metaObject.setValue("createBy", SysUserHolder.get().getId());
            metaObject.setValue("updateBy", SysUserHolder.get().getId());
            metaObject.setValue("companyId", SysUserHolder.get().getCompanyId());
            metaObject.setValue("companyName", SysUserHolder.get().getCompanyName());
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        metaObject.setValue("updateTime",new Date());
        if(SysUserHolder.get() != null) {
            metaObject.setValue("updateBy", SysUserHolder.get().getId());
        }
    }
}
