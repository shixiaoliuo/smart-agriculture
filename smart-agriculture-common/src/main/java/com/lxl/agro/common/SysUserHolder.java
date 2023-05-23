package com.lxl.agro.common;


import com.lxl.agro.pojo.SysUser;

/**
 * 存入线程局部变量
 */
public class SysUserHolder {

    private static ThreadLocal<SysUser> threadLocal = new ThreadLocal<SysUser>();

    public static void set(SysUser sysUser) {
        threadLocal.set(sysUser);
    }


    public static SysUser get() {
        return threadLocal.get();
    }


    public static void remove() {
        threadLocal.remove();
    }
}
