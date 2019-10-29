package com.java1234.controller;

import com.java1234.constanst.Constanst;
import com.java1234.entity.AccessToken;
import com.java1234.entity.WechatUserUnionID;
import com.java1234.service.WeixinLoginService;
import com.java1234.util.AesUtil;
import com.java1234.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 微信扫码登录Controller
 * @author java1234_小锋
 * @site www.java1234.com
 * @company Java知识分享网
 * @create 2019-02-25 下午 10:53
 */
@Controller
public class WeixinLoginController {

    @Autowired
    private WeixinLoginService weixinLoginService;

    /**
     * 重定向到微信扫码登录地址
     * @return
     */
    @GetMapping("/weixinLogin")
    public String weixinLogin(){
        String url = weixinLoginService.genLoginUrl();
        return "redirect:"+url;
    }

    /**
     * 回调地址处理
     * @param code
     * @param state
     * @return
     */
    @GetMapping(value = "/weixinconnect")
    public ModelAndView callback(String code, String state) {
        String access_token=null;
        String openid=null;
        ModelAndView mav=new ModelAndView();
        if (code != null && state != null) {
            // 验证state为了用于防止跨站请求伪造攻击
            String decrypt = AesUtil.decrypt(AesUtil.parseHexStr2Byte(state), AesUtil.PASSWORD_SECRET_KEY, 16);
            if (!decrypt.equals(Constanst.PWD_MD5 + DateUtil.getYYYYMMdd())) {
                mav.addObject("error","登录失败，请联系管理员！");
                mav.setViewName("loginError");
                return mav;
            }
            AccessToken access = weixinLoginService.getAccessToken(code);
            if (access != null) {
                // 把获取到的access_token和openId赋值给变量
                access_token=access.getAccess_token();
                openid=access.getOpenid();

                // 存在则把当前账号信息授权给扫码用户
                // 拿到openid获取微信用户的基本信息

                // 此处可以写业务逻辑

                WechatUserUnionID userUnionID = weixinLoginService.getUserUnionID(access_token,openid);
                mav.addObject("userInfo",userUnionID);
                mav.setViewName("main");
                return mav;

            }
        }
        return null;
    }


}
