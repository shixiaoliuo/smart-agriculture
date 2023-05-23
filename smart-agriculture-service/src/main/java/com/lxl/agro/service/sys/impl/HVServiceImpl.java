package com.lxl.agro.service.sys.impl;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lxl.agro.common.ResultInfo;
import com.lxl.agro.mapper.*;
import com.lxl.agro.pojo.*;
import com.lxl.agro.service.sys.HVService;
import com.lxl.agro.util.EncryptUtil;
import com.lxl.agro.util.SendSMSUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Project : smart-agriculture-parent
 * Package : com.lxl.agro.service.sys.impl
 * Description : HVServiceImpl
 * Author : LiuXinLei
 * createDate : 2023/5/23 10:53
 */

@Service
public class HVServiceImpl implements HVService {

    private  final RestTemplate restTemplate;

    private  final SmartDeviceMapper smartDeviceMapper;


    private final SmartDeviceDataLogsMapper smartDeviceDataLogsMapper;

    private final FacilitiesMapper facilitiesMapper;

    private final EarlyWarningMapper earlyWarningMapper;

    private final FacilitiesConfigMapper facilitiesConfigMapper;


    @Value("${IoT.key}")
    private String access_key;
    @Value("${IoT.securit}")
    private String access_securit;
    @Value("${IoT.url}")
    private String IoTUrl;

    public HVServiceImpl(RestTemplate restTemplate, SmartDeviceMapper smartDeviceMapper, SmartDeviceDataLogsMapper smartDeviceDataLogsMapper, FacilitiesMapper facilitiesMapper, EarlyWarningMapper earlyWarningMapper, FacilitiesConfigMapper facilitiesConfigMapper) {
        this.restTemplate = restTemplate;
        this.smartDeviceMapper = smartDeviceMapper;
        this.smartDeviceDataLogsMapper = smartDeviceDataLogsMapper;
        this.facilitiesMapper = facilitiesMapper;
        this.earlyWarningMapper = earlyWarningMapper;
        this.facilitiesConfigMapper = facilitiesConfigMapper;
    }

    /**
     * <p>
     * 大屏项目实际显示设备的状态：
     * 1、每隔30s查询一次设备的光照
     * 2、每隔60s查询一次温湿度
     * <p>
     * 步骤：
     * 1、查询所有的typeId为5并且state为1的设备 -》typeId为5说明是光照传感器，state为1说明正在使用
     * 2、远程调用 IOT云平台
     * 2.1 调用成功
     * 2.1.1 判断状态是否为200
     * 200 ： 成功
     * !200: 失败  发送短信（查询大棚中的联系人电话）  添加报警日志表令牌  更新设备的状态为2 （flag异常）
     * 2.2 调用失败  异常中处理数据
     * 3、保存设备数据日志表信息
     *
     * @return
     */
    @Override
    public ResultInfo pullDataBySchedule() {
        //查询所有的typeId为1并且state为5的设备 -》typeId为5说明是光照传感器，state为1说明正在使用

        LambdaQueryWrapper<SmartDevice> wrapper = new LambdaQueryWrapper<>();
        //2.设置查询条件
        //typeId=5 是关照传感器
        wrapper.eq(SmartDevice::getTypeId, 5);
        wrapper.eq(SmartDevice::getState, 1);


        List<SmartDevice> smartDeviceList = smartDeviceMapper.selectList(wrapper);
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
                 *
                 * {"code":200,"data":{"id":"63feafdcba846c4271c56b84",
                 * "deviceId":"1630394579492286465","pointId":"1630400237604188162",
                 * "value":"20","rawValue":"20","originTime":"2023-03-01 09:52:28.838",
                 * "createTime":"2023-03-01 09:52:28.838"},"ok":true,"message":"ok"}
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
                    //查询成功，如果光照超出限制，发出预警信息
                    JSONObject jsonData = jsonObject.getJSONObject("data");

                    //value里面保存的是光照
                    String value = jsonData.getString("value");

                    //查询出当前大棚的温室度配置-》如果超出范围，发出预警短信

                    LambdaQueryWrapper<FacilitiesConfig> wrapper = new LambdaQueryWrapper<>();
                    wrapper.eq(StringUtils.isNotEmpty(smartDevice.getFacilitiesId() + ""), FacilitiesConfig::getFacilitiesId, smartDevice.getFacilitiesId());

                    FacilitiesConfig config = facilitiesConfigMapper.selectOne(wrapper);

                    System.out.println("config================" + config);
                    if (config != null) {
                        //当前光照
                        double HVMax = config.getIlluminanceDegree();
                        double currentHV = Double.valueOf(value);

                        System.out.println("========================当前光照度" + currentHV + "#==设置最高光照度" + HVMax);

                        if (currentHV > HVMax) {  //温度过高异常
                            //发送短信
                            ResultInfo resultInfo = SendSMSUtil.send(tel);
                            //记录预警信息
                            addWarnimgLog("光照过高异常,当前光照度：" + currentHV + ",设置最高光照" + HVMax, facilities, smartDevice, resultInfo);
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

