package com.lxl.agro.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

//通用返回结果，服务端响应的数据最终都会封装成此对象
@Data
@ApiModel("返回结果")
public class ResultInfo implements Serializable {
    @ApiModelProperty("响应编码")
    private Integer code; //编码：1成功，0和其它数字为失败

    @ApiModelProperty("错误信息")
    private String msg; //错误信息

    @ApiModelProperty("成功数据")
    private Object data; //数据

    //成功结果
    public static ResultInfo success(Object object) {
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.data = object;
        resultInfo.code = 1;
        return resultInfo;
    }

    //失败结果
    public static ResultInfo error(String msg) {
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.msg = msg;
        resultInfo.code = 0;
        return resultInfo;
    }

}
