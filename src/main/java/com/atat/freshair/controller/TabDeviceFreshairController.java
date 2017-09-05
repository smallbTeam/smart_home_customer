package com.atat.freshair.controller;

import com.atat.common.base.controller.BaseController;
import com.atat.common.bean.JsonResult;
import com.atat.freshair.service.*;
import com.atat.util.StringUtil;
import com.atat.common.bean.ResultCode;
import com.atat.freshair.bean.TabDeviceFreshair;
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
 * @version $Id TabDeviceFreshairController.java, 2017-08-11 17:34:11 wuhaosoft
 *          Exp
 */
@Api("空气监测设备表接口")
@RestController
@RequestMapping("/freshair")
public class TabDeviceFreshairController extends BaseController {

    @Autowired
    private TabDeviceFreshairService tabDeviceFreshairService;

    @Autowired
    private DataFreshairNowService dataFreshairNowService;

    @Autowired
    private DataFreshairHourService dataFreshairHourService;

    @Autowired
    private DataFreshairDayService dataFreshairDayService;

    @Autowired
    private DataFreshairWeekService dataFreshairWeekService;

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
            @ApiParam(value = "每页显示多少数据(必传)") @RequestParam Integer pageSize, HttpServletResponse response)
            throws Exception {
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
        this.renderJson(response, tabDeviceFreshairService.getTabDeviceFreshairPageTurn(rs, pageNo, pageSize));
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
            HttpServletResponse response) throws Exception {
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
            rs.put("tabDeviceGroupId", tabDeviceGroupId);
        }
        if (null != state) {
            rs.put("state", state);
        }
        result.setObj(tabDeviceFreshairService.selectTabDeviceFreshairList(rs));
        result.setCode(ResultCode.SUCCESS.getCode());
        this.renderJson(response, result);
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
            HttpServletResponse response) throws Exception {
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
        this.renderJson(response, result);
    }

    @ApiOperation("更新 空气监测设备表")
    @RequestMapping(value = "/tabDeviceFreshair/{tabDeviceFreshairId}", method = RequestMethod.PUT)
    public void updateTabDeviceFreshairById(@ApiParam(value = "设备ID (必传参数)") @PathVariable Long tabDeviceFreshairId,
            @ApiParam(value = "设备序列号(唯一) (非必传参数)") @RequestParam(required = false) String deviceSeriaNumber,
            @ApiParam(value = "设备类型 (非必传参数)") @RequestParam(required = false) String deviceCategory,
            @ApiParam(value = "ip (非必传参数)") @RequestParam(required = false) String ip,
            @ApiParam(value = "设备名称 (非必传参数)") @RequestParam(required = false) String name,
            @ApiParam(value = "设备分组Id (非必传参数)") @RequestParam(required = false) Long tabDeviceGroupId,
            @ApiParam(value = "设备开关状态 (非必传参数)") @RequestParam(required = false) Integer state,
            @ApiParam(value = "是否删除 1:是 2:否 (非必传参数)") @RequestParam(required = false) Integer isDeleted,
            HttpServletResponse response) throws Exception {
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
        this.renderJson(response, result);
    }

    @ApiOperation("删除 空气监测设备表")
    @RequestMapping(value = "/tabDeviceFreshair/{tabDeviceFreshairId}", method = RequestMethod.DELETE)
    public void delTabDeviceFreshairById(@ApiParam(value = "设备ID (必传参数)") @PathVariable Long tabDeviceFreshairId,
            HttpServletResponse response) throws Exception {
        JsonResult<Object> result = new JsonResult<Object>();
        tabDeviceFreshairService.delTabDeviceFreshairById(tabDeviceFreshairId);
        result.setCode(ResultCode.SUCCESS.getCode());
        this.renderJson(response, result);
    }

    @ApiOperation("查询 空气监测设备表")
    @RequestMapping(value = "/tabDeviceFreshair/{tabDeviceFreshairId}", method = RequestMethod.GET)
    public void getTabDeviceFreshairById(@ApiParam(value = "设备ID (必传参数)") @PathVariable Long tabDeviceFreshairId,
            HttpServletResponse response) throws Exception {
        JsonResult<Map<String, Object>> result = new JsonResult<Map<String, Object>>();
        Map<String, Object> rs = new HashMap<String, Object>();
        result.setObj(tabDeviceFreshairService.getTabDeviceFreshairById(tabDeviceFreshairId));
        result.setCode(ResultCode.SUCCESS.getCode());
        this.renderJson(response, result);
    }

    @ApiOperation("查询 空气监测设备实时数据")
    @RequestMapping(value = "/freshairNowData/{deviceSeriaNumber}", method = RequestMethod.GET)
    public void getFreshairNowData(@ApiParam(value = "设备ID (必传参数)") @PathVariable String deviceSeriaNumber,
            HttpServletResponse response) throws Exception {
        JsonResult<Map<String, Object>> result = new JsonResult<Map<String, Object>>();
        Map<String, Object> rs = tabDeviceFreshairService.getFreshairNowData(deviceSeriaNumber);
        if (null == rs) {
            result.setCode(ResultCode.SYSTEM_ERROR.getCode());
        }
        else {
            result.setObj(rs);
            result.setCode(ResultCode.SUCCESS.getCode());
        }
        this.renderJson(response, result);
    }

    @ApiOperation("查询分组下所有空气检测设备")
    @RequestMapping(value = "/tabDeviceFreshairsInGroup/{tabDeviceGroupId}", method = RequestMethod.GET)
    public void findTabDeviceFreshairsByTabDeviceGroupId(
            @ApiParam(value = "设备分组Id (必传参数)") @PathVariable Long tabDeviceGroupId, HttpServletResponse response)
            throws Exception {
        JsonResult<Object> result = new JsonResult<Object>();
        Map<String, Object> rs = new HashMap<String, Object>();
        if (null != tabDeviceGroupId) {
            rs.put("tabDeviceGroupId", tabDeviceGroupId);
        }
        result.setObj(tabDeviceFreshairService.selectTabDeviceFreshairList(rs));
        result.setCode(ResultCode.SUCCESS.getCode());
        this.renderJson(response, result);
    }

    @ApiOperation("用户更新 空气监测设备")
    @RequestMapping(value = "/CustomerUpdateTabDeviceFreshair", method = RequestMethod.POST)
    public void CustomerUpdateTabDeviceFreshair(
            @ApiParam(value = "设备ID (必传参数)") @RequestParam Long tabDeviceFreshairId,
            @ApiParam(value = "用户Id (必传参数)") @RequestParam Long tabCustomerId,
            @ApiParam(value = "设备名称 (非必传参数)") @RequestParam(required = false) String name,
            @ApiParam(value = "设备分组Id (非必传参数)") @RequestParam(required = false) Long tabDeviceGroupId,
            @ApiParam(value = "设备开关状态 (非必传参数)") @RequestParam(required = false) Integer state,
            @ApiParam(value = "是否删除 1:是 2:否 (非必传参数)") @RequestParam(required = false) Integer isDeleted,
            HttpServletResponse response) throws Exception {
        JsonResult<Object> result = new JsonResult<Object>();
        TabDeviceFreshair tabDeviceFreshair = new TabDeviceFreshair();
        tabDeviceFreshair.setTabDeviceFreshairId(tabDeviceFreshairId);
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
        Integer rescode = tabDeviceFreshairService.CustomerUpdateTabDeviceFreshair(tabCustomerId, tabDeviceFreshair);
        if (((Integer) 1).equals(rescode)) {
            result.setCode(ResultCode.SUCCESS.getCode());
        }
        else if (((Integer) 0).equals(rescode)) {
            result.setCode(ResultCode.ERROR.getCode());
            result.setErrorMsg("非当前设备所有人不能修改设备信息");
        }
        else {
            result.setCode(ResultCode.SYSTEM_ERROR.getCode());
            result.setErrorMsg("数据异常 修改失败");
        }
        this.renderJson(response, result);
    }

    @ApiOperation("获取设备数据")
    @RequestMapping(value = "/getDeviceDataMap",method = RequestMethod.POST)
    public void getDeviceDataMap(
            @ApiParam(value = "时间类型 (必传参数)") @RequestParam String type,
            @ApiParam(value = "空气检测设备表id (必传参数)") @RequestParam Long tabDeviceFreshairId,
            @ApiParam(value = "参数类型 (必传参数)") @RequestParam String code,
            HttpServletResponse response) throws Exception {
        JsonResult<Map<String, Object>> result = new JsonResult<Map<String, Object>>();
        Map<String, Object> deviceData = new HashMap<String, Object>();
        if ("day".equals(type)) {
            deviceData = dataFreshairNowService.getThreeHourDeviceData(tabDeviceFreshairId, code);
        }
        else if ("mounth".equals(type)) {
            deviceData = dataFreshairDayService.getOneMounthDeviceData(tabDeviceFreshairId, code);
        }
        else if ("year".equals(type)) {
            deviceData = dataFreshairWeekService.getOneYearDeviceData(tabDeviceFreshairId, code);
        }else{
            deviceData = null;
        }
        if (null != deviceData) {
            result.setObj(deviceData);
            result.setCode(ResultCode.SUCCESS.getCode());
        }
        else {
            result.setCode(ResultCode.ERROR.getCode());
            result.setErrorMsg("没有请求对应类型参数");
        }
        this.renderJson(response, result);
    }
}
