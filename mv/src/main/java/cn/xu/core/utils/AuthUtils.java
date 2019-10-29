package cn.xu.core.utils;


import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.DefaultHttpClientConnectionOperator;
import org.apache.http.util.EntityUtils;
import java.io.IOException;

public class AuthUtils {

    //应用的ID
    public static final String CLIENT_ID = "db8fe6f7c371877b821d";
    //应用秘钥
    public static final String CLIENT_SECRET="813e80e48c8a086088e7215ef3c974fab6afc088";


    //将请求返回的结果转化为json
    public static JSONObject doGetJson(String url) throws IOException {
        JSONObject jsonObject = null;
        DefaultHttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        HttpResponse response =client.execute(httpGet);
        HttpEntity entity = response.getEntity();
        if(entity !=null){
            String result = EntityUtils.toString(entity,"utf-8");
            jsonObject = JSONObject.fromObject(result);
        }
        httpGet.releaseConnection();
        return jsonObject;
    }

    //将请求返回的结果转化为json
    public static String doGetStr(String url) throws IOException {
        String result = null;

        DefaultHttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        HttpResponse response =client.execute(httpGet);
        HttpEntity entity = response.getEntity();
        if(entity !=null){
            result = EntityUtils.toString(entity,"utf-8");
        }
        httpGet.releaseConnection();
        return result;
    }


}
