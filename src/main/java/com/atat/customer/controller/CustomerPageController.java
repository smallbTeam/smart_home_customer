/**
 * Company
 * Copyright (C) 2004-2017 All Rights Reserved.
 */
package com.atat.customer.controller;

import com.atat.common.base.controller.BaseController;
import com.atat.common.bean.JsonResult;
import com.atat.customer.service.TabCustomerService;
import com.atat.message.service.WeixinService;
import com.atat.util.CollectionUtil;
import com.atat.util.StringUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author ligw
 * @version $Id CustomerPageController.java, v 0.1 2017-08-20 11:58 ligw Exp $$
 */
@RestController
@RequestMapping("/customer")
public class CustomerPageController extends BaseController{

    @Autowired
    private TabCustomerService tabCustomerService;

    @Autowired
    private Properties wxPlatformProperties;

    @Autowired
    private WeixinService weixinService;

    /**
     * 用户注册页面
     *
     * @param request
     * @param response
     */

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("register");
        String wxId = request.getParameter("wxId");
        if (StringUtil.isNotEmpty(wxId)) {
            mav.addObject("wxId", wxId);
        }
        return mav;
    }

    /**
     * 用户登录页面
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("login");
        String wxId = request.getParameter("wxId");
        if (StringUtil.isNotEmpty(wxId)) {
            mav.addObject("wxId", wxId);
        }
        return mav;
    }

    /**
     * 个人中心页面
     *
     * @return
     */
    @RequestMapping(value = "/personal", method = RequestMethod.GET)
    public ModelAndView clientPersonal(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("personal");
        String mobelPhone = request.getParameter("mobelPhone");
        if (StringUtil.isNotEmpty(mobelPhone)) {
            mav.addObject("account", tabCustomerService.getCustomerByMobelPhone(mobelPhone));
        }
        return mav;
    }

    /**
     * 微信跳转页面
     *
     * @param request
     * @param response
     * @return
     */
    @ApiOperation("微信登陆验证WxId是否已注册表")
    @RequestMapping(value = "/wxUidIsExit", method = RequestMethod.GET)
    public ModelAndView wxUidIsExit(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ModelAndView mav = new ModelAndView("register");
        // 判断微信Id是否已存在
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        String appid = wxPlatformProperties.getProperty("wxAppId");
        String wxId = weixinService.getUserwxId(code);
        JsonResult<Map<String, Object>> result = new JsonResult<Map<String, Object>>();
        Map<String, Object> rs = new HashMap<String, Object>();
        if (StringUtil.isNotEmpty(wxId)) {
            // 判定openId是否已经在表中存在
            Map<String, Object> customerMap = tabCustomerService.getCustomerByWxId(wxId);
            if (CollectionUtil.isNotEmpty(customerMap)) {
                mav = new ModelAndView("main");
                mav.addObject("account", customerMap);
                String mainurl = "http://www.atatkj.com/smarthome/customer/wxUidIsExit";
                Map<String, Object> weixinInfoMap = weixinService.getSignature(mainurl);
                mav.addObject("appid", appid);
                mav.addObject("noncestr", weixinInfoMap.get("noncestr"));
                mav.addObject("timestamp", weixinInfoMap.get("timestamp"));
                mav.addObject("signaturet", weixinInfoMap.get("signaturet"));
            }
            else {
                mav.addObject("wxId", wxId);
            }
        }
        return mav;
    }

    /**
     * 首页
     *
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView clientIndex(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("main");
        String mobelPhone = request.getParameter("mobelPhone");
        if (StringUtil.isNotEmpty(mobelPhone)) {
            mav.addObject("account",tabCustomerService.getCustomerByMobelPhone(mobelPhone));
        }
        return mav;
    }

}
