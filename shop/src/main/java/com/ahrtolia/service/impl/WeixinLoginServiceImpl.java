package com.ahrtolia.service.impl;

import com.ahrtolia.entity.AccessToken;
import com.ahrtolia.entity.HttpParame;
import com.ahrtolia.entity.WechatUserUnionID;
import com.ahrtolia.service.WeixinLoginService;
import com.ahrtolia.util.HttpClientUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author java1234_小锋
 * @site www.java1234.com
 * @company Java知识分享网
 * @create 2019-02-25 下午 10:49
 */
@Service
public class WeixinLoginServiceImpl implements WeixinLoginService {

    @Override
    public AccessToken getAccessToken(String code) {
        String accessTokenUrl = HttpParame.ACCESS_TOKEN_URL;
        accessTokenUrl = accessTokenUrl.replaceAll("APPID", "wx7287a60bb700fd21");
        accessTokenUrl = accessTokenUrl.replaceAll("SECRET", "1ef8755f92bebae8ad7bab432ba29cbf");
        accessTokenUrl = accessTokenUrl.replaceAll("CODE", code);
        String responseContent = HttpClientUtil.getInstance().sendHttpGet(accessTokenUrl);
        if (responseContent == null || responseContent == "") {
            return null;
        }
        JSONObject parseObject = JSONObject.parseObject(responseContent);
        AccessToken accessToken = JSONObject.toJavaObject(parseObject, AccessToken.class);
        return accessToken;
    }

    @Override
    public WechatUserUnionID getUserUnionID(String access_token, String openid) {
        String unionIDUrl = HttpParame.GET_UNIONID_URL;
        unionIDUrl = unionIDUrl.replace("ACCESS_TOKEN", access_token);
        unionIDUrl = unionIDUrl.replace("OPENID", openid);
        String responseContent = HttpClientUtil.getInstance().sendHttpGet(unionIDUrl);
        System.out.println("responseContent:"+responseContent);
        if (responseContent == null || responseContent == "") {
            return null;
        }
        JSONObject parseObject = JSONObject.parseObject(responseContent);
        WechatUserUnionID userUnionID = JSONObject.toJavaObject(parseObject, WechatUserUnionID.class);
        return userUnionID;
    }
}
