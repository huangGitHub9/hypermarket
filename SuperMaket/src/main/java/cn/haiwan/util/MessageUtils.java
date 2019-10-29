package cn.haiwan.util;

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
        int appid = 1400255658; //1400213186
        String appkey = "70de58df1d276845a56ec984f9449c30"; //e1f3fa165fa2acc2062c94eeb67fac72
        int templateId = 418776; // 表示短信的正文内容408830
        String smsSign = "海玩商城"; // 表示短信签名
        SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
        SmsSingleSenderResult result = ssender.sendWithParam("86",
                phoneNumbers[0], templateId, params, smsSign, "", "");// 签名参数未提供或者为空时，会使用默认签名发送短信
        System.out.println(result);
    }
}
