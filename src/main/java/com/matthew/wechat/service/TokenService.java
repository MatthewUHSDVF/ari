package com.matthew.wechat.service;

import com.matthew.wechat.model.CheckModel;

/**
 * Created by Administrator on 2017/4/18.
 */
public interface TokenService {

    String validate(CheckModel tokenModel);
}
