package com.nirvana.domain.store;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import com.nirvana.domain.backend.AgentStoreCategory;
import com.nirvana.domain.backend.VisitRouteNode;
import com.nirvana.domain.backend.area.AgentArea;
import com.nirvana.domain.common.Channel;
import com.nirvana.domain.common.Device;
import com.nirvana.domain.dealer.Dealer;
import com.nirvana.domain.dealer.DealerStoreCategory;
import com.nirvana.domain.store.usersys.StoreUser;

/**
 * һ���ŵ�ʵ�塣
 * 
 * */
@Entity
@Table(name = "store_store")
public class Store {
	/** �ŵ�ID */
	@Id
	@GeneratedValue
	private Long id;

	/** �������ŵ��û� */
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "store")
	private StoreUser user;

	/** ERP�еĿͻ���� */
	@Column(length = 10, unique = true)
	private String codeInERP;

	/** ����CR����С���� */
	@ManyToOne
	@JoinColumn(name = "area_id")
	private AgentArea agentArea;

	/** ���׾����� */
	@ManyToOne
	@JoinColumn(name = "dealer_id")
	private Dealer dealer;

	/** �ŵ������Ϣ */
	@OneToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "storefrontinfo_id")
	private StoreStorefrontInfo storefrontInfo;

	/** �ŵ���ҵ��Ա�µķ��� */
	@ManyToOne
	@JoinColumn(name = "agentcategory_id")
	private AgentStoreCategory agentCategory;

	/** �ŵ��ھ������µķ��� */
	@ManyToOne
	@JoinColumn(name = "dealercategory_id")
	private DealerStoreCategory dealerCategory;

	/** ���� */
	@ManyToOne
	@JoinColumn(name = "channel_id")
	private Channel channel;

	/** �豸 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "store_store_device", joinColumns = @JoinColumn(name = "store_id"), inverseJoinColumns = @JoinColumn(name = "device_id"))
	private Set<Device> devices;

	/** �������ŵ��·�ߡ� */
	@OneToMany
	@JoinColumn(name = "store_id")
	private Set<VisitRouteNode> nodes;

	/** ���� */
	private String notes;

	/** �Ƿ�ر� */
	@Column(nullable = false)
	private Boolean isClose = false;

	/** �ر�ԭ�� */
	private String closeReason;

	/** �ر����� */
	@Temporal(TemporalType.TIMESTAMP)
	private Date closeDate;

	@Version
	private Integer version;

	public Store() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public StoreUser getUser() {
		return user;
	}

	public void setUser(StoreUser user) {
		this.user = user;
	}

	public Dealer getDealer() {
		return dealer;
	}

	public void setDealer(Dealer dealer) {
		this.dealer = dealer;
	}

	public String getCodeInERP() {
		return codeInERP;
	}

	public void setCodeInERP(String codeInERP) {
		this.codeInERP = codeInERP;
	}

	public AgentArea getAgentArea() {
		return agentArea;
	}

	public void setAgentArea(AgentArea agentArea) {
		this.agentArea = agentArea;
	}

	public StoreStorefrontInfo getStorefrontInfo() {
		return storefrontInfo;
	}

	public void setStorefrontInfo(StoreStorefrontInfo storefrontInfo) {
		this.storefrontInfo = storefrontInfo;
	}

	public AgentStoreCategory getAgentCategory() {
		return agentCategory;
	}

	public void setAgentCategory(AgentStoreCategory agentCategory) {
		this.agentCategory = agentCategory;
	}

	public DealerStoreCategory getDealerCategory() {
		return dealerCategory;
	}

	public void setDealerCategory(DealerStoreCategory dealerCategory) {
		this.dealerCategory = dealerCategory;
	}

	public Set<Device> getDevices() {
		return devices;
	}

	public void setDevices(Set<Device> devices) {
		this.devices = devices;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public Boolean getIsClose() {
		return isClose;
	}

	public void setIsClose(Boolean isClose) {
		this.isClose = isClose;
	}

	public String getCloseReason() {
		return closeReason;
	}

	public void setCloseReason(String closeReason) {
		this.closeReason = closeReason;
	}

	public Date getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Set<VisitRouteNode> getNodes() {
		return nodes;
	}

	public void setNodes(Set<VisitRouteNode> nodes) {
		this.nodes = nodes;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}
