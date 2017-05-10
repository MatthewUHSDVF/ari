package com.matthew.wechat.util;

import com.matthew.wechat.po.AccessToken;
import com.matthew.wechat.po.Menu;
import com.matthew.wechat.po.PushMsg;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;


/**
 * 公众平台通用接口工具类
 * Created by Administrator on 2017/4/20.
 */
public class WeixinUtil {
    private static Logger logger = LoggerFactory.getLogger(WeixinUtil.class);

    /***
     * 发起HTTPS请求并获取结果
     * @param requestUrl 请求地址
     * @param requestMethod 请求方式（get、post）
     * @param outputStr 提交的数据
     * @return JSONObject（通过JSONObject.get(key)的方式获取json对象的属性值）
     */
    public static JSONObject httpRequest(String requestUrl,String requestMethod,String outputStr){
        JSONObject jsonObject = null;
        StringBuffer buffer = new StringBuffer();
        try {
            //创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = {new MyX509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL","SunJSSE");

            sslContext.init(null,tm,new java.security.SecureRandom());
            //从上数SLLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
            httpUrlConn.setSSLSocketFactory(ssf);

            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            //设置请求方式(GET/POST)
            httpUrlConn.setRequestMethod(requestMethod);

            if ("GET".equalsIgnoreCase(requestMethod))
                httpUrlConn.connect();

            //当有数据需要提交时
            if (null != outputStr) {
                OutputStream outputStream = httpUrlConn.getOutputStream();
                //注意编码格式，防止中文乱码
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }

            //将返回的输入流转换成字符串
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null){
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            //释放资源
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();
            jsonObject = JSONObject.fromObject(buffer.toString());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    // 获取access_token的接口地址（GET） 限200（次/天）
    public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    /**
     * 获取access_token
     * @param appid 凭证
     * @param appsecret 密钥
     * @return
     */
    public static AccessToken getAccessToken(String appid,String appsecret){
        AccessToken accessToken = null;

        String requestUrl = access_token_url.replace("APPID",appid).replace("APPSECRET",appsecret);
        JSONObject jsonObject = httpRequest(requestUrl,"GET",null);
        //如果请求成功
        if (null != jsonObject) {
            try {
                accessToken = new AccessToken();
                accessToken.setToken(jsonObject.getString("access_token"));
                accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
            } catch (JSONException e) {
                accessToken = null;
                //获取token失败
                logger.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            }
        }
        return accessToken;
    }

    // 菜单创建（POST） 限100（次/天）
    public static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

    /**
     * 创建菜单
     * @param menu 菜单实例
     * @param accessToken 有效的access_token
     * @return 0表示成功，其他表示失败
     */
    public static int createMenu(Menu menu,String accessToken){
        int result = 0;

        //拼装创建菜单的url
        String url = menu_create_url.replace("ACCESS_TOKEN",accessToken);
        // 将菜单对象转换成json字符串
        String jsonMenu = JSONObject.fromObject(menu).toString();
        //调用接口创建菜单
        JSONObject jsonObject = httpRequest(url,"POST",jsonMenu);

        if (null != jsonObject) {
            if (0 != jsonObject.getInt("errcode")) {
                result = jsonObject.getInt("errcode");
                logger.error("创建菜单失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            }
        }
        return result;
        }

    // 消息推送（POST），前提条件：用户48小时内与本公众号有交互
    public static String push_message_url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";

    public static int pushMessage(PushMsg pushMsg, String accessToken){
        int result = 0;

        //拼装创建菜单的url
        String url = push_message_url.replace("ACCESS_TOKEN",accessToken);
        // 将推送对象转换成json字符串
        String jsonMsg = JSONObject.fromObject(pushMsg).toString();
        //调用接口推送消息
        JSONObject jsonObject = httpRequest(url,"POST",jsonMsg);
        if (null != jsonObject) {
            if (0 != jsonObject.getInt("errcode")) {
                result = jsonObject.getInt("errcode");
                logger.error("推送消息失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            }
        }
        return result;
    }

}

