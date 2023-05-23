package com.lxl.agro.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
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
@TableName("sa_sys_module")
@ApiModel("系统功能")
public class SysModule implements Serializable {

    @TableId
    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("父菜单id")
    private String parentId;

    @ApiModelProperty("父菜单名称")
    private String parentName;

    @ApiModelProperty("菜单名称")
    private String name;

    @ApiModelProperty("层级号")
    private Long layerNum;

    @ApiModelProperty("是否叶节点")
    private Long isLeaf;

    @ApiModelProperty("菜单图标")
    private String ico;

    @ApiModelProperty("菜单权限名称 可以和菜单名称同名")
    private String cpermission;

    @ApiModelProperty("菜单对应链接")
    private String curl;

    @ApiModelProperty("菜单类型 0主菜单 1左侧菜单 2按钮 3链接  4状态 ")
    private Long ctype;

    @ApiModelProperty("菜单状态  1启用  0停用")
    private Long state;

    @ApiModelProperty("从属关系  0 saas菜单   1租户菜单 ")
    private String belong;

    @ApiModelProperty("所属功能 预留字段 可以为Null")
    private String cwhich;

    @ApiModelProperty("引用号 预留字段 可以为Null")
    private Long quoteNum;

    @ApiModelProperty("菜单描述")
    private String remark;

    @ApiModelProperty("排序号")
    private Long orderNo;

    @TableField(exist = false)
    private List<SysModule> children;

}
