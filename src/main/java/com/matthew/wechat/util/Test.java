package com.matthew.wechat.util;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * Created by Administrator on 2017/4/21.
 */
public class Test {
    public static void test(String orderTranscontent){
        orderTranscontent = "<ap>\n" +
                "    <drugNum>2</drugNum>\n" +
                "    <drug1>\n" +
                "        <localCode>111</localCode>\n" +
                "        <appAmt>11111</appAmt>\n" +
                "    </drug1>\n" +
                "    <drug2>\n" +
                "        <localCode>222</localCode>\n" +
                "        <appAmt>2222</appAmt>\n" +
                "    </drug2>\n" +
                "</ap>";
        try {
            Document document = DocumentHelper.parseText(orderTranscontent);
            //得到xml根元素
            Element root = document.getRootElement();
            //得到根元素的所有子节点
            Element element1 = root.element("drugNum");
            int drugNum = Integer.parseInt(element1.getText());
            if (drugNum>0) {
                for (int i = 1;i<=Integer.parseInt(element1.getText());i++) {
                    if (root.element("drug"+i).element("localCode").getText() == null
                            || root.element("drug"+i).element("appAmt").getText() == null
                            || root.element("drug"+i).element("localCode").getText() == ""
                            || root.element("drug"+i).element("appAmt").getText() == "") {
                        throw new Exception();
                    }
                }
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        test("");
    }
}
