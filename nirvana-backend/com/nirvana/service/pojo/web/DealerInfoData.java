package com.nirvana.service.pojo.web;

import java.util.List;
import java.util.Map;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.nirvana.domain.store.Store;

/**
 * ��װ�̻���Ϣ��Dto
 * 
 * @author Administrator
 * 
 */
public class DealerInfoData {
	// �����̱��
	private String delaerCode;

	// ����������
	private String dealerName;

	// ERP�����̴���
	private String codeInERP;

	// ��ϵ��
	private String manager;

	// ѡ���Ĵ��� id
	private Integer bigAreaSelectId;

	// ��������
	private Map<Integer, String> bigeAreaMap;

	// ѡ�������� id
	private Integer channelSelectId;

	// ��������
	private Map<Integer, String> channelMap;

	// ��ַ��ϸ��Ϣ
	private String addrDetail;

	// ��������
	private String postalCode;

	// �绰�ֻ�����
	private String phoneNum;

	// ����
	private String email;

	// ����
	private String faxNum;

	// �Ƿ���Ч
	private boolean isValide;

	// ����
	private String password;

	//
	private List<Store> storeList;

	public List<Store> getStoreList() {
		return storeList;
	}

	public void setStoreList(List<Store> storeList) {
		this.storeList = storeList;
	}

	@NotEmpty(message = "��Ų���Ϊ��")
	public String getDelaerCode() {
		return delaerCode;
	}

	public void setDelaerCode(String delaerCode) {
		this.delaerCode = delaerCode;
	}

	@NotEmpty(message = "�û�������Ϊ��")
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

	@Email(message = "�ʼ���ʽ����ȷ")
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
