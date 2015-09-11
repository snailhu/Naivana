package com.nirvana.service.pojo.web;

import java.util.List;
import java.util.Map;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.nirvana.domain.store.Store;

/**
 * 封装商户信息的Dto
 * 
 * @author Administrator
 * 
 */
public class DealerInfoData {
	// 经销商编号
	private String delaerCode;

	// 经销商名称
	private String dealerName;

	// ERP经销商代码
	private String codeInERP;

	// 联系人
	private String manager;

	// 选定的大区 id
	private Integer bigAreaSelectId;

	// 大区集合
	private Map<Integer, String> bigeAreaMap;

	// 选定的渠道 id
	private Integer channelSelectId;

	// 渠道集合
	private Map<Integer, String> channelMap;

	// 地址详细信息
	private String addrDetail;

	// 邮政编码
	private String postalCode;

	// 电话手机号码
	private String phoneNum;

	// 邮箱
	private String email;

	// 传真
	private String faxNum;

	// 是否有效
	private boolean isValide;

	// 密码
	private String password;

	//
	private List<Store> storeList;

	public List<Store> getStoreList() {
		return storeList;
	}

	public void setStoreList(List<Store> storeList) {
		this.storeList = storeList;
	}

	@NotEmpty(message = "编号不能为空")
	public String getDelaerCode() {
		return delaerCode;
	}

	public void setDelaerCode(String delaerCode) {
		this.delaerCode = delaerCode;
	}

	@NotEmpty(message = "用户名不能为空")
	public String getDealerName() {
		return dealerName;
	}

	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}

	public String getCodeInERP() {
		return codeInERP;
	}

	public void setCodeInERP(String codeInERP) {
		this.codeInERP = codeInERP;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public Integer getBigAreaSelectId() {
		return bigAreaSelectId;
	}

	public void setBigAreaSelectId(Integer bigAreaSelectId) {
		this.bigAreaSelectId = bigAreaSelectId;
	}

	public Map<Integer, String> getBigeAreaMap() {
		return bigeAreaMap;
	}

	public void setBigeAreaMap(Map<Integer, String> bigeAreaMap) {
		this.bigeAreaMap = bigeAreaMap;
	}

	public Integer getChannelSelectId() {
		return channelSelectId;
	}

	public void setChannelSelectId(Integer channelSelectId) {
		this.channelSelectId = channelSelectId;
	}

	public Map<Integer, String> getChannelMap() {
		return channelMap;
	}

	public void setChannelMap(Map<Integer, String> channelMap) {
		this.channelMap = channelMap;
	}

	public String getAddrDetail() {
		return addrDetail;
	}

	public void setAddrDetail(String addrDetail) {
		this.addrDetail = addrDetail;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	@Email(message = "邮件格式不正确")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFaxNum() {
		return faxNum;
	}

	public void setFaxNum(String faxNum) {
		this.faxNum = faxNum;
	}

	public boolean isValide() {
		return isValide;
	}

	public void setValide(boolean isValide) {
		this.isValide = isValide;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
