package com.atat.property.controller;

import com.atat.common.base.controller.BaseController;
import com.atat.common.bean.JsonResult;
import com.atat.util.StringUtil;
import com.atat.common.bean.ResultCode;
import com.atat.property.bean.TabPropertyMap;
import com.atat.property.service.TabPropertyMapService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


/**
 * @author wuhaosoft
 * @version $Id TabPropertyMapController.java, 2017-08-10 01:19:52 wuhaosoft Exp
 *
 */
@Api("配置表接口")
@RestController
@RequestMapping("/property")
public class TabPropertyMapController  extends BaseController {

    @Autowired
    private TabPropertyMapService tabPropertyMapService;

    @ApiOperation("查询 所有 配置表 分页")
    @RequestMapping(value = "/tabPropertyMaps", method = RequestMethod.POST)
    public void getTabPropertyMapPageTurn(
            @ApiParam(value = "propval (非必传参数)") @RequestParam(required = false) String propval,
            @ApiParam(value = "页码(必传)") @RequestParam Integer pageNo,
            @ApiParam(value = "每页显示多少数据(必传)") @RequestParam Integer pageSize,
            HttpServletResponse response) throws Exception {
        Map<String, Object> rs = new HashMap<String, Object>();
        if (StringUtil.isNotEmpty(propval)) {
           rs.put("propval", "%" + propval + "%");
        }
        this.renderJson(response,tabPropertyMapService.getTabPropertyMapPageTurn(rs, pageNo, pageSize));
    }

    @ApiOperation("查询 所有 配置表")
    @RequestMapping(value = "/allTabPropertyMaps", method = RequestMethod.POST)
    public void selectTabPropertyMapList(
        @ApiParam(value = "propval (非必传参数)") @RequestParam(required = false) String propval,
            HttpServletResponse response
        )throws Exception {
        JsonResult<Object> result = new JsonResult<Object>();
        Map<String, Object> rs = new HashMap<String, Object>();
         if (StringUtil.isNotEmpty(propval)) {
            rs.put("propval", "%" + propval + "%");
         }
        result.setObj(tabPropertyMapService.selectTabPropertyMapList(rs));
        result.setCode(ResultCode.SUCCESS.getCode());
        this.renderJson(response,result);
    }

    @ApiOperation("新增 配置表")
    @RequestMapping(value = "/tabPropertyMap", method = RequestMethod.POST)
    public void addTabPropertyMap(
                @ApiParam(value = "propval (非必传参数)") @RequestParam(required = false) String propval,
            HttpServletResponse response
            ) throws Exception {
        JsonResult<Object> result = new JsonResult<Object>();
        TabPropertyMap tabPropertyMap = new TabPropertyMap();
        if (StringUtil.isNotEmpty(propval)) {
           tabPropertyMap.setPropval(propval);
        }
        //tabPropertyMap.setCreateDate(new Date());
        tabPropertyMapService.addTabPropertyMap(tabPropertyMap);
        result.setCode(ResultCode.SUCCESS.getCode());
        this.renderJson(response,result);
    }

    @ApiOperation("更新 配置表")
    @RequestMapping(value = "/tabPropertyMap/{tabPropertyMapId}", method = RequestMethod.PUT)
    public void updateTabPropertyMapById(
            @ApiParam(value = "配置 - 属性 (必传参数)") @PathVariable String  tabPropertyMapId,
             @ApiParam(value = "propval (非必传参数)") @RequestParam(required = false) String propval,
            HttpServletResponse response
            ) throws Exception {
        JsonResult<Object> result = new JsonResult<Object>();
        TabPropertyMap tabPropertyMap = new TabPropertyMap();
        tabPropertyMap.setTabPropertyMapId(tabPropertyMapId);

         if (StringUtil.isNotEmpty(propval)) {
            tabPropertyMap.setPropval(propval);
         }
        //tabPropertyMap.setUpdateDate(new Date());
        tabPropertyMapService.updateTabPropertyMapById(tabPropertyMap);
        result.setCode(ResultCode.SUCCESS.getCode());
        this.renderJson(response,result);
    }

    @ApiOperation("删除 配置表")
    @RequestMapping(value = "/tabPropertyMap/{tabPropertyMapId}", method = RequestMethod.DELETE)
    public void delTabPropertyMapById(
             @ApiParam(value = "配置 - 属性 (必传参数)") @PathVariable String  tabPropertyMapId,
            HttpServletResponse response
            ) throws Exception {
        JsonResult<Object> result = new JsonResult<Object>();
        tabPropertyMapService.delTabPropertyMapById(tabPropertyMapId);
        result.setCode(ResultCode.SUCCESS.getCode());
        this.renderJson(response,result);
    }

    @ApiOperation("查询 配置表")
    @RequestMapping(value = "/tabPropertyMap/{tabPropertyMapId}", method = RequestMethod.GET)
    public void getTabPropertyMapById(
            @ApiParam(value = "配置 - 属性 (必传参数)") @PathVariable String  tabPropertyMapId,
            HttpServletResponse response) throws Exception {
        JsonResult<Map<String, Object>> result = new JsonResult<Map<String, Object>>();
        Map<String, Object> rs = new HashMap<String, Object>();
        result.setObj(tabPropertyMapService.getTabPropertyMapById(tabPropertyMapId));
        result.setCode(ResultCode.SUCCESS.getCode());
        this.renderJson(response,result);
    }
}

