package com.mumu.servlet;

import com.mumu.factory.ServiceFactory;
import com.mumu.utils.servlet.DisptcherServlet;
import com.mumu.utils.sms.MessageUtils;
import com.mumu.vo.Member;

import javax.servlet.annotation.WebServlet;

/**
 * @author Huangxulin
 * @date 2019/9/4 - 15:22
 */
@WebServlet("/RegisterServlet/*")
public class RegisterServlet extends DisptcherServlet {
    public void sendSms() throws Exception {
        String phone = super.request.getParameter("phone");
        String[] phoneNumbers = {phone}; //保存手机号码
        int code = (int) ((Math.random() * 9 + 1) * 1000);//随机生成验证码
        String[] params = {code + ""};

        MessageUtils.sendMessage(phoneNumbers, params); //调用发送短信的方法
        super.request.getSession().setAttribute("phoneandcode", phone + code);
        super.response.getWriter().print("success");
    }

    public void register() throws Exception {
        String phone = super.request.getParameter("phone");
        String password = super.request.getParameter("password");
        String code = super.request.getParameter("code");
        String phonecode = phone + code;
        Member vo = new Member();
        vo.setPhone(phone);
        vo.setPassword(password);
        if (super.request.getSession().getAttribute("phoneandcode").equals(phonecode)) { //验证通过
            if (ServiceFactory.getIMemberServiceIntance().insert(vo)) {
                super.response.getWriter().print("success");
            } else {
                super.response.getWriter().print("error");//增加失败
            }
        } else { // 验证失败
            super.response.getWriter().print("error");
        }
    }
}
