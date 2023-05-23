package com.lxl.agro.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("sa_agro_smartdevice")
@ApiModel("智能设备")
public class SmartDevice  implements Serializable {

    @TableId
    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("设备名称")
    private String deviceName;

    @ApiModelProperty("所属农业设施id")
    private Long facilitiesId;

    @ApiModelProperty("所属农业设施")
    private String facilitiesName;

    @ApiModelProperty("设备类型id")
    private Long typeId;

    @ApiModelProperty("设备类型名称")
    private String typeName;

    @ApiModelProperty("设备使用状态 1正常 0异常")
    private Integer state;

    @ApiModelProperty("设备工作状态 1运行中 0已停止 -1待维修 -2维修中 -3待更换")
    private Integer flag;

    @ApiModelProperty("硬件描述")
    private String remark;

    @ApiModelProperty("创建人id")
    private Long createBy;

    @ApiModelProperty("创建人组id")
    private Long createGroup;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("更新人id")
    private Long updateBy;

    @ApiModelProperty("更新人组id")
    private Long updateGroup;

    @ApiModelProperty("更新时间")
    private Date updateTime;

    @ApiModelProperty("所属企业id")
    private Long companyId;

    @ApiModelProperty("所属企业名称")
    private String companyName;

    @ApiModelProperty("第三方设备id  调用Iot平台时使用")
    private String deviceId;
    @ApiModelProperty("第三方位号id 调用Iot平台时使用")
    private String pointId;
}
