/**
 * Company
 * Copyright (C) 2004-2017 All Rights Reserved.
 */
package com.atat.freshair.controller;

import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

/**
 * @author ligw
 * @version $Id DeviceFreshairPageController.java, v 0.1 2017-09-01 0:44 ligw Exp $$
 */
@RestController
@RequestMapping("/freshair")
public class DeviceFreshairPageController {

    /**
     * 设备图表页面
     *
     * @return
     */
    @RequestMapping(value = "/chartDetail" ,method = RequestMethod.GET)
    public ModelAndView deviceDataChart(
            @ApiParam(value = "空气检测设备表id (必传参数)") @RequestParam Long tabDeviceFreshairId,
            @ApiParam(value = "参数类型 (必传参数)") @RequestParam String code, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("chartDetail");
        mav.addObject("tabDeviceFreshairId",tabDeviceFreshairId);
        mav.addObject("code",code);

        return mav;
    }
}
