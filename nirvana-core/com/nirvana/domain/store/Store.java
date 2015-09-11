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
 * 一阶门店实体。
 * 
 * */
@Entity
@Table(name = "store_store")
public class Store {
	/** 门店ID */
	@Id
	@GeneratedValue
	private Long id;

	/** 关联的门店用户 */
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "store")
	private StoreUser user;

	/** ERP中的客户编号 */
	@Column(length = 10, unique = true)
	private String codeInERP;

	/** 所属CR区（小区） */
	@ManyToOne
	@JoinColumn(name = "area_id")
	private AgentArea agentArea;

	/** 二阶经销商 */
	@ManyToOne
	@JoinColumn(name = "dealer_id")
	private Dealer dealer;

	/** 门店店面信息 */
	@OneToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "storefrontinfo_id")
	private StoreStorefrontInfo storefrontInfo;

	/** 门店在业务员下的分类 */
	@ManyToOne
	@JoinColumn(name = "agentcategory_id")
	private AgentStoreCategory agentCategory;

	/** 门店在经销商下的分类 */
	@ManyToOne
	@JoinColumn(name = "dealercategory_id")
	private DealerStoreCategory dealerCategory;

	/** 渠道 */
	@ManyToOne
	@JoinColumn(name = "channel_id")
	private Channel channel;

	/** 设备 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "store_store_device", joinColumns = @JoinColumn(name = "store_id"), inverseJoinColumns = @JoinColumn(name = "device_id"))
	private Set<Device> devices;

	/** 关联此门店的路线。 */
	@OneToMany
	@JoinColumn(name = "store_id")
	private Set<VisitRouteNode> nodes;

	/** 备忘 */
	private String notes;

	/** 是否关闭 */
	@Column(nullable = false)
	private Boolean isClose = false;

	/** 关闭原因 */
	private String closeReason;

	/** 关闭日期 */
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
