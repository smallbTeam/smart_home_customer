package com.atat.deviceGroup.controller;

import com.atat.common.base.controller.BaseController;
import com.atat.common.bean.JsonResult;
import com.atat.freshair.bean.TabDeviceFreshair;
import com.atat.freshair.service.TabDeviceFreshairService;
import com.atat.util.StringUtil;
import com.atat.common.bean.ResultCode;
import com.atat.deviceGroup.bean.TabDeviceGroup;
import com.atat.deviceGroup.service.TabDeviceGroupService;
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
 * @version $Id TabDeviceGroupController.java, 2017-08-10 01:19:29 wuhaosoft Exp
 *
 */
@Api("设备分组表(家)接口")
@RestController
@RequestMapping("/deviceGroup")
public class TabDeviceGroupController  extends BaseController {

    @Autowired
    private TabDeviceGroupService tabDeviceGroupService;

    @Autowired
    private TabDeviceFreshairService tabDeviceFreshairService;

    @ApiOperation("查询 所有 设备分组表(家) 分页")
    @RequestMapping(value = "/tabDeviceGroups", method = RequestMethod.POST)
    public void getTabDeviceGroupPageTurn(
            @ApiParam(value = "系统分组编号 (非必传参数)") @RequestParam(required = false) String uid,
            @ApiParam(value = "设备分组所在地地址 (非必传参数)") @RequestParam(required = false) String address,
            @ApiParam(value = "页码(必传)") @RequestParam Integer pageNo,
            @ApiParam(value = "每页显示多少数据(必传)") @RequestParam Integer pageSize,
            HttpServletResponse response) throws Exception {
        Map<String, Object> rs = new HashMap<String, Object>();
        if (StringUtil.isNotEmpty(uid)) {
           rs.put("uid", "%" + uid + "%");
        }
        if (StringUtil.isNotEmpty(address)) {
           rs.put("address", "%" + address + "%");
        }
        this.renderJson(response,tabDeviceGroupService.getTabDeviceGroupPageTurn(rs, pageNo, pageSize));
    }

    @ApiOperation("查询 所有 设备分组表(家)")
    @RequestMapping(value = "/allTabDeviceGroups", method = RequestMethod.POST)
    public void selectTabDeviceGroupList(
        @ApiParam(value = "系统分组编号 (非必传参数)") @RequestParam(required = false) String uid,
        @ApiParam(value = "设备分组所在地地址 (非必传参数)") @RequestParam(required = false) String address,
            HttpServletResponse response
        )throws Exception {
        JsonResult<Object> result = new JsonResult<Object>();
        Map<String, Object> rs = new HashMap<String, Object>();
         if (StringUtil.isNotEmpty(uid)) {
            rs.put("uid", "%" + uid + "%");
         }
         if (StringUtil.isNotEmpty(address)) {
            rs.put("address", "%" + address + "%");
         }
        result.setObj(tabDeviceGroupService.selectTabDeviceGroupList(rs));
        result.setCode(ResultCode.SUCCESS.getCode());
        this.renderJson(response,result);
    }

    @ApiOperation("新增 设备分组表(家)")
    @RequestMapping(value = "/tabDeviceGroup", method = RequestMethod.POST)
    public void addTabDeviceGroup(
                @ApiParam(value = "系统分组编号 (非必传参数)") @RequestParam(required = false) String uid,
                @ApiParam(value = "设备分组所在地地址 (非必传参数)") @RequestParam(required = false) String address,
            HttpServletResponse response
            ) throws Exception {
        JsonResult<Object> result = new JsonResult<Object>();
        TabDeviceGroup tabDeviceGroup = new TabDeviceGroup();
        if (StringUtil.isNotEmpty(uid)) {
           tabDeviceGroup.setUid(uid);
        }
        if (StringUtil.isNotEmpty(address)) {
           tabDeviceGroup.setAddress(address);
        }
        tabDeviceGroup.setCreatedDate(new Date());
        tabDeviceGroupService.addTabDeviceGroup(tabDeviceGroup);
        result.setCode(ResultCode.SUCCESS.getCode());
        this.renderJson(response,result);
    }

    @ApiOperation("更新 设备分组表(家)")
    @RequestMapping(value = "/tabDeviceGroup/{tabDeviceGroupId}", method = RequestMethod.PUT)
    public void updateTabDeviceGroupById(
            @ApiParam(value = "设备分组ID (必传参数)") @PathVariable Long  tabDeviceGroupId,
             @ApiParam(value = "系统分组编号 (非必传参数)") @RequestParam(required = false) String uid,
             @ApiParam(value = "设备分组所在地地址 (非必传参数)") @RequestParam(required = false) String address,
             @ApiParam(value = "是否删除 1:是 2:否 (非必传参数)") @RequestParam(required = false) Integer isDeleted,
            HttpServletResponse response
            ) throws Exception {
        JsonResult<Object> result = new JsonResult<Object>();
        TabDeviceGroup tabDeviceGroup = new TabDeviceGroup();
        tabDeviceGroup.setTabDeviceGroupId(tabDeviceGroupId);

         if (StringUtil.isNotEmpty(uid)) {
            tabDeviceGroup.setUid(uid);
         }
         if (StringUtil.isNotEmpty(address)) {
            tabDeviceGroup.setAddress(address);
         }
         if (null != isDeleted) {
            tabDeviceGroup.setIsDeleted(isDeleted);
         }
        tabDeviceGroup.setModifiedDate(new Date());
        tabDeviceGroupService.updateTabDeviceGroupById(tabDeviceGroup);
        result.setCode(ResultCode.SUCCESS.getCode());
        this.renderJson(response,result);
    }

    @ApiOperation("删除 设备分组表(家)")
    @RequestMapping(value = "/tabDeviceGroup/{tabDeviceGroupId}", method = RequestMethod.DELETE)
    public void delTabDeviceGroupById(
             @ApiParam(value = "设备分组ID (必传参数)") @PathVariable Long  tabDeviceGroupId,
            HttpServletResponse response
            ) throws Exception {
        JsonResult<Object> result = new JsonResult<Object>();
        tabDeviceGroupService.delTabDeviceGroupById(tabDeviceGroupId);
        result.setCode(ResultCode.SUCCESS.getCode());
        this.renderJson(response,result);
    }

    @ApiOperation("查询 设备分组表(家) 以及分组下所有设备")
    @RequestMapping(value = "/tabDeviceInGroup/{tabDeviceGroupId}", method = RequestMethod.GET)
    public void getTabDeviceGroupById(
            @ApiParam(value = "设备分组ID (必传参数)") @PathVariable Long  tabDeviceGroupId,
            HttpServletResponse response) throws Exception {
        JsonResult<Map<String, Object>> result = new JsonResult<Map<String, Object>>();
        Map<String, Object> rs = new HashMap<String, Object>();
        result.setObj(tabDeviceGroupService.getTabDeviceGroupById(tabDeviceGroupId));
        result.setCode(ResultCode.SUCCESS.getCode());
        this.renderJson(response,result);
    }
}

