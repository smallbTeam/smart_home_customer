/**
 * Company
 * Copyright (C) 2004-2017 All Rights Reserved.
 */
package com.atat.message.service;

import com.alibaba.fastjson.JSONObject;
import com.atat.property.service.TabPropertyMapService;
import com.atat.util.JsonUtil;
import com.atat.util.StringUtil;
import com.atat.util.httpClient.HttpClientUtil;
import com.atat.util.httpClient.URLUtil;
import com.atat.util.weixinClient.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * @author ligw
 * @version $Id weixinMessageService.java, v 0.1 2017-07-15 16:03 ligw Exp $$
 */
@Service
public interface WeixinService {


    /**
     * 微信推送消息
     * @param touser
     * @param url
     * @param template_id
     * @param data
     * @return
     */
    public  Integer sendWeixinMessage(List<String> touser, String url, String template_id, JSONObject data);

    /**
     * 依据Code获取用户微信Id
     * @param code
     * @return
     */
    public  String getUserwxId(String code);

    /**
     * 调用微信jsapiticket_ticket
     * @param mainurl
     * @return
     */
    public  Map<String, Object> getSignature(String mainurl);

}

