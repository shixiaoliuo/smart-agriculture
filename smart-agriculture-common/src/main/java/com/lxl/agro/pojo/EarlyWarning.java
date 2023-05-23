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
@TableName("sa_agro_earlywarning")
@ApiModel("预警信息")
public class EarlyWarning  implements Serializable {

    @TableId
    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("所属企业id")
    private Long companyId;

    @ApiModelProperty("所属企业名称")
    private String companyName;

    @ApiModelProperty("所属农业设施id")
    private Long facilitiesId;

    @ApiModelProperty("所属农业设施名称")
    private String facilitiesName;


    @ApiModelProperty("所属硬件设备id")
    private Long deviceId;

    @ApiModelProperty("所属硬件设备名称")
    private String deviceName;



    @ApiModelProperty("预警内容")
    private String waringMessage;

    @ApiModelProperty("联系人")
    private String linkman;
    @ApiModelProperty("联系人电话")
    private String linkmanTel;
    @ApiModelProperty("是否发送短信")
    private Integer isSend;
    @ApiModelProperty("发送结果码")
    private  String sendResultCode;
    @ApiModelProperty("发送结果信息")
    private String sendResultMsg;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date sendTime;
/*
    @ApiModelProperty("通知人id 就是系统用户Id")
    private Long notifierId;


    @ApiModelProperty("通知人名称")
    private String notifierName;

    @ApiModelProperty("通知人电话")
    private String telephone;
*/

    @ApiModelProperty("处理结果")
    private String processMessage;

    @ApiModelProperty("处理人id 就是系统用户id")
    private Long processorId;

    @ApiModelProperty("处理人名称")
    private String processorName;

    @ApiModelProperty("状态 0未处理  1已处理")
    private Integer state;




}
