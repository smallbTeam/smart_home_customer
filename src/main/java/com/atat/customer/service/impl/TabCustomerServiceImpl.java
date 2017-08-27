package com.atat.customer.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.atat.customer.bean.TabCustomer;
import com.atat.customer.dao.TabCustomerDao;
import com.atat.customer.service.TabCustomerService;
import com.atat.util.CollectionUtil;
import com.atat.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * @author whaosoft
 *
 */
@Service
@Transactional
public class TabCustomerServiceImpl implements TabCustomerService {

    @Autowired
    private TabCustomerDao tabCustomerDao;

    private String daoNamespace = TabCustomerDao.class.getName();

    @Override
    public void  addTabCustomer(TabCustomer tabCustomer) {
        tabCustomerDao.addTabCustomer(tabCustomer);
    }

    @Override
    public void  updateTabCustomerById(TabCustomer tabCustomer) {
        tabCustomerDao.updateTabCustomerById(tabCustomer);
    }

    @Override
    public List<Map<String, Object>> selectTabCustomerList(Map<String, Object> param) {
        return tabCustomerDao.selectTabCustomerList(param);
    }

    @Override
    public PageInfo<Map<String, Object>> getTabCustomerPageTurn(Map<String, Object> param, Integer pageNo, Integer pageSize) {
        pageNo = pageNo == null?1:pageNo;
        pageSize = pageSize == null?10:pageSize;
        PageHelper.startPage(pageNo, pageSize);
        List<Map<String,Object>> list =  tabCustomerDao.selectTabCustomerList(param);
        //用PageInfo对结果进行包装
        PageInfo<Map<String,Object>> page = new PageInfo<Map<String,Object>>(list);
        return page;
    }

    @Override
    public Map<String, Object> getTabCustomerById(Long tabCustomerId) {
        Map<String, Object> tabCustomerinfo = new HashMap<String, Object>();
        Map<String, Object> rs = new HashMap<String, Object>();
        rs.put("tabCustomerId", tabCustomerId);
        List<Map<String, Object>> tabCustomerList = tabCustomerDao.selectTabCustomerList(rs);
        if ((null != tabCustomerList) && (tabCustomerList.size() > 0)) {
            tabCustomerinfo = tabCustomerList.get(0);
        }
        return tabCustomerinfo;
    }

    @Override
    public void delTabCustomerById(Long tabCustomerId) {
     TabCustomer tabCustomer = new TabCustomer();
     tabCustomer.setIsDeleted(1);
     tabCustomer.setTabCustomerId(tabCustomerId);
     tabCustomerDao.updateTabCustomerById(tabCustomer);
    }

    @Override public Map<String, Object> getCustomerByWxId(String wxId) {
        Map<String, Object> tabCustomerinfo = new HashMap<String, Object>();
        Map<String, Object> rs = new HashMap<String, Object>();
        rs.put("wxId", wxId);
        List<Map<String, Object>> tabCustomerList = tabCustomerDao.selectTabCustomerList(rs);
        if ((null != tabCustomerList) && (tabCustomerList.size() > 0)) {
            tabCustomerinfo = tabCustomerList.get(0);
        }
        return tabCustomerinfo;
    }

    @Override public Integer accountLogin(String mobelPhone, String password, String wxId) {
        Map<String, Object> loginRes = new HashMap<String, Object>();
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("mobelPhone",mobelPhone);
        param.put("password",password);
        List<Map<String, Object>> customerList = tabCustomerDao.selectTabCustomerList(param);
        if (CollectionUtil.isNotEmpty(customerList)){
            if (StringUtil.isNotEmpty(wxId)) {
                Map<String, Object> customerMap = customerList.get(0);
                //绑定wxId
                TabCustomer customer = new TabCustomer();
                customer.setTabCustomerId(Long.parseLong(customerMap.get("tabCustomerId").toString()));
                customer.setWxId(wxId);
                tabCustomerDao.updateTabCustomerById(customer);
            }
            return 1;
        } else {
            return 0;
        }
    }

    @Override public Map<String, Object> getCustomerByMobelPhone(String mobelPhone) {
        Map<String, Object> customerinfo = new HashMap<String, Object>();
        Map<String, Object> rs = new HashMap<String, Object>();
        rs.put("mobelPhone", mobelPhone);
        List<Map<String, Object>> customerList = tabCustomerDao.selectTabCustomerList(rs);
        if ((null != customerList) && (customerList.size() > 0)) {
            customerinfo = customerList.get(0);
        }
        return customerinfo;
    }
}
