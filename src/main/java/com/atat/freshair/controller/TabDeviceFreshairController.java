package com.atat.freshair.controller;

import com.atat.common.base.controller.BaseController;
import com.atat.common.bean.JsonResult;
import com.atat.util.StringUtil;
import com.atat.common.bean.ResultCode;
import com.atat.freshair.bean.TabDeviceFreshair;
import com.atat.freshair.service.TabDeviceFreshairService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * @author wuhaosoft
 * @version $Id TabDeviceFreshairController.java, 2017-08-11 17:34:11 wuhaosoft Exp
 *
 */
@Api("空气监测设备表接口")
@RestController
@RequestMapping("/freshair")
public class TabDeviceFreshairController  extends BaseController {

    @Autowired
    private TabDeviceFreshairService tabDeviceFreshairService;

    @ApiOperation("查询 所有 空气监测设备表 分页")
    @RequestMapping(value = "/tabDeviceFreshairs", method = RequestMethod.POST)
    public void getTabDeviceFreshairPageTurn(
            @ApiParam(value = "设备序列号(唯一) (非必传参数)") @RequestParam(required = false) String deviceSeriaNumber,
            @ApiParam(value = "设备类型 (非必传参数)") @RequestParam(required = false) String deviceCategory,
            @ApiParam(value = "ip (非必传参数)") @RequestParam(required = false) String ip,
            @ApiParam(value = "设备名称 (非必传参数)") @RequestParam(required = false) String name,
            @ApiParam(value = "设备分组Id (非必传参数)") @RequestParam(required = false) Long tabDeviceGroupId,
            @ApiParam(value = "设备开关状态 (非必传参数)") @RequestParam(required = false) Integer state,
            @ApiParam(value = "页码(必传)") @RequestParam Integer pageNo,
            @ApiParam(value = "每页显示多少数据(必传)") @RequestParam Integer pageSize,
            HttpServletResponse response) throws Exception {
        Map<String, Object> rs = new HashMap<String, Object>();
        if (StringUtil.isNotEmpty(deviceSeriaNumber)) {
           rs.put("deviceSeriaNumber", "%" + deviceSeriaNumber + "%");
        }
        if (StringUtil.isNotEmpty(deviceCategory)) {
           rs.put("deviceCategory", "%" + deviceCategory + "%");
        }
        if (StringUtil.isNotEmpty(ip)) {
           rs.put("ip", "%" + ip + "%");
        }
        if (StringUtil.isNotEmpty(name)) {
           rs.put("name", "%" + name + "%");
        }
        if (null != tabDeviceGroupId) {
           rs.put("tabDeviceGroupId", tabDeviceGroupId);
        }
        if (null != state) {
           rs.put("state", state);
        }
        this.renderJson(response,tabDeviceFreshairService.getTabDeviceFreshairPageTurn(rs, pageNo, pageSize));
    }

    @ApiOperation("查询 所有 空气监测设备表")
    @RequestMapping(value = "/allTabDeviceFreshairs", method = RequestMethod.POST)
    public void selectTabDeviceFreshairList(
        @ApiParam(value = "设备序列号(唯一) (非必传参数)") @RequestParam(required = false) String deviceSeriaNumber,
        @ApiParam(value = "设备类型 (非必传参数)") @RequestParam(required = false) String deviceCategory,
        @ApiParam(value = "ip (非必传参数)") @RequestParam(required = false) String ip,
        @ApiParam(value = "设备名称 (非必传参数)") @RequestParam(required = false) String name,
        @ApiParam(value = "设备分组Id (非必传参数)") @RequestParam(required = false) Long tabDeviceGroupId,
        @ApiParam(value = "设备开关状态 (非必传参数)") @RequestParam(required = false) Integer state,
            HttpServletResponse response
        )throws Exception {
        JsonResult<Object> result = new JsonResult<Object>();
        Map<String, Object> rs = new HashMap<String, Object>();
         if (StringUtil.isNotEmpty(deviceSeriaNumber)) {
            rs.put("deviceSeriaNumber", "%" + deviceSeriaNumber + "%");
         }
         if (StringUtil.isNotEmpty(deviceCategory)) {
            rs.put("deviceCategory", "%" + deviceCategory + "%");
         }
         if (StringUtil.isNotEmpty(ip)) {
            rs.put("ip", "%" + ip + "%");
         }
         if (StringUtil.isNotEmpty(name)) {
            rs.put("name", "%" + name + "%");
         }
         if (null != tabDeviceGroupId) {
            rs.put("tabDeviceGroupId",  tabDeviceGroupId );
         }
        if (null != state) {
            rs.put("state", state);
        }
        result.setObj(tabDeviceFreshairService.selectTabDeviceFreshairList(rs));
        result.setCode(ResultCode.SUCCESS.getCode());
        this.renderJson(response,result);
    }

    @ApiOperation("新增 空气监测设备表")
    @RequestMapping(value = "/tabDeviceFreshair", method = RequestMethod.POST)
    public void addTabDeviceFreshair(
                @ApiParam(value = "设备序列号(唯一) (非必传参数)") @RequestParam(required = false) String deviceSeriaNumber,
                @ApiParam(value = "设备类型 (非必传参数)") @RequestParam(required = false) String deviceCategory,
                @ApiParam(value = "ip (非必传参数)") @RequestParam(required = false) String ip,
                @ApiParam(value = "设备名称 (非必传参数)") @RequestParam(required = false) String name,
                @ApiParam(value = "设备分组Id (非必传参数)") @RequestParam(required = false) Long tabDeviceGroupId,
                @ApiParam(value = "设备开关状态 (非必传参数)") @RequestParam(required = false) Integer state,
            HttpServletResponse response
            ) throws Exception {
        JsonResult<Object> result = new JsonResult<Object>();
        TabDeviceFreshair tabDeviceFreshair = new TabDeviceFreshair();
        if (StringUtil.isNotEmpty(deviceSeriaNumber)) {
           tabDeviceFreshair.setDeviceSeriaNumber(deviceSeriaNumber);
        }
        if (StringUtil.isNotEmpty(deviceCategory)) {
           tabDeviceFreshair.setDeviceCategory(deviceCategory);
        }
        if (StringUtil.isNotEmpty(ip)) {
           tabDeviceFreshair.setIp(ip);
        }
        if (StringUtil.isNotEmpty(name)) {
           tabDeviceFreshair.setName(name);
        }
        if (null != tabDeviceGroupId) {
           tabDeviceFreshair.setTabDeviceGroupId(tabDeviceGroupId);
        }
        if (null != state) {
            tabDeviceFreshair.setState(state);
        }
        tabDeviceFreshair.setCreatedDate(new Date());
        tabDeviceFreshairService.addTabDeviceFreshair(tabDeviceFreshair);
        result.setCode(ResultCode.SUCCESS.getCode());
        this.renderJson(response,result);
    }

    @ApiOperation("更新 空气监测设备表")
    @RequestMapping(value = "/tabDeviceFreshair/{tabDeviceFreshairId}", method = RequestMethod.PUT)
    public void updateTabDeviceFreshairById(
            @ApiParam(value = "设备ID (必传参数)") @PathVariable Long  tabDeviceFreshairId,
             @ApiParam(value = "设备序列号(唯一) (非必传参数)") @RequestParam(required = false) String deviceSeriaNumber,
             @ApiParam(value = "设备类型 (非必传参数)") @RequestParam(required = false) String deviceCategory,
             @ApiParam(value = "ip (非必传参数)") @RequestParam(required = false) String ip,
             @ApiParam(value = "设备名称 (非必传参数)") @RequestParam(required = false) String name,
             @ApiParam(value = "设备分组Id (非必传参数)") @RequestParam(required = false) Long tabDeviceGroupId,
             @ApiParam(value = "设备开关状态 (非必传参数)") @RequestParam(required = false) Integer state,
             @ApiParam(value = "是否删除 1:是 2:否 (非必传参数)") @RequestParam(required = false) Integer isDeleted,
            HttpServletResponse response
            ) throws Exception {
        JsonResult<Object> result = new JsonResult<Object>();
        TabDeviceFreshair tabDeviceFreshair = new TabDeviceFreshair();
        tabDeviceFreshair.setTabDeviceFreshairId(tabDeviceFreshairId);

         if (StringUtil.isNotEmpty(deviceSeriaNumber)) {
            tabDeviceFreshair.setDeviceSeriaNumber(deviceSeriaNumber);
         }
         if (StringUtil.isNotEmpty(deviceCategory)) {
            tabDeviceFreshair.setDeviceCategory(deviceCategory);
         }
         if (StringUtil.isNotEmpty(ip)) {
            tabDeviceFreshair.setIp(ip);
         }
         if (StringUtil.isNotEmpty(name)) {
            tabDeviceFreshair.setName(name);
         }
         if (null != tabDeviceGroupId) {
            tabDeviceFreshair.setTabDeviceGroupId(tabDeviceGroupId);
         }
         if (null != state) {
            tabDeviceFreshair.setState(state);
         }
         if (null != isDeleted) {
            tabDeviceFreshair.setIsDeleted(isDeleted);
         }
        tabDeviceFreshair.setModifiedDate(new Date());
        tabDeviceFreshairService.updateTabDeviceFreshairById(tabDeviceFreshair);
        result.setCode(ResultCode.SUCCESS.getCode());
        this.renderJson(response,result);
    }

    @ApiOperation("删除 空气监测设备表")
    @RequestMapping(value = "/tabDeviceFreshair/{tabDeviceFreshairId}", method = RequestMethod.DELETE)
    public void delTabDeviceFreshairById(
             @ApiParam(value = "设备ID (必传参数)") @PathVariable Long  tabDeviceFreshairId,
            HttpServletResponse response
            ) throws Exception {
        JsonResult<Object> result = new JsonResult<Object>();
        tabDeviceFreshairService.delTabDeviceFreshairById(tabDeviceFreshairId);
        result.setCode(ResultCode.SUCCESS.getCode());
        this.renderJson(response,result);
    }

    @ApiOperation("查询 空气监测设备表")
    @RequestMapping(value = "/tabDeviceFreshair/{tabDeviceFreshairId}", method = RequestMethod.GET)
    public void getTabDeviceFreshairById(
            @ApiParam(value = "设备ID (必传参数)") @PathVariable Long  tabDeviceFreshairId,
            HttpServletResponse response) throws Exception {
        JsonResult<Map<String, Object>> result = new JsonResult<Map<String, Object>>();
        Map<String, Object> rs = new HashMap<String, Object>();
        result.setObj(tabDeviceFreshairService.getTabDeviceFreshairById(tabDeviceFreshairId));
        result.setCode(ResultCode.SUCCESS.getCode());
        this.renderJson(response,result);
    }
}

