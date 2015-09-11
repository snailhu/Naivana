package com.nirvana.domain.dealer;

import java.io.Serializable;
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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import com.nirvana.domain.backend.AgentDealerCategory;
import com.nirvana.domain.backend.VisitRouteNode;
import com.nirvana.domain.backend.area.AgentArea;
import com.nirvana.domain.backend.area.DirectorArea;
import com.nirvana.domain.common.Channel;
import com.nirvana.domain.common.Device;
import com.nirvana.domain.dealer.usersys.DealerUser;
import com.nirvana.domain.store.Store;

/**
 * ������ʵ�塣
 * 
 * */
@Entity
@Table(name = "dealer_dealer")
public class Dealer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4921375077721822639L;

	/** ������ID */
	@Id
	@GeneratedValue
	private Long id;

	/** �������û� */
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "dealer")
	private DealerUser user;

	/** ERP�еĿͻ���� */
	@Column(length = 20, unique = true, nullable = false)
	private String codeInERP;

	/** ������һ���ŵ� */
	@OneToMany(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "dealer_id")
	private Set<Store> stores;

	/** �����̵�����Ϣ */
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "storefrontinfo_id")
	private DealerStorefrontInfo storefrontInfo;

	/** �����̵Ŀ����Ϣ */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "dealer_id")
	private Set<Inventory> inventories;

	/** ���������� */
	@ManyToOne(optional = false)
	@JoinColumn(name = "directorarea_id")
	private DirectorArea directorArea;

	/** �Ƿ�ֱӪ */
	@Column(nullable = false)
	private Boolean isDirect;

	/** ֱӪ��������С�� */
	@ManyToOne
	@JoinTable(name = "backend_area_agentarea_dealer_directstore", joinColumns = @JoinColumn(name = "dealer_id"), inverseJoinColumns = @JoinColumn(name = "area_id"))
	private AgentArea agentArea;

	/** ������������С�� */
	@ManyToMany
	@JoinTable(name = "backend_area_agentarea_dealer_dealer", joinColumns = @JoinColumn(name = "dealer_id"), inverseJoinColumns = @JoinColumn(name = "area_id"))
	private Set<AgentArea> agentAreas;

	@OneToMany
	@JoinColumn(name = "dealer_id")
	private Set<VisitRouteNode> nodes;

	/** ���� */
	@ManyToOne
	@JoinColumn(name = "channel_id")
	private Channel channel;

	/** �豸 */
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "dealer_dealer_device", joinColumns = @JoinColumn(name = "dealer_id"), inverseJoinColumns = @JoinColumn(name = "device_id"))
	private Set<Device> devices;

	/** �Ƿ�ر� */
	@Column(nullable = false)
	private Boolean isClose = false;

	/** �ر�ԭ�� */
	@Column(length = 50)
	private String closeReason;

	/** �ر����� */
	@Temporal(TemporalType.TIMESTAMP)
	private Date closeDate;

	/** ֱӪ����ҵ��Ա�еķ��ࡣ */
	@ManyToOne
	@JoinColumn(name = "category_id")
	private AgentDealerCategory category;

	/** ���� */
	@Column(length = 50)
	private String notes;

	/** �汾��Ϣ */
	@Column(length = 30, nullable = false)
	private String version = "-1";

	/** ��ˮ�� */
	@OneToOne
	@PrimaryKeyJoinColumn
	private DealerSerialNumber serialNumber;

	/** �µ���ˮ�� */
	@OneToOne
	@PrimaryKeyJoinColumn
	private DealerStockSerialNumber stockSerialNumber;

	/** ��ǰ������Ϣ */
	@OneToOne
	@PrimaryKeyJoinColumn
	private DealerPoints points;

	/** ���͵�channelId */
	@Column(length = 30)
	private String channelId;

	/** ���͵��豸���� */
	private Integer deviceType;

	@Version
	private Integer versionForLock;

	public Dealer() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DealerUser getUser() {
		return user;
	}

	public void setUser(DealerUser user) {
		this.user = user;
	}

	public String getCodeInERP() {
		return codeInERP;
	}

	public void setCodeInERP(String codeInERP) {
		this.codeInERP = codeInERP;
	}

	public Set<Store> getStores() {
		return stores;
	}

	public void setStores(Set<Store> stores) {
		this.stores = stores;
	}

	public DealerStorefrontInfo getStorefrontInfo() {
		return storefrontInfo;
	}

	public void setStorefrontInfo(DealerStorefrontInfo storefrontInfo) {
		this.storefrontInfo = storefrontInfo;
	}

	public Set<Inventory> getInventories() {
		return inventories;
	}

	public void setInventories(Set<Inventory> inventories) {
		this.inventories = inventories;
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

	public DirectorArea getDirectorArea() {
		return directorArea;
	}

	public void setDirectorArea(DirectorArea directorArea) {
		this.directorArea = directorArea;
	}

	public AgentArea getAgentArea() {
		return agentArea;
	}

	public void setAgentArea(AgentArea agentArea) {
		this.agentArea = agentArea;
	}

	public Set<AgentArea> getAgentAreas() {
		return agentAreas;
	}

	public void setAgentAreas(Set<AgentArea> agentAreas) {
		this.agentAreas = agentAreas;
	}

	public Boolean getIsDirect() {
		return isDirect;
	}

	public void setIsDirect(Boolean isDirect) {
		this.isDirect = isDirect;
	}

	public Set<Device> getDevices() {
		return devices;
	}

	public void setDevices(Set<Device> devices) {
		this.devices = devices;
	}

	public AgentDealerCategory getCategory() {
		return category;
	}

	public void setCategory(AgentDealerCategory category) {
		this.category = category;
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

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public DealerSerialNumber getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(DealerSerialNumber serialNumber) {
		this.serialNumber = serialNumber;
	}

	public DealerStockSerialNumber getStockSerialNumber() {
		return stockSerialNumber;
	}

	public void setStockSerialNumber(DealerStockSerialNumber stockSerialNumber) {
		this.stockSerialNumber = stockSerialNumber;
	}

	public DealerPoints getPoints() {
		return points;
	}

	public void setPoints(DealerPoints points) {
		this.points = points;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public Integer getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(Integer deviceType) {
		this.deviceType = deviceType;
	}

	public Integer getVersionForLock() {
		return versionForLock;
	}

	public void setVersionForLock(Integer versionForLock) {
		this.versionForLock = versionForLock;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Dealer other = (Dealer) obj;

		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;

		return true;
	}

}
