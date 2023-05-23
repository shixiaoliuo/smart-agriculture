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
@ApiModel("智能设备分类")
@TableName("sa_agro_smartdevice_type")
public class SmartDeviceType  implements Serializable {

    @TableId
    @ApiModelProperty("主键")
    private Long id;
    @ApiModelProperty("分类名称")
    private String typeName;
    @ApiModelProperty("分类描述")
    private String remark;

}
