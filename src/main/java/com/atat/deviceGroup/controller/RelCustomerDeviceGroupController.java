package com.atat.deviceGroup.controller;

import com.atat.common.base.controller.BaseController;
import com.atat.common.bean.JsonResult;
import com.atat.util.IpUtil;
import com.atat.util.StringUtil;
import com.atat.common.bean.ResultCode;
import com.atat.deviceGroup.bean.RelCustomerDeviceGroup;
import com.atat.deviceGroup.service.RelCustomerDeviceGroupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wuhaosoft
 * @version $Id RelCustomerDeviceGroupController.java, 2017-08-10 01:19:29
 *          wuhaosoft Exp
 */
@Api("用户设备分组关系表接口")
@RestController
@RequestMapping("/deviceGroup")
public class RelCustomerDeviceGroupController extends BaseController {

    @Autowired
    private RelCustomerDeviceGroupService relCustomerDeviceGroupService;

    @ApiOperation("查询 所有 用户设备分组关系表 分页")
    @RequestMapping(value = "/relCustomerDeviceGroups", method = RequestMethod.POST)
    public void getRelCustomerDeviceGroupPageTurn(
            @ApiParam(value = "用户Id (非必传参数)") @RequestParam(required = false) Long tabCustomerId,
            @ApiParam(value = "设备分组Id (非必传参数)") @RequestParam(required = false) Long tabDeviceGroupId,
            @ApiParam(value = "组名称 (非必传参数)") @RequestParam(required = false) String groupName,
            @ApiParam(value = "用户是否为设备组拥有着 (非必传参数)") @RequestParam(required = false) Integer isOnwer,
            @ApiParam(value = "是否接受消息推送 (非必传参数)") @RequestParam(required = false) Integer isSendMsg,
            @ApiParam(value = "页码(必传)") @RequestParam Integer pageNo,
            @ApiParam(value = "每页显示多少数据(必传)") @RequestParam Integer pageSize, HttpServletResponse response)
            throws Exception {
        Map<String, Object> rs = new HashMap<String, Object>();
        if (null != tabCustomerId) {
            rs.put("tabCustomerId", tabCustomerId);
        }
        if (null != tabDeviceGroupId) {
            rs.put("tabDeviceGroupId", tabDeviceGroupId);
        }
        if (StringUtil.isNotEmpty(groupName)) {
            rs.put("groupName", "%" + groupName + "%");
        }
        if (null != isOnwer) {
            rs.put("isOnwer", isOnwer);
        }
        if (null != isSendMsg) {
            rs.put("isSendMsg", isSendMsg);
        }
        this.renderJson(response, relCustomerDeviceGroupService.getRelCustomerDeviceGroupPageTurn(rs, pageNo, pageSize));
    }

    @ApiOperation("查询 所有 用户设备分组关系表")
    @RequestMapping(value = "/allRelCustomerDeviceGroups", method = RequestMethod.POST)
    public void selectRelCustomerDeviceGroupList(
            @ApiParam(value = "用户Id (非必传参数)") @RequestParam(required = false) Long tabCustomerId,
            @ApiParam(value = "设备分组Id (非必传参数)") @RequestParam(required = false) Long tabDeviceGroupId,
            @ApiParam(value = "组名称 (非必传参数)") @RequestParam(required = false) String groupName,
            @ApiParam(value = "用户是否为设备组拥有着 (非必传参数)") @RequestParam(required = false) Integer isOnwer,
            @ApiParam(value = "是否接受消息推送 (非必传参数)") @RequestParam(required = false) Integer isSendMsg,
            HttpServletResponse response) throws Exception {
        JsonResult<Object> result = new JsonResult<Object>();
        Map<String, Object> rs = new HashMap<String, Object>();
        if (null != tabCustomerId) {
            rs.put("tabCustomerId", tabCustomerId);
        }
        if (null != tabDeviceGroupId) {
            rs.put("tabDeviceGroupId", tabDeviceGroupId);
        }
        if (StringUtil.isNotEmpty(groupName)) {
            rs.put("groupName", "%" + groupName + "%");
        }
        if (null != isOnwer) {
            rs.put("isOnwer", isOnwer);
        }
        if (null != isSendMsg) {
            rs.put("isSendMsg", isSendMsg);
        }
        result.setObj(relCustomerDeviceGroupService.selectRelCustomerDeviceGroupList(rs));
        result.setCode(ResultCode.SUCCESS.getCode());
        this.renderJson(response, result);
    }

    @ApiOperation("新增 用户设备分组关系表")
    @RequestMapping(value = "/relCustomerDeviceGroup", method = RequestMethod.POST)
    public void addRelCustomerDeviceGroup(
            @ApiParam(value = "用户Id (非必传参数)") @RequestParam(required = false) Long tabCustomerId,
            @ApiParam(value = "设备分组Id (非必传参数)") @RequestParam(required = false) Long tabDeviceGroupId,
            @ApiParam(value = "组名称 (非必传参数)") @RequestParam(required = false) String groupName,
            @ApiParam(value = "用户是否为设备组拥有着 (非必传参数)") @RequestParam(required = false) Integer isOnwer,
            @ApiParam(value = "是否接受消息推送 (非必传参数)") @RequestParam(required = false) Integer isSendMsg,
            HttpServletResponse response) throws Exception {
        JsonResult<Object> result = new JsonResult<Object>();
        RelCustomerDeviceGroup relCustomerDeviceGroup = new RelCustomerDeviceGroup();
        if (null != tabCustomerId) {
            relCustomerDeviceGroup.setTabCustomerId(tabCustomerId);
        }
        if (null != tabDeviceGroupId) {
            relCustomerDeviceGroup.setTabDeviceGroupId(tabDeviceGroupId);
        }
        if (StringUtil.isNotEmpty(groupName)) {
            relCustomerDeviceGroup.setGroupName(groupName);
        }
        if (null != isOnwer) {
            relCustomerDeviceGroup.setIsOnwer(isOnwer);
        }
        if (null != isSendMsg) {
            relCustomerDeviceGroup.setIsSendMsg(isSendMsg);
        }
        relCustomerDeviceGroup.setCreatedDate(new Date());
        relCustomerDeviceGroupService.addRelCustomerDeviceGroup(relCustomerDeviceGroup);
        result.setCode(ResultCode.SUCCESS.getCode());
        this.renderJson(response, result);
    }

    @ApiOperation("更新 用户设备分组关系表")
    @RequestMapping(value = "/relCustomerDeviceGroup/{relCustomerDeviceGroupId}", method = RequestMethod.PUT)
    public void updateRelCustomerDeviceGroupById(
            @ApiParam(value = "用户-设备分组关联Id (必传参数)") @PathVariable Long relCustomerDeviceGroupId,
            @ApiParam(value = "用户Id (非必传参数)") @RequestParam(required = false) Long tabCustomerId,
            @ApiParam(value = "设备分组Id (非必传参数)") @RequestParam(required = false) Long tabDeviceGroupId,
            @ApiParam(value = "组名称 (非必传参数)") @RequestParam(required = false) String groupName,
            @ApiParam(value = "用户是否为设备组拥有着 (非必传参数)") @RequestParam(required = false) Integer isOnwer,
            @ApiParam(value = "是否接受消息推送 (非必传参数)") @RequestParam(required = false) Integer isSendMsg,
            @ApiParam(value = "是否删除 1:是 2:否 (非必传参数)") @RequestParam(required = false) Integer isDeleted,
            HttpServletResponse response) throws Exception {
        JsonResult<Object> result = new JsonResult<Object>();
        RelCustomerDeviceGroup relCustomerDeviceGroup = new RelCustomerDeviceGroup();
        relCustomerDeviceGroup.setRelCustomerDeviceGroupId(relCustomerDeviceGroupId);
        if (null != tabCustomerId) {
            relCustomerDeviceGroup.setTabCustomerId(tabCustomerId);
        }
        if (null != tabDeviceGroupId) {
            relCustomerDeviceGroup.setTabDeviceGroupId(tabDeviceGroupId);
        }
        if (StringUtil.isNotEmpty(groupName)) {
            relCustomerDeviceGroup.setGroupName(groupName);
        }
        if (null != isOnwer) {
            relCustomerDeviceGroup.setIsOnwer(isOnwer);
        }
        if (null != isSendMsg) {
            relCustomerDeviceGroup.setIsSendMsg(isSendMsg);
        }
        if (null != isDeleted) {
            relCustomerDeviceGroup.setIsDeleted(isDeleted);
        }
        relCustomerDeviceGroup.setModifiedDate(new Date());
        relCustomerDeviceGroupService.updateRelCustomerDeviceGroupById(relCustomerDeviceGroup);
        result.setCode(ResultCode.SUCCESS.getCode());
        this.renderJson(response, result);
    }

    @ApiOperation("删除 用户设备分组关系表")
    @RequestMapping(value = "/relCustomerDeviceGroup/{relCustomerDeviceGroupId}", method = RequestMethod.DELETE)
    public void delRelCustomerDeviceGroupById(
            @ApiParam(value = "用户-设备分组关联Id (必传参数)") @PathVariable Long relCustomerDeviceGroupId,
            HttpServletResponse response) throws Exception {
        JsonResult<Object> result = new JsonResult<Object>();
        relCustomerDeviceGroupService.delRelCustomerDeviceGroupById(relCustomerDeviceGroupId);
        result.setCode(ResultCode.SUCCESS.getCode());
        this.renderJson(response, result);
    }

    @ApiOperation("查询 用户设备分组关系表")
    @RequestMapping(value = "/relCustomerDeviceGroup/{relCustomerDeviceGroupId}", method = RequestMethod.GET)
    public void getRelCustomerDeviceGroupById(
            @ApiParam(value = "用户-设备分组关联Id (必传参数)") @PathVariable Long relCustomerDeviceGroupId,
            HttpServletResponse response) throws Exception {
        JsonResult<Map<String, Object>> result = new JsonResult<Map<String, Object>>();
        Map<String, Object> rs = new HashMap<String, Object>();
        result.setObj(relCustomerDeviceGroupService.getRelCustomerDeviceGroupById(relCustomerDeviceGroupId));
        result.setCode(ResultCode.SUCCESS.getCode());
        this.renderJson(response, result);
    }

    @ApiOperation("查询 当前IP下设备")
    @RequestMapping(value = "/findDeviceByIp", method = RequestMethod.GET)
    public void findDeviceByIp(@ApiParam(value = "用户Id (必传参数)") @RequestParam Long tabCustomerId,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        JsonResult<Map<String, Object>> result = new JsonResult<Map<String, Object>>();
        // 获取IP及其他信息
        Map<String, Object> ipInfoMap = IpUtil.getIpInfo(request);
        String ip = (String) ipInfoMap.get("ipNet");
        //ip = "125.38.56.131";
        logger.info("用户当前外网Ip为:" + ip);
        Map<String, Object> resultMap = relCustomerDeviceGroupService.findDeviceByIp(tabCustomerId,ip);
        if (null != resultMap) {
            result.setObj(resultMap);
            result.setCode(ResultCode.SUCCESS.getCode());
        }
        else {
            result.setCode(ResultCode.ERROR.getCode());
            result.setErrorMsg("设备服务请求失败");
        }
        this.renderJson(response, result);
    }

    @ApiOperation("用户新增组 并完成绑定")
    @RequestMapping(value = "/customerAddNewGroup", method = RequestMethod.POST)
    public void customerAddNewGroup(@ApiParam(value = "用户Id (必传参数)") @RequestParam Long tabCustomerId,
            @ApiParam(value = "组名称 (必传参数)") @RequestParam String groupName,
            @ApiParam(value = "设备分组所在地地址 (必传参数)") @RequestParam String address, HttpServletResponse response)
            throws Exception {
        JsonResult<Map<String, Object>> result = new JsonResult<Map<String, Object>>();
        Map<String, Object> groupInfo = relCustomerDeviceGroupService.customerAddNewGroup(tabCustomerId, groupName, address);
        if (null == groupInfo) {
            result.setCode(ResultCode.SYSTEM_ERROR.getCode());
            result.setErrorMsg("创建新组失败");
        }
        else {
            result.setCode(ResultCode.SUCCESS.getCode());
            result.setObj(groupInfo);
        }
        this.renderJson(response, result);
    }

    @ApiOperation("用户给分组绑定设备")
    @RequestMapping(value = "/groupBoundDevice", method = RequestMethod.POST)
    public void groupBoundDevice(
            @ApiParam(value = "用户Id (必传参数)") @RequestParam Long tabCustomerId,
            @ApiParam(value = "设备分组Id (非必传参数)") @RequestParam Long tabDeviceGroupId,
            @ApiParam(value = "设备序列号参数 (必传参数)") @RequestParam String deviceSeriaNumberList, HttpServletResponse response)
            throws Exception {
        JsonResult<Object> result = new JsonResult<Object>();
        Integer rescod = relCustomerDeviceGroupService.groupBoundDevice(tabCustomerId, tabDeviceGroupId, deviceSeriaNumberList);
        if (((Integer)0).equals(rescod)){
            result.setCode(ResultCode.SUCCESS.getCode());
        } else {
            result.setCode(ResultCode.ERROR.getCode());
            result.setErrorMsg("您没有权限添加设备 请联系房屋所有人");
        }
        this.renderJson(response, result);
    }

    /**
     * 用户邀请注册
     * return  1成功 0没有权限
     * @param tabCustomerId
     * @param invitederPhone
     * @param tabDeviceGroupId
     * @param response
     * @throws Exception
     */
    @RequestMapping(params = "action=addGroupByInvite")
    public void addGroupByInvite(
            @ApiParam(value = "邀请人用户Id (必传参数)") @RequestParam Long tabCustomerId,
            @ApiParam(value = "被邀请人手机号 (必传参数)") @RequestParam String invitederPhone,
            @ApiParam(value = "设备分组Id (非必传参数)") @RequestParam Long tabDeviceGroupId,
            HttpServletResponse response) throws Exception {
            JsonResult<Object> result = new JsonResult<Object>();
            Integer rescod = relCustomerDeviceGroupService.addGroupByInvite(tabCustomerId, invitederPhone, tabDeviceGroupId);
        if (((Integer)1).equals(rescod)){
            result.setCode(ResultCode.SUCCESS.getCode());
        } if (((Integer)0).equals(rescod)){
            result.setCode(ResultCode.ERROR.getCode());
            result.setErrorMsg("用户未注册系统 已短信邀请注册");
        } if (((Integer)(-1)).equals(rescod)){
            result.setCode(ResultCode.ERROR.getCode());
            result.setErrorMsg("仅房屋所有人有权限分享设备");
        } else {
            result.setCode(ResultCode.SYSTEM_ERROR.getCode());
        }
        this.renderJson(response, result);
    }

    /**
     * 用户切换微信通知状态
     * @param tabCustomerId
     * @param tabDeviceGroupId
     * @param response
     * @throws IOException
     */
    @RequestMapping(params = "action=switchGroupIsSendMag")
    public void switchGroupIsSendMag(
            @ApiParam(value = "用户Id (必传参数)") @RequestParam Long tabCustomerId,
            @ApiParam(value = "设备分组Id (非必传参数)") @RequestParam Long tabDeviceGroupId,
            HttpServletResponse response) throws IOException {
        JsonResult<Integer> result = new JsonResult<Integer>();
        Integer status = relCustomerDeviceGroupService.switchGroupIsSendMag(tabCustomerId,tabDeviceGroupId);
        if (null != status){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setObj(status);} else {
            result.setCode(ResultCode.SYSTEM_ERROR.getCode());
            result.setErrorMsg("数据异常");
        }
        this.renderJson(response, result);
    }
}
