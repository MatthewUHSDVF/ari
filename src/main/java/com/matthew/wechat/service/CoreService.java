package com.matthew.wechat.service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2017/4/19.
 */
public interface CoreService {
    String processRequest(HttpServletRequest request);
    String processRequestText();
}
