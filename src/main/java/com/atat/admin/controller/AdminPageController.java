/**
 * Company
 * Copyright (C) 2004-2017 All Rights Reserved.
 */
package com.atat.admin.controller;

import com.atat.admin.service.TabAdminService;
import com.atat.common.base.controller.BaseController;
import com.atat.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ligw
 * @version $Id AdminPageController.java, v 0.1 2017-08-20 11:55 ligw Exp $$
 */
@RestController
@RequestMapping("/admin")
public class AdminPageController  extends BaseController {

    @Autowired
    private TabAdminService tabAdminService;

    /**
     * 管理员登录页面
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/login")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("adminlogin");
        return mav;
    }

    /**
     * 管理员首页
     *
     * @return
     */
    @RequestMapping(value = "/index")
    public ModelAndView clientPersonal(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("adminIndex");
        String adminId = request.getParameter("adminId");
        if (StringUtil.isNotEmpty(adminId)) {
            mav.addObject("admin", tabAdminService.getTabAdminById(Integer.parseInt(adminId)));
        }
        return mav;
    }
}
