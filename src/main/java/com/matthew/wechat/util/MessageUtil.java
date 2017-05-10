package com.matthew.wechat.util;

import com.matthew.wechat.model.message.resp.MusicMessage;
import com.matthew.wechat.model.message.resp.NewsMessage;
import com.matthew.wechat.model.message.resp.TextMessage;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 消息处理工具
 * Created by Administrator on 2017/4/18.
 */
public class MessageUtil {

    /**
     * 返回消息类型:文本
     */
    public static final String RESP_MESSAGE_TYPE_TEXT = "text";

    /**
     * 返回消息类型:音乐
     */
    public static final String RESP_MESSAGE_TYPE_MUSIC = "music";

    /**
     * 返回消息类型:图文
     */
    public static final String RESP_MESSAGE_TYPE_NEWS = "news";

    /**
     * 请求消息类型:文本
     */
    public static final String REQ_MESSAGE_TYPE_TEXT = "text";

    /**
     * 请求消息类型:图片
     */
    public static final String REQ_MESSAGE_TYPE_IMAGE= "image";

    /**
     * 请求消息类型:链接
     */
    public static final String REQ_MESSAGE_TYPE_LINK = "link";

    /**
     * 请求消息类型:地理位置
     */
    public static final String REQ_MESSAGE_TYPE_LOCATION = "location";

    /**
     * 请求消息类型:音频
     */
    public static final String REQ_MESSAGE_TYPE_VOICE = "voice";

    /**
     * 返回消息类型:推送
     */
    public static final String REQ_MESSAGE_TYPE_EVENT = "event";

    /**
     * 事件类型:subscribe(订阅)
     */
    public static final String REQ_MESSAGE_TYPE_SUBSCRIBE = "subscribe";

    /**
     * 事件类型:unsubscribe(取消订阅)
     85
     */
    public static final String REQ_MESSAGE_TYPE_UNSUBSCRIBE = "unsubscribe";

    /**
     * 事件类型:CLICK(自定义菜单点击事件)
     */
    public static final String REQ_MESSAGE_TYPE_CLICK = "CLICK";


    /***
     * 解析微信发来的请求（XML）
     * @param request
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static Map<String,String> parseXml(HttpServletRequest request) throws Exception{
        //将解析结果存储在hashmap中
        Map<String,String> map = new HashMap<String, String>();

        //从request中取得输入流
        InputStream is = request.getInputStream();
        //读取输入流
        SAXReader reader = new SAXReader();
        Document document = reader.read(is);
        //得到xml根元素
        Element root = document.getRootElement();
        //得到根元素的所有子节点
        List<Element> elementList= root.elements();

        //遍历所有子节点
        for (Element e : elementList) {
            map.put(e.getName(),e.getText());
        }

        //释放资源
        is.close();
        is=null;
        return map;
    }

    /***
     * 将文本消息对象转换成XML
     * @param textMessage
     * @return
     */
    public static String textMessageToXml(TextMessage textMessage){

        XStream xstream = new XStream();                        //使用XStream将实体类的实例转换成xml格式
        xstream.alias("xml",textMessage.getClass());
        return xstream.toXML(textMessage);
    }

    /***
     * 将音乐消息对象转换成XML
     * @param musicMessage
     * @return
     */
    public static String musicMessageToXml(MusicMessage musicMessage){

        XStream xstream = new XStream();                        //使用XStream将实体类的实例转换成xml格式
        xstream.alias("xml",musicMessage.getClass());
        return xstream.toXML(musicMessage);
    }

    /***
     * 将图文消息对象转换成XML
     * @param newsMessage
     * @return
     */
    public static String newsMessageToXml(NewsMessage newsMessage){

        XStream xstream = new XStream();                        //使用XStream将实体类的实例转换成xml格式
        xstream.alias("xml",newsMessage.getClass());
        return xstream.toXML(newsMessage);
    }


    /***
     * 扩展 xstream，使其支持 CDATA 块
     * 说明：由于 xstream 框架本身并不支持 CDATA 块的生成，以下代码是对 xtream 做了
     * 扩展，使其支持在生成 xml 各元素值时添加 CDATA 块。
     */
    private static XStream xStream = new XStream(new XppDriver(){
        public HierarchicalStreamWriter createWriter(Writer out){
            return new PrettyPrintWriter(out){
                //对所有xml节点的转换都增加CDATA标记
                boolean cdata = true;

                @SuppressWarnings("unchecked")
                public void startNode(String name,Class clazz){
                    super.startNode(name,clazz);
                }

                protected void writeText(QuickWriter writer, String text){
                    if (cdata) {
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    } else {
                        writer.write(text);
                    }
                }
            };
        }
    });
}
