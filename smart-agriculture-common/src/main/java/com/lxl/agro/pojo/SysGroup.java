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
@TableName("sa_sys_group")
@ApiModel("系统组")
public class SysGroup  implements Serializable {

    @TableId
    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("组名称")
    private String name;

    @ApiModelProperty("组人数")
    private Integer total;

    @ApiModelProperty("组状态")
    private Integer state;

    @ApiModelProperty("组简介")
    private String remark;

    @ApiModelProperty("所属企业id")
    private Long companyId;

    @ApiModelProperty("所属企业名称")
    private String companyName;
}
