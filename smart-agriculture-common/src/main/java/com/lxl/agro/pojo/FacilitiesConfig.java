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
@TableName("sa_agro_facilities_config")
@ApiModel("农业设施配置")
public class FacilitiesConfig  implements Serializable {

    @TableId
    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("农业设施id")
    private Long facilitiesId;

    @ApiModelProperty("农业设施名称")
    private String facilitiesName;

    @ApiModelProperty("温度阈值下限")
    private Integer temperatureLow;

    @ApiModelProperty("温度阈值上限")
    private Integer temperatureHigh;

    @ApiModelProperty("湿度阈值下限")
    private Integer humidityLow;

    @ApiModelProperty("湿度阈值上限")
    private Integer humidityHigh;

    @ApiModelProperty("土壤酸碱度下限")
    private Integer phLow;

    @ApiModelProperty("土壤酸碱度上限")
    private Integer phHigh;

    @ApiModelProperty("土壤溶液盐度下限")
    private Integer ecLow;

    @ApiModelProperty("土壤溶液盐度上限")
    private Integer ecHigh;

    @ApiModelProperty("光照度")
    private Double illuminanceDegree;

    @ApiModelProperty("虫情")
    private Double entomopathyThreshold;


}
