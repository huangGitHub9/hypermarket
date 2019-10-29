package cn.xu.core.utils;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @author xupenghao@163.com
 * @date 2018-05-17 19:47
 */
public class AlipayConfig {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016091100485474";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC58RA3jRU3DoRGELmc9d3qPbV7TQsqcRA9Fpc1JqqEei+X6O2kWqsE7DJQQqyyxRZ3onJwdwf8xIqRvsD4WymkkCeUtxZOblm8qQ35iaxMungX41mmxI62WJzD6NvqwT4SOlrU08bv1oRHdkIB2ngYWxiN0a+/poEYIEJQiZYjcNf+eSY8HGDXLpGfARUQj40OXRGwS1RZNtwg2udz4Dc9OONl7tTObs7wD8RCyu/noYTN75vdgmA+DpKYdPRZl/HyxqzFYhZkapclUHLN8CLNh1NjSqJC9Wpdp6SqSQJI0gAFDcvlylqnibcpI8iE6/hsUKbi73onRqTfgXJP/HG/AgMBAAECggEAXAKr0bFgpCPs1HcGQmNv8q+ilGcldVUJghEhtc1yZ6fG0VcT8VZkdkUjCJBVwQjB+2VJV9mu/CeNol1QRBVoX+RtkaufwCPfB/pnzemQd092ieuYu2tca2Mh/9fEq0AApSh3NgWt1ZTDVFFyjFQ9RWAeoF74ps+P5g5gglXzE4m1YD0FXD4TaXAPKIw7weid66W/MU0qxFmxTopSACWl8OOLJ3k/o1dD3t3xfzp5OFqYdlY75IQZ4PlvC4iAqlPd8U9JTmOvmqgl9x9zz1WwkxYaQQSfFN7oB0+GqkGA7SwqNSkPgxKuH9TuqW8KuAkORYkqYPUgSxOQew0b+TwQAQKBgQDi9D2B3K8B/GgdHc5spiCHX1phHQTsxUkCt4cma0/J2/cPq4vn3NRNcjxevZ17QGIEHcrMfO2Bo6fsRTG09LORbYBL+MF/FrawaGWWFs7A8jldzU37aCHYxPwnK50tVlJIl69xRwo7R6Rq4B43NTOcdAsZyMWgNLgb7UMPS9MkAQKBgQDRvR86JGKXFDcxAb9TQUWUl9oz/C603PnRtIMIzSQ3dc0bba+VJJNMCGWvFRWEi47AUk+kBJX0ro08LQcwZ6aY16VukaLVPI8gYpmIFhUdpaLjKxyvPf1yYc+ErhSvdik0OD+L7K2D2CzMGc144QyRuQet5IEl9uI+4W7Q2YCVvwKBgGlaxh69qkQ6vr4dcxD+wYT2H8eLKBkdsDnhpoyQLlbflL4ifWziF2/oQtwcG7n2dknA5r6sk7illzNYH64IFj/BXFhoHrL3gVE89UOKTOpGx5ii0LuPaH7vWrp4RZuhQctPFvN37ej85fdzGLO6L75GJE0Q5aTsNDdKmeHE6jwBAoGBALFeHBBRQMa+o8Xf0mqXoj39BNw9gUoWLXW4D6/bu6rjBicR5RBdM+tylnsve1jxpDDj6tGrxxx6mJmxAolevjY294G/y1JEuk0K1QeOlSDTOCKgDMvtwm1+Zb2wcXHInSO6BZNQ/ZOntoqLr4Vv2+9H6ESYHtd6Ju5COjKIHQOFAoGAEHtE2TZq+eejj8nNXWrVEOJPDIpOnNG/0RYaqF7unlh7HilFFit8ccEdBlSUilRxXRyMPtaW8Q4fnyhBcJxFr/TSOV0TWvv5lczx8uEPaGVJuEKZ8cmvnN2nK/4lGIjQvTIWEX1+hDJ1JDDtbRnAzMiXuzdpP/z5h9A+58Yg9Xk=";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqEz4pkYAEN2l47tc0VUYaKyxLb2ZOVyQfzKQnFz48KnCKviu/nF3SeDd9DsCzjFQjDudJ2IdHiQIIOhQkbdT1ekDxDveGAaSFtREF9+UutULXcKLwlhonMka8Hv7Jot9a35D/gGC1nYxhF+KMoVNkPnXRJ+t4u+CaaSYdwq3MG6teI18Q0RijWnB/j91afUe5o/nkbcHEdJcWmcQL1ILaXy3b+1ZZbZnQgvx2qt5BKwfjxlgEkyM7jYm6QDmTiIFoXqIOjR5WkkvR8SvngqpNqJyPYWwvtVpTvn4ihRLeGYyTK+QLamjdqKJwlKuo0Jerojnx1IRJoyaT6NGOsjz5wIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://工程公网访问地址/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

    // 页面跳转同步通知页面路径 需http:/-/格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://localhost:8080/dyglxt/alipay/returnUrl.action";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
