/**
 * Company
 * Copyright (C) 2004-2017 All Rights Reserved.
 */
package com.atat.message.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.atat.message.service.WeixinService;
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
public class WeixinServiceImpl implements WeixinService{

    protected  final Logger logger = LoggerFactory.getLogger(WeixinServiceImpl.class);

    public  final String GETACCESSTOKENURL = "https://api.weixin.qq.com/cgi-bin/token";

    public  final String GETAJSAPITICKET = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";
    
    @Autowired
    private TabPropertyMapService tabPropertyMapService;

    @Autowired
    private Properties wxPlatformProperties;


    /**
     * 微信推送消息
     * @param touser
     * @param url
     * @param template_id
     * @param data
     * @return
     */
    @Override
    public  Integer sendWeixinMessage(List<String> touser, String url, String template_id, JSONObject data) {
        Map<String, Object> propMap = tabPropertyMapService.getTabPropertyMapById("accessToken");
        String accesstoken = (String) propMap.get("propval");
        String urls = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + accesstoken;
        Integer sentRes = 0;
        for (int i = 0;i < touser.size();i++) {
            // 封装数据
            JSONObject json = new JSONObject();
            json.put("touser", touser.get(i));// 接收者wxName
            json.put("template_id", template_id);// 消息模板
            if (StringUtil.isNotEmpty(url)) {
                json.put("url", url);// 填写url可查看详情
            }
            json.put("data", data);
            JSONObject resJsonObj = CommonUtil.httpsRequest(urls, "POST", json.toString());
            Map<String, Object> resMap = JsonUtil.fromJson(resJsonObj.toString(), Map.class);
            String errcode = resMap.get("errcode") + "";
            String errmsg = (String) resMap.get("errmsg");
            if (!errcode.equals("0")) {
                sentRes = sentRes++;
                logger.info("\n\nerrcode:" + errcode + "\n\n\n\nerrmsg:" + errmsg + "\n\n\n\n");
            }
        }
        return sentRes;
    }

    /**
     * 依据Code获取用户微信Id
     * @param code
     * @return
     */
    @Override
    public  String getUserwxId(String code) {
        String appid = wxPlatformProperties.getProperty("wxAppId");
        String secret = wxPlatformProperties.getProperty("wxSecret");
        if (StringUtil.isEmpty(code) || StringUtil.isEmpty(appid) || StringUtil.isEmpty(secret)) {
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("appid", appid);
        paramMap.put("secret", secret);
        paramMap.put("code", code);
        paramMap.put("grant_type", "authorization_code");
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token";
        String resJson = "";
        try {
            resJson = HttpClientUtil.doPost(url, paramMap, "utf-8");
            logger.info("微信平台get请求：" + URLUtil.getDataUrl(url, paramMap));
            logger.info("微信平台请求用户OpenID：[" + resJson + "]");
            // 解析Json 获取AppId
            Map<String, Object> resMap = JsonUtil.fromJson(resJson, Map.class);
            String wxId = (String) resMap.get("openid");
            if (StringUtil.isNotEmpty(wxId)) {
                return wxId;
            }
            else {
                return null;
            }
        }
        catch (Exception e) {
            logger.info("[微信平台请求用户OpenID][Http请求异常" + e.getMessage() + "]");
            return null;
        }
    }

    /**
     * 调用微信jsapiticket_ticket
     * @param mainurl
     * @return
     */
    @Override
    public  Map<String, Object> getSignature(String mainurl) {
        Map<String, Object> jsapiticket_ticket = new HashMap<String, Object>();
        // 校验签名用的参数 参与签名的字段包括noncestr（随机字符串）, 有效的jsapi_ticket,
        // timestamp（时间戳）, url（当前网页的URL，不包含#及其后面部分）
        // 随机字符串
        String signature = "";
        // 随机字符串
        String noncestr = UUID.randomUUID().toString().replaceAll("-", "");
        // 时间戳
        String timestamp = String.valueOf(new Date().getTime());
        //获取jsapiticket
        String jsapiTicket = (String) tabPropertyMapService.getTabPropertyMapById("jsapiTicket").get("propval");
        /////拼接sha1
        String str1 = "jsapi_ticket="+jsapiTicket+"&noncestr="+noncestr+"&timestamp="+timestamp+"&url="+mainurl;
        try {
            signature = sha1(str1);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        jsapiticket_ticket.put("noncestr",noncestr);
        jsapiticket_ticket.put("timestamp",timestamp);
        jsapiticket_ticket.put("signaturet",signature);
        return  jsapiticket_ticket;
    }

    // 将数组拼接成字符串
    public String implode(List<String> list) {
        StringBuilder sb = new StringBuilder(list.size() * 3);
        for (int i = 0;i < list.size();i++) {
            sb.append(list.get(i));
        }
        return sb.toString();
    }

    // 执行sh1哈希散列运算算法
    public  String sha1(String data) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA1");
        md.update(data.getBytes());
        StringBuffer buf = new StringBuffer();
        byte[] bits = md.digest();
        for (int i = 0;i < bits.length;i++) {
            int a = bits[i];
            if (a < 0) {
                a += 256;
            }
            if (a < 16) {
                buf.append("0");
            }
            buf.append(Integer.toHexString(a));
        }
        return buf.toString();
    }
}

