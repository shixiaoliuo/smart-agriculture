package com.lxl.agro.pojo;

import com.baomidou.mybatisplus.annotation.*;
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
@TableName("sa_sys_user")
@ApiModel("系统用户")
public class SysUser  implements Serializable {

    @TableId
    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("用户邮箱")
    private String email;

    @ApiModelProperty("用户名称")
    private String username;

    @ApiModelProperty("身份证号")
    private String station;

    @ApiModelProperty("用户密码")
    private String password;

    @ApiModelProperty("用户状态 1 启用  0 停用")
    private Integer state;

    @ApiModelProperty("用户所属组id")
    private Long groupId;

    @ApiModelProperty("上级用户id")
    private Long managerId;

    @ApiModelProperty("用户性别")
    private String gender;

    @ApiModelProperty("用户电话")
    private String telephone;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ApiModelProperty("用户生日")
    private Date birthday;

    @ApiModelProperty("用户薪水")
    private Long salary;

    @ApiModelProperty("入职日期")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date joinDate;

    @ApiModelProperty("排序号")
    private Integer orderNo;

    @ApiModelProperty("用户所属企业id")
    @TableField(fill = FieldFill.INSERT)
    private Long companyId;

    @ApiModelProperty("用户所属企业名称")
    @TableField(fill = FieldFill.INSERT)
    private String companyName;

    @ApiModelProperty("创建人id")
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty("更新人id")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;

    @ApiModelProperty("更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty("描述")
    private String remark;

    @ApiModelProperty("用户具备的角色集合，该数据不存在数据库")
    @TableField(exist = false)
    private List<SysRole> roleList;

}
