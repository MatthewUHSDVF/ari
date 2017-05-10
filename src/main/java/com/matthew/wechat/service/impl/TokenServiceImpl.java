package com.matthew.wechat.service.impl;

import com.matthew.wechat.model.CheckModel;
import com.matthew.wechat.service.TokenService;
import com.matthew.wechat.util.CheckUtil;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * Created by Administrator on 2017/4/18.
 */
@Service
public class TokenServiceImpl implements TokenService {


    public String validate(CheckModel tokenModel) {
        String token = "wechatzzz";
        String signature = tokenModel.getSignature();
        String timestamp = tokenModel.getTimestamp();
        String nonce = tokenModel.getNonce();
        String echostr = tokenModel.getEchostr();
        if (signature != null && timestamp != null & nonce != null) {
            String[] str = { token , timestamp + "", nonce + "" };
            Arrays.sort(str); // 字典序排序
            String bigStr = str[0] + str[1] + str[2];
            // SHA1加密
            String digest = CheckUtil.encode("SHA1", bigStr).toLowerCase();
            // 确认请求来至微信
            if (digest.equals(signature)) {
                // 最好此处将echostr存起来，以后每次校验消息来源都需要用到
                return echostr;
            }
        }
        return "error";
    }
}
