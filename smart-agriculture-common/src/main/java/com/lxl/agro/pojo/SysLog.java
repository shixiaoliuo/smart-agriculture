package com.lxl.agro.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @company：千峰教育
 * @author：zhy
 * @date：2023-02-17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName("sa_sys_log")
@ApiModel("系统日志")
public class SysLog  implements Serializable {

    @TableId
    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("操作者登录名")
    private String username;

    @ApiModelProperty("操作者ip")
    private String ip;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty("操作时间")
    private Date createTime;

    @ApiModelProperty("操作的方法名")
    private String method;

    @ApiModelProperty("操作的功能名")
    private String action;

    @ApiModelProperty("所属企业id")
    private Long companyId;

    @ApiModelProperty("所属企业名称")
    private String companyName;
}
