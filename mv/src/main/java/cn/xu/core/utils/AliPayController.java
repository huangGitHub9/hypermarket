package cn.xu.core.utils;

import cn.xu.dygl.memPackage.service.MemPackageService;
import cn.xu.dygl.user.controller.UserController;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author xupenghao@163.com
 * @date 2018-05-17 19:50
 */
@Controller
@RequestMapping("/alipay")
public class AliPayController {

    @Autowired
    private MemPackageService memPackageService;

    @Autowired
    private UserController userController;

    @RequestMapping("/setRequestParam.action")
    public void setRequestParam(HttpServletRequest request,HttpServletResponse response){
        try {
            //根据id来从数据库中查询价钱
            String memPackageId = request.getParameter("memPackageId");
            String price = memPackageService.findObjectById(memPackageId).getMemPackagePrice()+"";
            Date date = new Date();
            String out_trade_no =""+date.getTime();
            String total_amount = price;
            String subject = URLEncoder.encode("充值会员", "utf-8");
            String url="/alipay/treadPay.action"+
                    "?WIDout_trade_no="+out_trade_no+
                    "&WIDtotal_amount="+total_amount+
                    "&WIDsubject="+subject+
                    "&WIDbody="+""+
                    "&memPackageId="+memPackageId;
             //去请求另一个连接
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //交易支付
    @RequestMapping("/treadPay.action")
    public void treadPay(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no =request.getParameter("WIDout_trade_no");
        //付款金额，必填
        String total_amount =request.getParameter("WIDtotal_amount");
        //订单名称，必填
        String subject = request.getParameter("WIDsubject");
        //商品描述，可空
        subject = URLDecoder.decode(subject, "utf-8");
        String body = new String(request.getParameter("WIDbody").getBytes("ISO-8859-1"),"UTF-8");


        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        //若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
        //alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
        //		+ "\"total_amount\":\""+ total_amount +"\","
        //		+ "\"subject\":\""+ subject +"\","
        //		+ "\"body\":\""+ body +"\","
        //		+ "\"timeout_express\":\"10m\","
        //		+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节

        //请求
        String result = "";
        try {
            result = alipayClient.pageExecute(alipayRequest).getBody();
        } catch (AlipayApiException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //输出
        response.getWriter().println(result);
    }

    //回调地址
    @RequestMapping("/returnUrl.action")
    public String returnUrl(HttpServletRequest request, Model model, HttpServletResponse response)
            throws Exception {
        System.out.println("我来了");
        PrintWriter out = response.getWriter();

        //获取支付宝GET过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        Boolean signVerified =null;
        try {
            signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type);
        } catch (AlipayApiException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } //调用SDK验证签名
        //——请在这里编写您的程序（以下代码仅作参考）——
        if(signVerified) {
            //rsa验证成功 则获取信息 本次信息不需要
            //根据价钱查id
            double price =  Double.parseDouble(params.get("total_amount"));
            String memPackageId =  memPackageService.findMemPByPrice(price);
            return userController.payVIP(memPackageId,price,model,request);
        }else {
            //out.println("验签失败");
            return "errorMsgUI";
        }
    }


}
