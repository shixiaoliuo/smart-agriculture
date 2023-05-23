package com.lxl.agro.service.sys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lxl.agro.common.ResultInfo;
import com.lxl.agro.pojo.SysUser;

/**
 * Project : smart-agriculture-parent
 * Package : com.lxl.agro.service.sys
 * Description : SysUserService
 * Author : LiuXinLei
 * createDate : 2023/5/20 14:18
 */
public interface SysUserService extends IService<SysUser> {
    /**
     * 登录接口
     * @param username 由前端传递过来的用户名
     * @param password 由前端传递过来的密码
     * @return 工具类
     */
    ResultInfo login(String username, String password);

    ResultInfo logout(Long id);
}
