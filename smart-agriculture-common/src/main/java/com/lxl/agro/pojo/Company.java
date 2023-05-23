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
import org.springframework.format.annotation.DateTimeFormat;

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
@TableName("sa_sys_company")
@ApiModel("企业")
public class Company  implements Serializable {

    @TableId
    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("企业名称")
    private String name;

    @ApiModelProperty("过期时间")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private Date expirationDate;

    @ApiModelProperty("企业地址")
    private String address;

    @ApiModelProperty("营业执照")
    private String licenseId;

    @ApiModelProperty("企业法人")
    private String representative;

    @ApiModelProperty("联系方式")
    private String phone;

    @ApiModelProperty("企业规模")
    private String companySize;

    @ApiModelProperty("所属行业")
    private String industry;

    @ApiModelProperty("企业简介")
    private String remarks;

    @ApiModelProperty("企业状态 0 停用  1 启用")
    private Integer state;

    @ApiModelProperty("企业余额")
    private Double balance;

    @ApiModelProperty("所在城市")
    private String city;
}
