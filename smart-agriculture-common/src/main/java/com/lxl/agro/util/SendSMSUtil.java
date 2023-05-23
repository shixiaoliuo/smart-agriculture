package com.lxl.agro.util;

import com.aliyun.tea.TeaException;
import com.lxl.agro.common.ResultInfo;

/**
 * @Author：xudeming
 * @Package：com.qf.agro.util
 * @Project：smart-agriculture-parent
 * @name：SendSMS
 * @Date：2023/3/2 14:35
 * @Filename：SendSMS
 */
public class SendSMSUtil {



    /*说明：此keyId和KeySecret是申请的阿里云测试签名和模板，只能给指定的电话号码发送短信,
        并且测试版本不能使用变量，其他的和正式的版本一样；
    */
    private static String accessKeyId="LTAI4FhSMvdBQWHFRpBvgLC2";
    private static String accessKeySecret="1P1aRcRg8aPmvxQ0jSL7bTcVf9mDAa";



    public static ResultInfo send(String tel)  {

//        com.aliyun.dysmsapi20170525.Client client = null;
//        try {
//            client = SendSMSUtil.createClient(accessKeyId, accessKeySecret);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        com.aliyun.dysmsapi20170525.models.SendSmsRequest sendSmsRequest = new com.aliyun.dysmsapi20170525.models.SendSmsRequest()
//                .setPhoneNumbers(tel)
//                .setSignName("农业智能云平台")
//                .setTemplateCode("SMS_271585869");
//        com.aliyun.teautil.models.RuntimeOptions runtime = new com.aliyun.teautil.models.RuntimeOptions();
        try {
            // 复制代码运行请自行打印 API 的返回值
            //client.sendSmsWithOptions(sendSmsRequest, runtime);
            return ResultInfo.success("发送成功");
        } catch (TeaException error) {
            // 如有需要，请打印 error
            com.aliyun.teautil.Common.assertAsString(error.message);
            return ResultInfo.error(error.message);
        } catch (Exception _error) {
            TeaException error = new TeaException(_error.getMessage(), _error);
            // 如有需要，请打印 error
            com.aliyun.teautil.Common.assertAsString(error.message);
            return ResultInfo.error(error.message);
        }


    }


    /**
     * 使用AK&SK初始化账号Client
     * @param accessKeyId
     * @param accessKeySecret
     * @return Client
     * @throws Exception
     */
    public static com.aliyun.dysmsapi20170525.Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config()
                // 必填，您的 AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 必填，您的 AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.endpoint = "dysmsapi.aliyuncs.com";
        return new com.aliyun.dysmsapi20170525.Client(config);
    }

    public static void main(String[] args_) throws Exception {
        java.util.List<String> args = java.util.Arrays.asList(args_);
        // 工程代码泄露可能会导致AccessKey泄露，并威胁账号下所有资源的安全性。以下代码示例仅供参考，建议使用更安全的 STS 方式，更多鉴权访问方式请参见：https://help.aliyun.com/document_detail/378657.html
        com.aliyun.dysmsapi20170525.Client client = SendSMSUtil.createClient(accessKeyId, accessKeySecret);
        com.aliyun.dysmsapi20170525.models.SendSmsRequest sendSmsRequest = new com.aliyun.dysmsapi20170525.models.SendSmsRequest()
                .setPhoneNumbers("13140133868")
                .setSignName("农业智能云平台")
                .setTemplateCode("SMS_271585869");
        com.aliyun.teautil.models.RuntimeOptions runtime = new com.aliyun.teautil.models.RuntimeOptions();
        try {
            // 复制代码运行请自行打印 API 的返回值
            client.sendSmsWithOptions(sendSmsRequest, runtime);
        } catch (TeaException error) {
            // 如有需要，请打印 error
            com.aliyun.teautil.Common.assertAsString(error.message);
        } catch (Exception _error) {
            TeaException error = new TeaException(_error.getMessage(), _error);
            // 如有需要，请打印 error
            com.aliyun.teautil.Common.assertAsString(error.message);
        }
    }
}
