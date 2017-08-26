/**
 * Company
 * Copyright (C) 2004-2017 All Rights Reserved.
 */
package com.atat.message.controller;

import com.atat.common.base.controller.BaseController;
import com.atat.common.bean.JsonResult;
import com.atat.common.bean.ResultCode;
import com.atat.customer.service.TabCustomerService;
import com.atat.message.service.ShortMessageService;
import com.atat.util.CollectionUtil;
import com.atat.util.StringUtil;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author ligw
 * @version $Id ShortMessageController.java, v 0.1 2017-08-20 12:53 ligw Exp $$
 */
@RestController
@RequestMapping("/shortMessage")
public class ShortMessageController extends BaseController {

    @Resource
    private ShortMessageService shortMessageService;

    @Resource
    private TabCustomerService tabCustomerService;

    /**
     * 发送短信验证码 并记录时间戳
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/sendMsg", method = RequestMethod.GET)
    public void sendVerificationMsg(@ApiParam(value = "用户手机号 (必传参数)") @RequestParam String mobelPhone,
            HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonResult<String> result = new JsonResult<String>();
        // 依据手机号查询用户是否已存在
        Map<String, Object> customer = tabCustomerService.getCustomerByMobelPhone(mobelPhone);
        if (CollectionUtil.isEmpty(customer)) {
            // 生成随机六位短信验证码
            String randomCode = ((int) ((Math.random() * 9 + 1) * 100000)) + "";
            // 发送短信
            String msgContent = "感谢注ATAT智能家居，您的验证码为 " + randomCode + "有效时间十分钟";
            String state = shortMessageService.sendShortMessage(mobelPhone, msgContent).toString();
            if ((null != state) && ("1".equals(state))) {
                // 成功-验证码存入session
                String timeStamp = String.valueOf(new Date().getTime());
                request.getSession().setAttribute("msgCodeSsion" + mobelPhone, randomCode + "&&" + timeStamp);
                // 成功-将发送结果返回前台
                result.setCode(ResultCode.SUCCESS.getCode());
                // result.setObj(randomCode);
            }
            else {
                result.setCode(ResultCode.ERROR.getCode());
                result.setErrorMsg("发送验证码失败!");
            }
            // 失败-将发送结果返回前台
        }
        else {
            result.setCode(ResultCode.ERROR.getCode());
            result.setErrorMsg("用户已存在");
        }
        this.renderJson(response, result);
    }

    /**
     * 较验验证码是否有效
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/veridateMsg", method = RequestMethod.POST)
    public void veridateMsg(@ApiParam(value = "用户手机号 (必传参数)") @RequestParam String mobelPhone,
            @ApiParam(value = "验证码 (必传参数)") @RequestParam String veridateMsg, HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        JsonResult<Boolean> result = new JsonResult<Boolean>();
        // 验证session
        String msmRandomCode = (String) request.getSession().getAttribute("msgCodeSsion" + mobelPhone);
        String randomCode = "";
        String timeStamp = "";
        long timePath = 600001;
        if (StringUtil.isNotEmpty(msmRandomCode)) {
            String temp[] = msmRandomCode.split("&&");
            randomCode = temp[0];
            timeStamp = temp[1];
        }
        // 计算时间差
        if ((StringUtil.isNotEmpty(randomCode)) && (StringUtil.isNotEmpty(timeStamp))) {
            timePath = (Long) ((new Date()).getTime()) - Long.parseLong(timeStamp);
        }
        if ((StringUtil.isNotEmpty(randomCode)) && (veridateMsg.equals(randomCode)) && (timePath < 600000)) {
            // 结果返回前台
            result.setCode(ResultCode.SUCCESS.getCode());
            result.setObj(true);
        }
        else {
            result.setCode(ResultCode.SUCCESS.getCode());
            result.setObj(false);
        }
        this.renderJson(response, result);
    }
}
