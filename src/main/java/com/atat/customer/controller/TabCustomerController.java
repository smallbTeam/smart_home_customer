package com.atat.customer.controller;

import com.atat.common.base.controller.BaseController;
import com.atat.common.bean.JsonResult;
import com.atat.util.CollectionUtil;
import com.atat.util.StringUtil;
import com.atat.common.bean.ResultCode;
import com.atat.customer.bean.TabCustomer;
import com.atat.customer.service.TabCustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wuhaosoft
 * @version $Id TabCustomerController.java, 2017-08-10 00:19:02 wuhaosoft Exp
 */
@Api("用户表接口")
@RestController
@RequestMapping("/customer")
public class TabCustomerController  extends BaseController {

    @Autowired
    private TabCustomerService tabCustomerService;

    @ApiOperation("用户登录 0 手机号或密码错误 1 成功")
    @RequestMapping(value = "/accountLogin", method = RequestMethod.POST)
    public void accountLogin(@ApiParam(value = "用户名(必传)") @RequestParam String mobelPhone,
            @ApiParam(value = "密码(必传)") @RequestParam String password,
            @ApiParam(value = "微信Id(非必传 传的话表示绑定)") @RequestParam String wxId,
            HttpServletResponse response) throws Exception {
        JsonResult<Object> result = new JsonResult<Object>();
        try {
            Integer loginRes = tabCustomerService.accountLogin(mobelPhone, password, wxId);
            result.setCode(ResultCode.SUCCESS.getCode());
            result.setObj(loginRes);
        } catch (Exception e) {
            result.setCode(ResultCode.ERROR.getCode());
            result.setErrorMsg(ResultCode.ERROR.getInfo());
        }
        this.renderJson(response,result);
    }

    @ApiOperation("依据手机号较验账户是否存在")
    @RequestMapping(value = "/accountIsExit/{mobelPhone}", method = RequestMethod.GET)
    public void accountIsExit(@ApiParam(value = "用户手机号 (必传参数)") @PathVariable String mobelPhone,
            HttpServletResponse response)
            throws IOException {
        JsonResult<Map<String, Object>> result = new JsonResult<Map<String, Object>>();
        Map<String, Object> rs = new HashMap<String, Object>();
            try {
                // 依据手机号查询用户是否已存在
                Map<String, Object> customer = tabCustomerService.getCustomerByMobelPhone(mobelPhone);
                if (CollectionUtil.isNotEmpty(customer)) {
                    result.setCode(ResultCode.SUCCESS.getCode());
                    rs.put("isExit", true);
                    rs.put("customer", customer);
                    result.setObj(rs);
                }
                else {
                    result.setCode(ResultCode.SUCCESS.getCode());
                    rs.put("isExit", false);
                    result.setObj(rs);
                }
            }
            catch (NumberFormatException e) {
                result.setCode(ResultCode.ERROR.getCode());
                result.setErrorMsg(ResultCode.ERROR.getInfo());
            }
        this.renderJson(response,result);
    }

    /**
     *
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @ApiOperation("用户更换手机号")
    @RequestMapping(value = "/accountUpdateMobile", method = RequestMethod.POST)
    public void accountUpdateMobile(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        JsonResult<Object> result = new JsonResult<Object>();
        String newMobelPhone = request.getParameter("newMobelPhone");
        String tabCustomerId = request.getParameter("tabCustomerId");
        String veridateMsg = request.getParameter("veridateMsg");
        if ((StringUtil.isNotEmpty(veridateMsg)) && (StringUtil.isNotEmpty(newMobelPhone))
                && (StringUtil.isNotEmpty(tabCustomerId))) {
            // 验证session
            String msmRandomCode = (String) request.getSession().getAttribute("msgCodeSsion" + newMobelPhone);
            String randomCode = "";
            String timeStamp = "";
            long timePath = 600001;
            if (StringUtil.isNotEmpty(msmRandomCode)) {
                String temp[] = msmRandomCode.split("&&");
                randomCode = temp[0];
                timeStamp = temp[1];
            }
            // 计算时间差
            if ((StringUtil.isNotEmpty(randomCode)) && (StringUtil.isNotEmpty(timeStamp))) {
                timePath = (Long) ((new Date()).getTime()) - Long.parseLong(timeStamp);
            }
            if ((StringUtil.isNotEmpty(randomCode)) && (veridateMsg.equals(randomCode)) && (timePath < 600000)) {
                // 结果返回前台
                try {
                    // 依据手机号查询用户是否已存在
                    Map<String, Object> customer = tabCustomerService.getCustomerByMobelPhone(newMobelPhone);
                    if (CollectionUtil.isEmpty(customer)) {
                        Map<String, Object> param = new HashMap<String, Object>();
                        TabCustomer tabCustomer = new TabCustomer();
                        tabCustomer.setTabCustomerId(Long.parseLong(tabCustomerId));
                        tabCustomer.setMobelPhone(newMobelPhone);
                        tabCustomerService.updateTabCustomerById(tabCustomer);
                        result.setCode(ResultCode.SUCCESS.getCode());
                        result.setObj(1);
                    }
                    else {
                        result.setCode(ResultCode.SUCCESS.getCode());
                        result.setObj(0);
                    }
                }
                catch (NumberFormatException e) {
                    logger.error("依据手机号查询用户是否已存在出错" + e, e);
                    result.setCode(ResultCode.SYSTEM_ERROR.getCode());
                    result.setErrorMsg(ResultCode.SYSTEM_ERROR.getInfo());
                }
            }
            else {
                result.setCode(ResultCode.ERROR.getCode());
                result.setErrorMsg("验证码错误");
            }
        }
        else {
            result.setCode(ResultCode.PARAM_EMPTY_ERROR.getCode());
            result.setErrorMsg("重要参数为空");
        }
        this.renderJson(response,result);
    }

    @ApiOperation("查询 所有 用户表 分页")
    @RequestMapping(value = "/tabCustomers", method = RequestMethod.POST)
    public void getTabCustomerPageTurn(
            @ApiParam(value = "电话 (非必传参数)") @RequestParam(required = false) String mobelPhone,
            @ApiParam(value = "密码 (非必传参数)") @RequestParam(required = false) String password,
            @ApiParam(value = "微信Id (非必传参数)") @RequestParam(required = false) String wxId,
            @ApiParam(value = "昵称 (非必传参数)") @RequestParam(required = false) String nickName,
            @ApiParam(value = "出生日期 (非必传参数 日期格式:yyyy-MM-dd HH)") @RequestParam(required = false) @DateTimeFormat(
                    pattern = "yyyy-MM-dd HH") Date birthday,
            @ApiParam(value = "性别 1男 2 女 0其他 (非必传参数)") @RequestParam(required = false) Integer sex,
            @ApiParam(value = "token (非必传参数)") @RequestParam(required = false) String token,
            @ApiParam(value = "页码(必传)") @RequestParam Integer pageNo,
            @ApiParam(value = "每页显示多少数据(必传)") @RequestParam Integer pageSize,
            HttpServletResponse response) throws Exception {
        Map<String, Object> rs = new HashMap<String, Object>();
        if (StringUtil.isNotEmpty(mobelPhone)) {
            rs.put("mobelPhone", "%" + mobelPhone + "%");
        }
        if (StringUtil.isNotEmpty(password)) {
            rs.put("password", "%" + password + "%");
        }
        if (StringUtil.isNotEmpty(wxId)) {
            rs.put("wxId", "%" + wxId + "%");
        }
        if (StringUtil.isNotEmpty(nickName)) {
            rs.put("nickName", "%" + nickName + "%");
        }
        if (null != birthday) {
            rs.put("birthday", birthday);
        }
        if (null != sex) {
            rs.put("sex", sex);
        }
        if (StringUtil.isNotEmpty(token)) {
            rs.put("token", "%" + token + "%");
        }
        this.renderJson(response,tabCustomerService.getTabCustomerPageTurn(rs, pageNo, pageSize));
    }

    @ApiOperation("查询 所有 用户表")
    @RequestMapping(value = "/allTabCustomers", method = RequestMethod.POST)
    public void selectTabCustomerList(
            @ApiParam(value = "电话 (非必传参数)") @RequestParam(required = false) String mobelPhone,
            @ApiParam(value = "密码 (非必传参数)") @RequestParam(required = false) String password,
            @ApiParam(value = "微信Id (非必传参数)") @RequestParam(required = false) String wxId,
            @ApiParam(value = "昵称 (非必传参数)") @RequestParam(required = false) String nickName,
            @ApiParam(value = "出生日期 (非必传参数 日期格式:yyyy-MM-dd HH)") @RequestParam(required = false) @DateTimeFormat(
                    pattern = "yyyy-MM-dd HH") Date birthday,
            @ApiParam(value = "性别 1男 2 女 0其他 (非必传参数)") @RequestParam(required = false) Integer sex,
            @ApiParam(value = "token (非必传参数)") @RequestParam(required = false) String token,
            HttpServletResponse response) throws Exception {
        JsonResult<Object> result = new JsonResult<Object>();
        Map<String, Object> rs = new HashMap<String, Object>();
        if (StringUtil.isNotEmpty(mobelPhone)) {
            rs.put("mobelPhone", "%" + mobelPhone + "%");
        }
        if (StringUtil.isNotEmpty(password)) {
            rs.put("password", "%" + password + "%");
        }
        if (StringUtil.isNotEmpty(wxId)) {
            rs.put("wxId", "%" + wxId + "%");
        }
        if (StringUtil.isNotEmpty(nickName)) {
            rs.put("nickName", "%" + nickName + "%");
        }
        if (null != birthday) {
            rs.put("birthday", birthday);
        }
        if (null != sex) {
            rs.put("sex", sex);
        }
        if (StringUtil.isNotEmpty(token)) {
            rs.put("token", "%" + token + "%");
        }
        result.setObj(tabCustomerService.selectTabCustomerList(rs));
        result.setCode(ResultCode.SUCCESS.getCode());
        this.renderJson(response,result);
    }

    @ApiOperation("新增 用户表")
    @RequestMapping(value = "/tabCustomer", method = RequestMethod.POST)
    public void addTabCustomer(@ApiParam(value = "电话 (必传参数)") @RequestParam String mobelPhone,
            @ApiParam(value = "密码 (必传参数)") @RequestParam String password,
            @ApiParam(value = "微信Id (非必传参数)") @RequestParam(required = false) String wxId,
            @ApiParam(value = "昵称 (非必传参数)") @RequestParam String nickName,
            @ApiParam(value = "出生日期 (必传参数日期格式:yyyy-MM-dd HH)") @RequestParam @DateTimeFormat(
                    pattern = "yyyy-MM-dd HH") Date birthday,
            @ApiParam(value = "性别 1男 2 女 0其他 (必传参数)") @RequestParam Integer sex,
            @ApiParam(value = "token (非必传参数)") @RequestParam(required = false) String token,
            HttpServletResponse response) throws Exception {
        JsonResult<Object> result = new JsonResult<Object>();
        TabCustomer tabCustomer = new TabCustomer();
        tabCustomer.setMobelPhone(mobelPhone);
        tabCustomer.setPassword(password);
        if (StringUtil.isNotEmpty(wxId)) {
            tabCustomer.setWxId(wxId);
        }
        tabCustomer.setNickName(nickName);
        tabCustomer.setBirthday(birthday);
        tabCustomer.setSex(sex);
        if (StringUtil.isNotEmpty(token)) {
            tabCustomer.setToken(token);
        }
        tabCustomer.setCreatedDate(new Date());
        //新增用户
        tabCustomerService.addTabCustomer(tabCustomer);
        //给用户默认绑定分组

        result.setCode(ResultCode.SUCCESS.getCode());
        result.setObj(mobelPhone);
        this.renderJson(response,result);
    }

    @ApiOperation("更新 用户表")
    @RequestMapping(value = "/tabCustomer/{tabCustomerId}", method = RequestMethod.PUT)
    public void updateTabCustomerById(
            @ApiParam(value = "用户ID (必传参数)") @PathVariable Long tabCustomerId,
            @ApiParam(value = "电话 (非必传参数)") @RequestParam(required = false) String mobelPhone,
            @ApiParam(value = "密码 (非必传参数)") @RequestParam(required = false) String password,
            @ApiParam(value = "微信Id (非必传参数)") @RequestParam(required = false) String wxId,
            @ApiParam(value = "昵称 (非必传参数)") @RequestParam(required = false) String nickName,
            @ApiParam(value = "出生日期 (非必传参数 日期格式:yyyy-MM-dd HH)") @RequestParam(required = false) @DateTimeFormat(
                    pattern = "yyyy-MM-dd HH") Date birthday,
            @ApiParam(value = "性别 1男 2 女 0其他 (非必传参数)") @RequestParam(required = false) Integer sex,
            @ApiParam(value = "token (非必传参数)") @RequestParam(required = false) String token,
            @ApiParam(value = "是否删除 1:是 2:否 (非必传参数)") @RequestParam(required = false) Integer isDeleted,
            HttpServletResponse response)
            throws Exception {
        JsonResult<Object> result = new JsonResult<Object>();
        TabCustomer tabCustomer = new TabCustomer();
        tabCustomer.setTabCustomerId(tabCustomerId);
        if (StringUtil.isNotEmpty(mobelPhone)) {
            tabCustomer.setMobelPhone(mobelPhone);
        }
        if (StringUtil.isNotEmpty(password)) {
            tabCustomer.setPassword(password);
        }
        if (StringUtil.isNotEmpty(wxId)) {
            tabCustomer.setWxId(wxId);
        }
        if (StringUtil.isNotEmpty(nickName)) {
            tabCustomer.setNickName(nickName);
        }
        if (null != birthday) {
            tabCustomer.setBirthday(birthday);
        }
        if (null != sex) {
            tabCustomer.setSex(sex);
        }
        if (StringUtil.isNotEmpty(token)) {
            tabCustomer.setToken(token);
        }
        if (null != isDeleted) {
            tabCustomer.setIsDeleted(isDeleted);
        }
        tabCustomer.setModifiedDate(new Date());
        tabCustomerService.updateTabCustomerById(tabCustomer);
        result.setCode(ResultCode.SUCCESS.getCode());
        this.renderJson(response,result);
    }

    @ApiOperation("删除 用户表")
    @RequestMapping(value = "/tabCustomer/{tabCustomerId}", method = RequestMethod.DELETE)
    public void delTabCustomerById(@ApiParam(value = "用户ID (必传参数)") @PathVariable Long tabCustomerId,
            HttpServletResponse response)
            throws Exception {
        JsonResult<Object> result = new JsonResult<Object>();
        tabCustomerService.delTabCustomerById(tabCustomerId);
        result.setCode(ResultCode.SUCCESS.getCode());
        this.renderJson(response,result);
    }

    @ApiOperation("查询 用户表")
    @RequestMapping(value = "/tabCustomer/{tabCustomerId}", method = RequestMethod.GET)
    public void getTabCustomerById(@ApiParam(value = "用户ID (必传参数)") @PathVariable Long tabCustomerId,
            HttpServletResponse response) throws Exception {
        JsonResult<Map<String, Object>> result = new JsonResult<Map<String, Object>>();
        Map<String, Object> rs = new HashMap<String, Object>();
        result.setObj(tabCustomerService.getTabCustomerById(tabCustomerId));
        result.setCode(ResultCode.SUCCESS.getCode());
        this.renderJson(response,result);
    }
}
