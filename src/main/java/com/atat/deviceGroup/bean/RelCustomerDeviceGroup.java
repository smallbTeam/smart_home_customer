package com.atat.deviceGroup.bean;

import java.util.Date;
/**
 * 用户设备分组关系表
 * @author wuhaosoft
 * @version $Id RelCustomerDeviceGroup.java, 2017-08-10 01:19:29 wuhaosoft Exp
 *
 */
public class RelCustomerDeviceGroup {

    //用户-设备分组关联Id
    private Long  relCustomerDeviceGroupId;

    //用户Id
    private Long tabCustomerId;

    //设备分组Id
    private Long tabDeviceGroupId;

    //组名称
    private String groupName;

    //用户是否为设备组拥有着
    private Integer isOnwer;

    //是否接受消息推送
    private Integer isSendMsg;

    //创建时间
    private Date createdDate;

    //修改时间
    private Date modifiedDate;

    //是否删除 1:是 2:否
    private Integer isDeleted;

    public RelCustomerDeviceGroup(){}

    public RelCustomerDeviceGroup(
        Long  relCustomerDeviceGroupId,
        Long tabCustomerId,
        Long tabDeviceGroupId,
        String groupName,
        Integer isOnwer,
        Integer isSendMsg,
        Date createdDate,
        Date modifiedDate,
        Integer isDeleted
    ) {
        this.tabCustomerId = tabCustomerId;
        this.tabDeviceGroupId = tabDeviceGroupId;
        this.groupName = groupName;
        this.isOnwer = isOnwer;
        this.isSendMsg = isSendMsg;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.isDeleted = isDeleted;
    }

    public Long  getRelCustomerDeviceGroupId(){
        return relCustomerDeviceGroupId;
    }

    public void setRelCustomerDeviceGroupId(Long  relCustomerDeviceGroupId) {
        this.relCustomerDeviceGroupId = relCustomerDeviceGroupId;
    }

    public Long getTabDeviceGroupId() {
        return tabDeviceGroupId;
    }

    public void setTabDeviceGroupId(Long tabDeviceGroupId) {
        this.tabDeviceGroupId = tabDeviceGroupId;
    }

    public Long getTabCustomerId() {
        return tabCustomerId;
    }

    public void setTabCustomerId(Long tabCustomerId) {
        this.tabCustomerId = tabCustomerId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String  groupName) {
        this.groupName = groupName;
    }

    public Integer getIsOnwer() {
        return isOnwer;
    }

    public void setIsOnwer(Integer  isOnwer) {
        this.isOnwer = isOnwer;
    }

    public Integer getIsSendMsg() {
        return isSendMsg;
    }

    public void setIsSendMsg(Integer  isSendMsg) {
        this.isSendMsg = isSendMsg;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date  createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date  modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer  isDeleted) {
        this.isDeleted = isDeleted;
    }

}



