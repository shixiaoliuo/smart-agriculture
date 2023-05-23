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
 * @Author：xudeming
 * @Package：com.qf.agro.pojo
 * @Project：smart-agriculture-parent
 * @name：Crop
 * @Date：2023/3/1 15:04
 * @Filename：Crop
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName("sa_agro_crop")
@ApiModel("农作物")
public class Crop  implements Serializable {
    @TableId
    @ApiModelProperty("主键")
    private Long id;
    /**
     * 设施id 设施就是大棚
     */
    private Long facilitiesId;
    /**
     * 设施名字 设施就是大棚
     */
    private String facilitiesName;
    /**
     * 名字
     */
    private String name;
    /**
     * 产量
     */
    private String yield;
    /**
     * 种植面积
     */
    private String area;
    /**
     * 状态
     */
    private Integer status;
    private String userId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;


}
