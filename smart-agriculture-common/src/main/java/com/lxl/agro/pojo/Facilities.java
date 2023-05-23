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
@TableName("sa_agro_facilities")
@ApiModel("农业设施")
public class Facilities  implements Serializable {

    @TableId
    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("设施名称")
    private String facilitiesName;

    @ApiModelProperty("设施分类id")
    private Long categoryId;

    @ApiModelProperty("设施分类名称")
    private String categoryName;

    @ApiModelProperty("设施所在省份名称")
    private String provinceName;

    @ApiModelProperty("设施所在城市名称")
    private String cityName;

    @ApiModelProperty("设施所在乡镇名称")
    private String townName;

    @ApiModelProperty("设施占地面积")
    private Double occupationArea;

    @ApiModelProperty("设施包含硬件数量")
    private Integer deviceCount;

    @ApiModelProperty("设施状态")
    private Integer state;

    @ApiModelProperty("设施负责人id")
    private Long userId;

    @ApiModelProperty("设施负责人名称")
    private String userName;

    @ApiModelProperty("设施介绍")
    private String remark;

    @ApiModelProperty("创建人id")
    private Long createBy;

    @ApiModelProperty("创建人所属组id")
    private Long createGroup;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("更新人id")
    private Long updateBy;

    @ApiModelProperty("更新人所属组")
    private Long updateGroup;

    @ApiModelProperty("更新时间")
    private Date updateTime;

    @ApiModelProperty("所属企业id")
    private Long companyId;

    @ApiModelProperty("所属企业名称")
    private String companyName;
    @ApiModelProperty("联系人")
    private String linkman;
    @ApiModelProperty("联系人电话")
    private String linkmanTel;
}
