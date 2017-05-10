package com.matthew.wechat.controller;

import com.matthew.wechat.model.CheckModel;
import com.matthew.wechat.po.PushMsg;
import com.matthew.wechat.po.Text;
import com.matthew.wechat.service.CoreService;
import com.matthew.wechat.service.TokenService;
import com.matthew.wechat.util.WeixinUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;

/**
 * Created by Administrator on 2017/4/18.
 */
@Controller
@RequestMapping("/wechat")
public class TokenController {

    private static Logger logger = LoggerFactory.getLogger(TokenController.class);

    @Autowired
    private TokenService tokenService;

    @Autowired
    private CoreService coreService;

    /**
     * 开发者模式token校验
     * @param tokenModel
     * @throws ParseException
     * @throws IOException
     */
    @RequestMapping(method = { RequestMethod.GET })
    @ResponseBody
    public String validate(CheckModel tokenModel) throws ParseException, IOException {
        logger.info("tokenModel="+tokenModel.toString());
        return tokenService.validate(tokenModel);
    }

    /**
     * 微信消息的处理
     */
    @RequestMapping(method = { RequestMethod.POST })
    public void dispose(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /* 消息的接收、处理、响应 */

        // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // 调用核心业务类接收消息、处理消息
        String respMessage = coreService.processRequest(request);
        logger.info(respMessage);
        // 响应消息
        PrintWriter out = response.getWriter();
        out.print(respMessage);
        out.close();
    }

    @RequestMapping(value = "push_message")
    public void pushMessage(String content,String openId) throws IOException {
        Text text =new Text();
        text.setContent(content);

        PushMsg pushMsg = new PushMsg();
        pushMsg.setTouser(openId);
        pushMsg.setMsgtype("text");
        pushMsg.setText(text);

        int result = WeixinUtil.pushMessage(pushMsg,WeixinUtil.getAccessToken("wxffdaa6a7dbf1a892","2fa77078715987758fb057915979e8c9").getToken());

        //判断菜单创建结果
        if (0 == result)
            logger.info("消息推送成功！");
        else
            logger.info("消息推送失败，错误码：" + result);

    }


}
