package com.mumu.utils.sms;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;

import java.io.IOException;

/**
 * @author Huangxulin
 * @date 2019/9/4 - 17:49
 */
public abstract class MessageUtils {
    public static void sendMessage(String[] phoneNumbers, String[] params) throws HTTPException, IOException {
        int appid = 1400213186;
        String appkey = "e1f3fa165fa2acc2062c94eeb67fac72";
        int templateId = 408830; // 表示短信的正文内容
        String smsSign = "欣茂Java学院"; // 表示短信签名
        SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
        SmsSingleSenderResult result = ssender.sendWithParam("86",
                phoneNumbers[0], templateId, params, smsSign, "", "");// 签名参数未提供或者为空时，会使用默认签名发送短信
        System.out.println(result);
    }
}
