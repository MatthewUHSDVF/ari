//package com.matthew.menu;
//
//import com.pkh.weixin.util.HttpClientConnectionManager;
//import com.pkh.weixin.util.HttpClientUtils;
//import com.pkh.weixin.util.JsonUtils;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.util.EntityUtils;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * Created by Administrator on 2017/4/17.
// */
////设置微信后台标签
//public class MenuSet {
//        //正式环境
//        private static String appid = "wxffdaa6a7dbf1a892";
//        private static String appsecret = "2fa77078715987758fb057915979e8c9";
//        //测试环境
////	private static String appid = "wxea1a536f18a489bf";
////	private static String appsecret = "008296d3dc117e3363f942d9048dcc48";
//        public static DefaultHttpClient httpclient;
//        static {
//            httpclient = new DefaultHttpClient();
//            httpclient = (DefaultHttpClient) HttpClientConnectionManager
//                    .getSSLInstance(httpclient); // 接受任何证书的浏览器客户端
//        }
//
//        /**
//         * 创建菜单
//         */
//        public static String createMenu(String params, String accessToken)
//                throws Exception {
//            HttpPost httpost = HttpClientConnectionManager
//                    .getPostMethod("https://api.weixin.qq.com/cgi-bin/menu/create?access_token="
//                            + accessToken);
//            httpost.setEntity(new StringEntity(params, "UTF-8"));
//            HttpResponse response = httpclient.execute(httpost);
//            String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");
//
//            return (String) JsonUtils.getMapFromJson(jsonStr).get("errmsg");
//        }
//
//        /**
//         * 查询菜单
//         */
//        public static String getMenuInfo(String accessToken) throws Exception {
//            HttpGet get = HttpClientConnectionManager
//                    .getGetMethod("https://api.weixin.qq.com/cgi-bin/menu/get?access_token="
//                            + accessToken);
//            HttpResponse response = httpclient.execute(get);
//            String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");
//            return jsonStr;
//        }
//
//        /**
//         * 删除菜单
//         */
//        public static String deleteMenuInfo(String accessToken) throws Exception {
//            HttpGet get = HttpClientConnectionManager
//                    .getGetMethod("https://api.weixin.qq.com/cgi-bin/menu/delete?access_token="
//                            + accessToken);
//            HttpResponse response = httpclient.execute(get);
//            String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");
//
//            return (String) JsonUtils.getMapFromJson(jsonStr).get("errmsg");
//        }
//        public static String userGet( String accessToken)
//                throws Exception {
//            Map<String, String> params = new HashMap<String, String>();
//            params.put("access_token", accessToken);
//            String json = HttpClientUtils.doGet(
//                    "https://api.weixin.qq.com/cgi-bin/user/get", params);
//            System.out.println(json);
//            return json;
//        }
//        public static String userInfo( String accessToken)
//                throws Exception {
//            Map<String, String> params = new HashMap<String, String>();
//            params.put("access_token", accessToken);
//            params.put("openid","oJmUCs8gO85Y7NjwZHilZ-_sTbtY");
//            params.put("lang","zh_CN");
//            String json = HttpClientUtils.doGet(
//                    "https://api.weixin.qq.com/cgi-bin/user/info", params);
//            System.out.println(json);
//            return json;
//        }
//        public static String getAccess_token() {
//            Map<String, String> params = new HashMap<String, String>();
//            params.put("grant_type", "client_credential");
//            params.put("appid", appid);
//            params.put("secret", appsecret);
//            String json = HttpClientUtils.doGet(
//                    "https://api.weixin.qq.com/cgi-bin/token", params);
//            System.out.println(json);
//            return json;
//        }
//
//        public static boolean createView(String params, String accessToken) {
//            return true;
//        }
//
//        public static void setMenu() {
//            //正式环境
//            String menu = "{\"button\":[{\"name\":\"我的普康宝\",\"sub_button\":["
//                    + "{\"type\":\"view\",\"name\":\"账户登录\",\"url\":\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxffdaa6a7dbf1a892&redirect_uri=http://www.pukangpay.com.cn/PKWeixin/bind_card&response_type=code&scope=snsapi_base&state=1#wechat_redirect\"}"
//                    + ",{\"name\":\"理赔限额查询\",\"type\":\"view\",\"url\":\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxffdaa6a7dbf1a892&redirect_uri=http://www.pukangpay.com.cn/PKWeixin/get_bal&response_type=code&scope=snsapi_base&state=1#wechat_redirect\"}"
//                    + ",{\"name\":\"直付记录查询\",\"type\":\"view\",\"url\":\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxffdaa6a7dbf1a892&redirect_uri=http://www.pukangpay.com.cn/PKWeixin/transDetail&response_type=code&scope=snsapi_base&state=1#wechat_redirect\"}"
//                    + ",{\"type\":\"view\",\"name\":\"修改密码\",\"url\":\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxffdaa6a7dbf1a892&redirect_uri=http://www.pukangpay.com.cn/PKWeixin/change_password&response_type=code&scope=snsapi_base&state=1#wechat_redirect\"}"
//                    + ",{\"name\":\"绑定手机\",\"type\":\"view\",\"url\":\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxffdaa6a7dbf1a892&redirect_uri=http://www.pukangpay.com.cn/PKWeixin/bind_mobile&response_type=code&scope=snsapi_base&state=1#wechat_redirect\"}"
//                    + "]},"
//                    + "{\"name\":\"我要健康\",\"sub_button\":["
//                    + "{\"name\":\"合作商户\",\"type\":\"view\",\"url\":\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxffdaa6a7dbf1a892&redirect_uri=http://www.pukangpay.com.cn/PKWeixin/store_list&response_type=code&scope=snsapi_base&state=1#wechat_redirect\"}"
////				+ ",{\"name\":\"附近药店\",\"type\":\"view\",\"url\":\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxffdaa6a7dbf1a892&redirect_uri=http://www.pukangpay.com.cn/PKWeixin/store_lbs&response_type=code&scope=snsapi_base&state=1#wechat_redirect\"}"
//                    + ",{\"name\":\"热线电话\",\"type\":\"click\",\"key\":\"hotline\"}"
//                    + "]},"
//                    + "{\"name\":\"普康惠不停\",\"sub_button\":["
//                    + "{\"name\":\"普康惠不停\",\"type\":\"view\",\"url\":\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxffdaa6a7dbf1a892&redirect_uri=http://www.pukangpay.com.cn/PKWeixin/pkPreferential&response_type=code&scope=snsapi_base&state=1#wechat_redirect\"}"
//                    + ",{\"name\":\"帮助\",\"type\":\"view\",\"url\":\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxffdaa6a7dbf1a892&redirect_uri=http://www.pukangpay.com.cn/PKWeixin/user_manual&response_type=code&scope=snsapi_base&state=1#wechat_redirect\"}"
//                    + "]}]}";
//
//            //开发测试:
////		String menu = "{\"button\":[{\"name\":\"我的普康宝\",\"sub_button\":["
////				+ "{\"type\":\"view\",\"name\":\"账户登录\",\"url\":\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxea1a536f18a489bf&redirect_uri=http://docheer.com/PKWeixin/bind_card&response_type=code&scope=snsapi_base&state=1#wechat_redirect\"}"
////				+ ",{\"name\":\"理赔限额查询\",\"type\":\"view\",\"url\":\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxea1a536f18a489bf&redirect_uri=http://docheer.com/PKWeixin/get_bal&response_type=code&scope=snsapi_base&state=1#wechat_redirect\"}"
////				+ ",{\"name\":\"直付记录查询\",\"type\":\"view\",\"url\":\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxea1a536f18a489bf&redirect_uri=http://docheer.com/PKWeixin/transDetail&response_type=code&scope=snsapi_base&state=1#wechat_redirect\"}"
////				+ ",{\"type\":\"view\",\"name\":\"修改密码\",\"url\":\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxea1a536f18a489bf&redirect_uri=http://docheer.com/PKWeixin/change_password&response_type=code&scope=snsapi_base&state=1#wechat_redirect\"}"
////				+ ",{\"name\":\"绑定手机\",\"type\":\"view\",\"url\":\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxea1a536f18a489bf&redirect_uri=http://docheer.com/PKWeixin/bind_mobile&response_type=code&scope=snsapi_base&state=1#wechat_redirect\"}"
////				+ "]},"
////				+ "{\"name\":\"我要健康\",\"sub_button\":["
////				+ "{\"name\":\"合作商户\",\"type\":\"view\",\"url\":\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxea1a536f18a489bf&redirect_uri=http://docheer.com/PKWeixin/store_list&response_type=code&scope=snsapi_base&state=1#wechat_redirect\"}"
//////				+ ",{\"name\":\"附近药店\",\"type\":\"view\",\"url\":\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxcc694c8589791dcf&redirect_uri=http://www.pukangpay.com.cn/PKWeixin/store_lbs&response_type=code&scope=snsapi_base&state=1#wechat_redirect\"}"
////				+ ",{\"name\":\"热线电话\",\"type\":\"click\",\"key\":\"hotline\"}"
////				+ "]},"
////				+ "{\"name\":\"普康惠不停\",\"sub_button\":["
////				+ "{\"name\":\"普康惠不停\",\"type\":\"view\",\"url\":\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxea1a536f18a489bf&redirect_uri=http://docheer.com/PKWeixin/pkPreferential&response_type=code&scope=snsapi_base&state=1#wechat_redirect\"}"
////				+ ",{\"name\":\"帮助\",\"type\":\"view\",\"url\":\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxea1a536f18a489bf&redirect_uri=http://docheer.com/PKWeixin/user_manual&response_type=code&scope=snsapi_base&state=1#wechat_redirect\"}"
////				+ "]}]}";
//            Map map = JsonUtils.getMapFromJson(getAccess_token());
//            String access_token = (String) map.get("access_token");
//            System.out.println(access_token);
//            try {
//                System.out.println(createMenu(menu, access_token));
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//        public static void getUserList(){
//            Map map = JsonUtils.getMapFromJson(getAccess_token());
//            String access_token = (String) map.get("access_token");
//            try {
//                System.out.println(userGet(access_token));
//            } catch (Exception e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }
//        public static void getUserInfo(){
//            Map map = JsonUtils.getMapFromJson(getAccess_token());
//            String access_token = (String) map.get("access_token");
//            try {
//                System.out.println(userInfo(access_token));
//            } catch (Exception e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }
//
//        public static void main(String[] args) {
////		String host = "https://jisukdcx.market.alicloudapi.com";
////		String path = "/express/query";
////		String method = "GET";
////		Map<String, String> headers = new HashMap<String, String>();
////		//最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
////		headers.put("Authorization", "APPCODE 9635e58b0488487fb2557694e817a8e5");
////		Map<String, String> querys = new HashMap<String, String>();
////		querys.put("number", "402152241638");
////		querys.put("type", "STO");
////		try {
////			/**
////			 * 重要提示如下:
////			 * HttpUtils请从
////			 * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
////			 * 下载
////			 *
////			 * 相应的依赖请参照
////			 * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
////			 */
////			HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
////			System.out.println(response.toString());
////			//获取response的body
////			System.out.println(EntityUtils.toString(response.getEntity()));
//            setMenu();
////		} catch (Exception e) {
////			e.printStackTrace();
////		}
//        }
//
//}
