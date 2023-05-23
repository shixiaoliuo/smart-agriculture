package com.lxl.agro.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
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
 * @Author：xudeming
 * @Package：com.qf.agro.pojo
 * @Project：smart-agriculture-parent
 * @name：SmartDeviceDataLogs
 * @Date：2023/3/2 11:11
 * @Filename：SmartDeviceDataLogs
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName("sa_agro_smartdevice_data_logs")
@ApiModel("智能设备拉取的日志数据")
public class SmartDeviceDataLogs  implements Serializable {



    @TableId
    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("设备id")
    private Long deviceId;

    @Excel(name="设备名称",width = 30)
    @ApiModelProperty("设备名称")
    private String deviceName;

    @ApiModelProperty("硬件类型  不同的硬件类型上报的数据不一样")
    private Long typeId;
    @Excel(name="硬件类型",width = 20)
    @ApiModelProperty("硬件类型  不同的硬件类型上报的数据不一样")
    private String typeName;

    @ApiModelProperty("农业设施id")
    private Long facilitiesId;
    @Excel(name="农业设施名称",width = 30)
    @ApiModelProperty("农业设施名称")
    private String facilitiesName;



    @Excel(name="返回code")
    @ApiModelProperty("返回结果code")
    private String  resultCode;
    @Excel(name="返回结果说明",width = 10)
    @ApiModelProperty("返回结果说明")
    private String  resultMessage;

    @ApiModelProperty("返回结果")
    private String  resultData;

    @Excel(name="数据",width = 10)
    @ApiModelProperty("返回结果核心数据")
    private String  resultDataValue;
    @Excel(name="时间" ,exportFormat = "yyyy-MM-dd hh:mm:ss",width = 30)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;





}
