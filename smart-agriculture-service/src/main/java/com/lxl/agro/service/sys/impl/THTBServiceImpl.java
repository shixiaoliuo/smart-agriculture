package com.lxl.agro.service.sys.impl;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lxl.agro.common.ResultInfo;
import com.lxl.agro.mapper.*;
import com.lxl.agro.pojo.*;
import com.lxl.agro.service.sys.THTBService;
import com.lxl.agro.util.EncryptUtil;
import com.lxl.agro.util.SendSMSUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class THTBServiceImpl implements THTBService {

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private SmartDeviceMapper smartDeviceMapper;


    @Resource
    private SmartDeviceDataLogsMapper smartDeviceDataLogsMapper;

    @Resource
    private FacilitiesMapper facilitiesMapper;

    @Resource
    private EarlyWarningMapper earlyWarningMapper;

    @Resource
    private FacilitiesConfigMapper facilitiesConfigMapper;


    @Value("${IoT.key}")
    private String access_key;
    @Value("${IoT.securit}")
    private String access_securit;
    @Value("${IoT.url}")
    private String IoTUrl;


    @Override
    public ResultInfo pullDataBySchedule() {
        //查询所有的typeId为1并且state为1的设备 -》typeId为1说明说温湿度传感器，state为1说明正在使用

        LambdaQueryWrapper<SmartDevice> wrapper = new LambdaQueryWrapper<>();
        //2.设置查询条件
        wrapper.eq(SmartDevice::getTypeId, 1);
        wrapper.eq(SmartDevice::getState, 1);

        List<SmartDevice> smartDeviceList = smartDeviceMapper.selectList(wrapper);
        //获取数据
        getData(smartDeviceList);
        return null;
    }


    public void getData(List<SmartDevice> smartDeviceList) {
        for (SmartDevice smartDevice : smartDeviceList) {

            //请求地址
            String requestUrl = IoTUrl + "/data/point_value/one";

            String deviceId = smartDevice.getDeviceId();
            String pointId = smartDevice.getPointId();


            Map<String, Object> paramMap = new HashMap<>();

            paramMap.put("deviceId", deviceId);
            paramMap.put("pointId", pointId);

            //添加日志
            SmartDeviceDataLogs deviceDataLogs = new SmartDeviceDataLogs();
            deviceDataLogs.setDeviceId(smartDevice.getId());
            deviceDataLogs.setDeviceName(smartDevice.getDeviceName());
            deviceDataLogs.setTypeId(smartDevice.getTypeId());
            deviceDataLogs.setTypeName(smartDevice.getTypeName());
            deviceDataLogs.setFacilitiesId(smartDevice.getFacilitiesId());
            deviceDataLogs.setFacilitiesName(smartDevice.getFacilitiesName());


            //查询设备对应的设施信息
            Facilities facilities = facilitiesMapper.selectById(smartDevice.getFacilitiesId());
            String tel = facilities.getLinkmanTel();
            if (tel == null) {
                tel = "13140133868";
            }

            try {
                //远程调用
                HttpHeaders requestHeaders = new HttpHeaders();
                requestHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

                requestHeaders.set("access_key", access_key);
                requestHeaders.set("access_securit", access_securit);

                String encrypt = EncryptUtil.encrypt(access_key + access_securit);
                requestHeaders.set("sign", encrypt);

                HttpEntity<Map<String, Object>> mapHttpEntity = new HttpEntity<>(paramMap, requestHeaders);
                ResponseEntity<JSONObject> resultObjectResponseEntity = restTemplate.exchange(requestUrl, HttpMethod.POST, mapHttpEntity, JSONObject.class);
                System.out.println(resultObjectResponseEntity.getBody());
                JSONObject jsonObject = resultObjectResponseEntity.getBody();

                /**
                 * {"code":200,
                 * "data":{"id":"63ef141543f5052a93c1bc12","deviceId":"1626098870559473666","pointId":"1626099160188747777",
                 * "value":"T=20,H=28","rawValue":"T=20,H=28","originTime":"2023-02-17 13:43:49.287","createTime":"2023-02-17 13:43:49.287"},"ok":true,"message":"ok"}
                 *
                 *
                 */

                System.out.println("调用返回结果：" + jsonObject);
                System.out.println("调用返回data数据：" + jsonObject.getJSONObject("data"));


                Integer code = jsonObject.getInteger("code");

                deviceDataLogs.setResultCode(code + "");
                deviceDataLogs.setResultMessage(jsonObject.getString("message"));
                deviceDataLogs.setResultData(jsonObject.getJSONObject("data").toString());
                deviceDataLogs.setResultDataValue(jsonObject.getJSONObject("data").getString("value"));

                if (code != 200) {
                    //发送短信提醒
                    ResultInfo resultInfo = null;
                    if (facilities.getLinkmanTel() != null) {
                        resultInfo = SendSMSUtil.send(facilities.getLinkmanTel());
                    }

                    //记录预警信息
                    addWarnimgLog("接口调用异常", facilities, smartDevice, resultInfo);

                    //设备状态异常->修改设备使用状态
                    smartDeviceMapper.updateFlagById(2, smartDevice.getId());

                } else if (code == 200) {

                    //设备状态异常->修改设备使用状态
                    smartDeviceMapper.updateFlagById(1, smartDevice.getId());

                    //设备状态正常
                    //查询成功，如果温湿度超出限制，发出预警信息
                    JSONObject jsonData = jsonObject.getJSONObject("data");

                    //value里面保存的是温度和湿度
                    String value = jsonData.getString("value");
                    //T=20,H=28

                    //把温度和湿度拆分出来
                    String T = value.split(",")[0].split("=")[1];
                    String H = value.split(",")[1].split("=")[1];

                    //查询出当前大棚的温室度配置-》如果超出范围，发出预警短信

                    LambdaQueryWrapper<FacilitiesConfig> wrapper = new LambdaQueryWrapper<>();
                    wrapper.eq(StringUtils.isNotEmpty(smartDevice.getFacilitiesId() + ""), FacilitiesConfig::getFacilitiesId, smartDevice.getFacilitiesId());

                    FacilitiesConfig config = facilitiesConfigMapper.selectOne(wrapper);

                    System.out.println("config================" + config);
                    if (config != null) {
                        Integer tH = config.getTemperatureHigh();
                        Integer tL = config.getTemperatureLow();

                        Integer currentT = Integer.valueOf(T);

                        Integer hH = config.getHumidityHigh();
                        Integer hL = config.getHumidityLow();
                        Integer currentH = Integer.valueOf(H);


                        System.out.println("========================当前温度" + currentT + "#==设置最高温度" + tH);
                        System.out.println("========================当前湿度" + currentH + "#==设置最高温度" + hH);
                        if (currentT > tH) {  //温度过高异常
                            //发送短信
                            ResultInfo resultInfo = SendSMSUtil.send(tel);
                            //记录预警信息
                            addWarnimgLog("温度过高异常,当前温度：" + currentT + ",设置最高温度" + tH, facilities, smartDevice, resultInfo);
                        }
                        if (currentT < tL) { //温度过低异常
                            //发送短信
                            ResultInfo resultInfo = SendSMSUtil.send(tel);
                            //记录预警信息
                            addWarnimgLog("温度过低异常,当前温度：" + currentT + ",设置最低温度" + tL, facilities, smartDevice, resultInfo);
                        }
                        if (currentH > hH) {  //湿度过高异常
                            //发送短信
                            ResultInfo resultInfo = SendSMSUtil.send(tel);
                            //记录预警信息
                            addWarnimgLog("湿度过高异常，当前湿度：" + currentH + ",设置最高湿度" + hH, facilities, smartDevice, resultInfo);
                        }
                        if (currentH < hL) { //湿度过低异常
                            //发送短信
                            ResultInfo resultInfo = SendSMSUtil.send(tel);
                            //记录预警信息
                            addWarnimgLog("湿度过低异常，当前湿度：" + currentH + ",设置最低湿度" + hL, facilities, smartDevice, resultInfo);
                        }
                    }
                }

            } catch (Exception e) {
                deviceDataLogs.setResultCode(500 + "");
                deviceDataLogs.setResultMessage(e.getMessage());
                //发送短信
                ResultInfo resultInfo = SendSMSUtil.send(facilities.getLinkmanTel());
                //记录预警信息
                addWarnimgLog("接口异常", facilities, smartDevice, resultInfo);

                //设备状态异常->修改设备使用状态
                smartDeviceMapper.updateFlagById(2, smartDevice.getId());

            }

            smartDeviceDataLogsMapper.insert(deviceDataLogs);

        }
    }


    public ResultInfo addWarnimgLog(String warningMsg, Facilities facilities, SmartDevice smartDevice, ResultInfo info) {

        EarlyWarning earlyWarning = new EarlyWarning();

        earlyWarning.setWaringMessage(warningMsg);
        earlyWarning.setDeviceId(smartDevice.getId());
        earlyWarning.setDeviceName(smartDevice.getDeviceName());
        earlyWarning.setFacilitiesId(smartDevice.getFacilitiesId());
        earlyWarning.setFacilitiesName(smartDevice.getFacilitiesName());


        earlyWarning.setCompanyId(facilities.getCompanyId());
        earlyWarning.setCompanyName(facilities.getCompanyName());
        earlyWarning.setLinkman(facilities.getLinkman());
        earlyWarning.setLinkmanTel(facilities.getLinkmanTel());


        System.out.println("info======" + info);
        if (info != null) {
            earlyWarning.setIsSend(1);
            earlyWarning.setSendResultCode(info.getCode() + "");
            earlyWarning.setSendResultMsg(info.getMsg());
            earlyWarning.setSendTime(new Date());
        } else {
            earlyWarning.setIsSend(0);
            earlyWarning.setSendResultCode(201 + "");
            earlyWarning.setSendResultMsg("电话号码不存在");
            earlyWarning.setSendTime(new Date());
        }

        earlyWarningMapper.insert(earlyWarning);
        return ResultInfo.success("添加成功");
    }


}
