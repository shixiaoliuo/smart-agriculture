package com.lxl.agro.vo;

import lombok.Data;

/**
 * @Author：xudeming
 * @Package：com.qf.agro.vo
 * @Project：smart-agriculture-parent
 * @name：SysUserVo
 * @Date：2023/2/28 16:26
 * @Filename：SysUserVo
 */
@Data
public class SysUserVo {

    private Long userId;
    private String username;
    private String companyId;
    private String roleId;
}
