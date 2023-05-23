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
@TableName("sa_agro_smartdevice_log")
@ApiModel("智能设备操作日志")
public class SmartDeviceLog  implements Serializable {

    @TableId
    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("智能设备id")
    private Long deviceId;

    @ApiModelProperty("智能设备名称")
    private String deviceName;

    @ApiModelProperty("所属农业设施id")
    private Long facilitiesId;

    @ApiModelProperty("所属农业设施名称")
    private String facilitiesName;

    @ApiModelProperty("操作人id 就是用户id")
    private Long userId;

    @ApiModelProperty("操作指令")
    private String code;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty("操作时间")
    private Date sendTime;

    @ApiModelProperty("所属企业id")
    private Long companyId;

    @ApiModelProperty("所属企业名称")
    private String companyName;
}
