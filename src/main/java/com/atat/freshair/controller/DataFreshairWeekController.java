package com.atat.freshair.controller;

import com.atat.common.base.controller.BaseController;
import com.atat.common.bean.JsonResult;
import com.atat.util.StringUtil;
import com.atat.common.bean.ResultCode;
import com.atat.freshair.bean.DataFreshairWeek;
import com.atat.freshair.service.DataFreshairWeekService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wuhaosoft
 * @version $Id DataFreshairWeekController.java, 2017-08-11 17:34:11 wuhaosoft
 *          Exp
 */
@Api("空气监测设备每周数据表接口")
@RestController
@RequestMapping("/freshair")
public class DataFreshairWeekController extends BaseController {

    @Autowired
    private DataFreshairWeekService dataFreshairWeekService;

    @ApiOperation("查询 所有 空气监测设备每周数据表 分页")
    @RequestMapping(value = "/dataFreshairWeeks", method = RequestMethod.POST)
    public void getDataFreshairWeekPageTurn(
            @ApiParam(value = "设备Id (非必传参数)") @RequestParam(required = false) Long tabDeviceFreshairId,
            @ApiParam(value = "温度 (非必传参数)") @RequestParam(required = false) Double wendu,
            @ApiParam(value = "湿度 (非必传参数)") @RequestParam(required = false) Double shidu,
            @ApiParam(value = "pm2.5 (非必传参数)") @RequestParam(required = false) Double pm,
            @ApiParam(value = "voc (非必传参数)") @RequestParam(required = false) Double voc,
            @ApiParam(value = "CO2 (非必传参数)") @RequestParam(required = false) Double co2,
            @ApiParam(value = "时间范围查询-开始(非必传参数日期格式:yyyy-MM-dd HH)") @RequestParam(required = false) @DateTimeFormat(
                    pattern = "yyyy-MM-dd HH") Date recordTimeStart,
            @ApiParam(value = "时间范围查询-截至(非必传参数日期格式:yyyy-MM-dd HH)") @RequestParam(required = false) @DateTimeFormat(
                    pattern = "yyyy-MM-dd HH") Date recordTimeEnd,
            @ApiParam(value = "页码(必传)") @RequestParam Integer pageNo,
            @ApiParam(value = "每页显示多少数据(必传)") @RequestParam Integer pageSize,
            HttpServletResponse response) throws Exception {
        Map<String, Object> rs = new HashMap<String, Object>();
        if (null != tabDeviceFreshairId) {
            rs.put("tabDeviceFreshairId", tabDeviceFreshairId);
        }
        if (null != wendu) {
            rs.put("wendu", wendu);
        }
        if (null != shidu) {
            rs.put("shidu", shidu);
        }
        if (null != pm) {
            rs.put("pm", pm);
        }
        if (null != voc) {
            rs.put("voc", voc);
        }
        if (null != co2) {
            rs.put("co2", co2);
        }
        if (null != recordTimeStart) {
            rs.put("recordTimeStart", recordTimeStart.getTime());
        }
        if (null != recordTimeEnd) {
            rs.put("recordTimeEnd", recordTimeEnd.getTime());
        }
        this.renderJson(response,dataFreshairWeekService.getDataFreshairWeekPageTurn(rs, pageNo, pageSize));
    }

    @ApiOperation("查询 所有 空气监测设备每周数据表")
    @RequestMapping(value = "/allDataFreshairWeeks", method = RequestMethod.POST)
    public void selectDataFreshairWeekList(
            @ApiParam(value = "设备Id (非必传参数)") @RequestParam(required = false) Long tabDeviceFreshairId,
            @ApiParam(value = "温度 (非必传参数)") @RequestParam(required = false) Double wendu,
            @ApiParam(value = "湿度 (非必传参数)") @RequestParam(required = false) Double shidu,
            @ApiParam(value = "pm2.5 (非必传参数)") @RequestParam(required = false) Double pm,
            @ApiParam(value = "voc (非必传参数)") @RequestParam(required = false) Double voc,
            @ApiParam(value = "CO2 (非必传参数)") @RequestParam(required = false) Double co2,
            @ApiParam(value = "时间范围查询-开始(非必传参数日期格式:yyyy-MM-dd HH)") @RequestParam(required = false) @DateTimeFormat(
                    pattern = "yyyy-MM-dd HH") Date recordTimeStart,
            @ApiParam(value = "时间范围查询-截至(非必传参数日期格式:yyyy-MM-dd HH)") @RequestParam(required = false) @DateTimeFormat(
                    pattern = "yyyy-MM-dd HH") Date recordTimeEnd,
            HttpServletResponse response)
            throws Exception {
        JsonResult<Object> result = new JsonResult<Object>();
        Map<String, Object> rs = new HashMap<String, Object>();
        if (null != tabDeviceFreshairId) {
            rs.put("tabDeviceFreshairId", tabDeviceFreshairId);
        }
        if (null != wendu) {
            rs.put("wendu", wendu);
        }
        if (null != shidu) {
            rs.put("shidu", shidu);
        }
        if (null != pm) {
            rs.put("pm", pm);
        }
        if (null != voc) {
            rs.put("voc", voc);
        }
        if (null != co2) {
            rs.put("co2", co2);
        }
        if (null != recordTimeStart) {
            rs.put("recordTimeStart", recordTimeStart.getTime());
        }
        if (null != recordTimeEnd) {
            rs.put("recordTimeEnd", recordTimeEnd.getTime());
        }
        result.setObj(dataFreshairWeekService.selectDataFreshairWeekList(rs));
        result.setCode(ResultCode.SUCCESS.getCode());
        this.renderJson(response,result);
    }

    @ApiOperation("新增 空气监测设备每周数据表")
    @RequestMapping(value = "/dataFreshairWeek", method = RequestMethod.POST)
    public void addDataFreshairWeek(
            @ApiParam(value = "设备Id (非必传参数)") @RequestParam(required = false) Long tabDeviceFreshairId,
            @ApiParam(value = "温度 (非必传参数)") @RequestParam(required = false) Double wendu,
            @ApiParam(value = "湿度 (非必传参数)") @RequestParam(required = false) Double shidu,
            @ApiParam(value = "pm2.5 (非必传参数)") @RequestParam(required = false) Double pm,
            @ApiParam(value = "voc (非必传参数)") @RequestParam(required = false) Double voc,
            @ApiParam(value = "CO2 (非必传参数)") @RequestParam(required = false) Double co2,
            HttpServletResponse response) throws Exception {
        JsonResult<Object> result = new JsonResult<Object>();
        DataFreshairWeek dataFreshairWeek = new DataFreshairWeek();
        if (null != tabDeviceFreshairId) {
            dataFreshairWeek.setTabDeviceFreshairId(tabDeviceFreshairId);
        }
        if (null != wendu) {
            dataFreshairWeek.setWendu(wendu);
        }
        if (null != shidu) {
            dataFreshairWeek.setShidu(shidu);
        }
        if (null != pm) {
            dataFreshairWeek.setPm(pm);
        }
        if (null != voc) {
            dataFreshairWeek.setVoc(voc);
        }
        if (null != co2) {
            dataFreshairWeek.setCo2(co2);
        }
        dataFreshairWeek.setDataFreshairWeekId(new Date().getTime());
        dataFreshairWeekService.addDataFreshairWeek(dataFreshairWeek);
        result.setCode(ResultCode.SUCCESS.getCode());
        this.renderJson(response,result);
    }

    @ApiOperation("更新 空气监测设备每周数据表")
    @RequestMapping(value = "/dataFreshairWeek/{dataFreshairWeekId}", method = RequestMethod.PUT)
    public void updateDataFreshairWeekById(
            @ApiParam(value = "采集时间 (必传参数)") @PathVariable Long dataFreshairWeekId,
            @ApiParam(value = "设备Id (非必传参数)") @RequestParam(required = false) Long tabDeviceFreshairId,
            @ApiParam(value = "温度 (非必传参数)") @RequestParam(required = false) Double wendu,
            @ApiParam(value = "湿度 (非必传参数)") @RequestParam(required = false) Double shidu,
            @ApiParam(value = "pm2.5 (非必传参数)") @RequestParam(required = false) Double pm,
            @ApiParam(value = "voc (非必传参数)") @RequestParam(required = false) Double voc,
            @ApiParam(value = "CO2 (非必传参数)") @RequestParam(required = false) Double co2,
            HttpServletResponse response) throws Exception {
        JsonResult<Object> result = new JsonResult<Object>();
        DataFreshairWeek dataFreshairWeek = new DataFreshairWeek();
        dataFreshairWeek.setDataFreshairWeekId(dataFreshairWeekId);
        if (null != tabDeviceFreshairId) {
            dataFreshairWeek.setTabDeviceFreshairId(tabDeviceFreshairId);
        }
        if (null != wendu) {
            dataFreshairWeek.setWendu(wendu);
        }
        if (null != shidu) {
            dataFreshairWeek.setShidu(shidu);
        }
        if (null != pm) {
            dataFreshairWeek.setPm(pm);
        }
        if (null != voc) {
            dataFreshairWeek.setVoc(voc);
        }
        if (null != co2) {
            dataFreshairWeek.setCo2(co2);
        }
        // dataFreshairWeek.setUpdateDate(new Date());
        dataFreshairWeekService.updateDataFreshairWeekById(dataFreshairWeek);
        result.setCode(ResultCode.SUCCESS.getCode());
        this.renderJson(response,result);
    }

    @ApiOperation("删除 空气监测设备每周数据表")
    @RequestMapping(value = "/dataFreshairWeek/{dataFreshairWeekId}", method = RequestMethod.DELETE)
    public void delDataFreshairWeekById(
            @ApiParam(value = "采集时间 (必传参数)") @PathVariable Long dataFreshairWeekId,
            HttpServletResponse response) throws Exception {
        JsonResult<Object> result = new JsonResult<Object>();
        dataFreshairWeekService.delDataFreshairWeekById(dataFreshairWeekId);
        result.setCode(ResultCode.SUCCESS.getCode());
        this.renderJson(response,result);
    }

    @ApiOperation("查询 空气监测设备 一年内数据")
    @RequestMapping(value = "/oneYearFreshairData/{tabDeviceFreshairId}", method = RequestMethod.GET)
    public void delTabDeviceFreshairById(
            @ApiParam(value = "设备ID (必传参数)") @PathVariable Long  tabDeviceFreshairId,
            HttpServletResponse response
    ) throws Exception {
        JsonResult<Object> result = new JsonResult<Object>();
        dataFreshairWeekService.getOneYearDeviceData(tabDeviceFreshairId);
        result.setCode(ResultCode.SUCCESS.getCode());
        this.renderJson(response,result);
    }
}
