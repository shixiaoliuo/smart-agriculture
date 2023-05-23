package com.lxl.agro.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
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
import java.util.List;

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
@TableName("sa_sys_role")
@ApiModel("系统角色")
public class SysRole  implements Serializable {

    @TableId
    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("角色名称")
    private String name;

    @ApiModelProperty("角色描述")
    private String remark;

    @ApiModelProperty("排序号")
    private Long orderNo;

    @ApiModelProperty("创建人id")
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty("更新人id")
    @TableField(fill = FieldFill.UPDATE)
    private Long updateBy;

    @ApiModelProperty("更新时间")
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

    @ApiModelProperty("所属企业id")
    @TableField(fill = FieldFill.INSERT)
    private Long companyId;

    @ApiModelProperty("所属企业名称")
    @TableField(fill = FieldFill.INSERT)
    private String companyName;

    @ApiModelProperty("角色权限的集合")
    @TableField(exist = false)
    private List<SysModule> moduleList;
}
