/**
 * Company
 * Copyright (C) 2004-2017 All Rights Reserved.
 */
package com.atat.deviceGroup.controller;

import com.atat.common.base.controller.BaseController;
import com.atat.customer.service.TabCustomerService;
import com.atat.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author ligw
 * @version $Id CustomerDevicePageController.java, v 0.1 2017-08-20 12:08 ligw Exp $$
 */
@RestController
@RequestMapping("/deviceGroup")
public class CustomerDevicePageController extends BaseController{

    @Autowired
    private TabCustomerService tabCustomerService;

    /**
     * 设备列表页面
     *
     * @return
     */
    @RequestMapping(value = "/deviceList", method = RequestMethod.GET)
    public ModelAndView clientDeviceList() {
        ModelAndView mav = new ModelAndView("detail");
        return mav;
    }

    /**
     * 设备图表页面
     *
     * @return
     */
    @RequestMapping(value = "/chartDetail", method = RequestMethod.GET)
    public ModelAndView deviceDataChart(HttpServletRequest request, HttpServletResponse response) {
        String deviceId = request.getParameter("deviceId");
        String code = request.getParameter("code");
        ModelAndView mav = new ModelAndView("chartDetail");
        mav.addObject("code",code);
        mav.addObject("deviceId",deviceId);

        return mav;
    }

    @RequestMapping(value = "/updateService", method = RequestMethod.GET)
    public ModelAndView updateService(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("updateService");
        String mobelPhone = request.getParameter("mobelPhone");
        if (StringUtil.isNotEmpty(mobelPhone)) {
            mav.addObject("customer",tabCustomerService.getCustomerByMobelPhone(mobelPhone));
        }
        return mav;
    }

    @RequestMapping(value = "/addDevice", method = RequestMethod.GET)
    public ModelAndView addDevice(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("addDevice");
        String mobelPhone = request.getParameter("mobelPhone");
        if (StringUtil.isNotEmpty(mobelPhone)) {
            mav.addObject("customer",tabCustomerService.getCustomerByMobelPhone(mobelPhone));
        }
        return mav;
    }

}
