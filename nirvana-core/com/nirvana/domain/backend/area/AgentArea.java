package com.nirvana.domain.backend.area;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import com.nirvana.domain.backend.AgentDealerCategory;
import com.nirvana.domain.backend.AgentStoreCategory;
import com.nirvana.domain.backend.Memorandum;
import com.nirvana.domain.backend.Position;
import com.nirvana.domain.backend.VisitRoute;
import com.nirvana.domain.backend.goal.AgentAreaNrGoal;
import com.nirvana.domain.backend.goal.AgentWDNrGoal;
import com.nirvana.domain.dealer.Dealer;
import com.nirvana.domain.store.Store;

/**
 * CR区实体。
 * 
 **/
@Entity
@Table(name = "backend_area_agentarea")
public class AgentArea {
	/** ID */
	@Id
	@GeneratedValue
	private Integer id;

	/** 岗位类型 */
	@Enumerated(EnumType.STRING)
	@Column(length = 20, nullable = false)
	private WorkType type;

	/** 小区描述 */
	@Column(length = 30, name = "description", nullable = false)
	private String desc;

	/** CR区编码 */
	@Column(nullable = false, length = 20, unique = true)
	private String areaCode;

	/** 此CR区所属的主任区 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "area_id")
	private DirectorArea directorArea;

	/** 负责此CR区的业务代表 */
	@OneToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "position_id")
	private Position agent;

	/** 此CR区需要拜访的门店 */
	@OneToMany
	@JoinColumn(name = "area_id")
	private Set<Store> stores;

	/** 和此小区关联的经销商 */
	@ManyToMany
	@JoinTable(name = "backend_area_agentarea_dealer_dealer", joinColumns = @JoinColumn(name = "area_id"), inverseJoinColumns = @JoinColumn(name = "dealer_id"))
	private Set<Dealer> dealers;

	/** 和此小区关联的直营店 */
	@OneToMany
	@JoinTable(name = "backend_area_agentarea_dealer_directstore", joinColumns = @JoinColumn(name = "area_id"), inverseJoinColumns = @JoinColumn(name = "dealer_id"))
	private Set<Dealer> directStores;

	/** 此CR区的目标项 */
	@OneToMany(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "area_id")
	private Set<AgentAreaNrGoal> goals;

	/** CR区的门店分类 */
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "area_id")
	private Set<AgentStoreCategory> storeCategories;

	/** CR区的直营门店分类 */
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "area_id")
	private Set<AgentDealerCategory> dealerCategories;

	/** 是否关闭 */
	@Column(nullable = false)
	private Boolean isClosed = false;

	@OneToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "wdnrgoal_id")
	private AgentWDNrGoal wdNrGoal;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "area_id")
	private Set<Memorandum> memorandums;

	/** 拥有的拜访线路。 */
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "area_id")
	private Set<VisitRoute> routes;

	@Version
	private Integer version;

	public AgentArea() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public WorkType getType() {
		return type;
	}

	public void setType(WorkType type) {
		this.type = type;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public DirectorArea getDirectorArea() {
		return directorArea;
	}

	public void setDirectorArea(DirectorArea directorArea) {
		this.directorArea = directorArea;
	}

	public Position getAgent() {
		return agent;
	}

	public void setAgent(Position agent) {
		this.agent = agent;
	}

	public Set<Store> getStores() {
		return stores;
	}

	public void setStores(Set<Store> stores) {
		this.stores = stores;
	}

	public Set<AgentAreaNrGoal> getGoals() {
		return goals;
	}

	public void setGoals(Set<AgentAreaNrGoal> goals) {
		this.goals = goals;
	}

	public Set<AgentStoreCategory> getStoreCategories() {
		return storeCategories;
	}

	public void setStoreCategories(Set<AgentStoreCategory> storeCategories) {
		this.storeCategories = storeCategories;
	}

	public Set<AgentDealerCategory> getDealerCategories() {
		return dealerCategories;
	}

	public void setDealerCategories(Set<AgentDealerCategory> dealerCategories) {
		this.dealerCategories = dealerCategories;
	}

	public Boolean getIsClosed() {
		return isClosed;
	}

	public void setIsClosed(Boolean isClosed) {
		this.isClosed = isClosed;
	}

	public Set<Dealer> getDealers() {
		return dealers;
	}

	public void setDealers(Set<Dealer> dealers) {
		this.dealers = dealers;
	}

	public Set<Dealer> getDirectStores() {
		return directStores;
	}

	public void setDirectStores(Set<Dealer> directStores) {
		this.directStores = directStores;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public AgentWDNrGoal getWdNrGoal() {
		return wdNrGoal;
	}

	public void setWdNrGoal(AgentWDNrGoal wdNrGoal) {
		this.wdNrGoal = wdNrGoal;
	}

	public Set<Memorandum> getMemorandums() {
		return memorandums;
	}

	public void setMemorandums(Set<Memorandum> memorandums) {
		this.memorandums = memorandums;
	}

	public Set<VisitRoute> getRoutes() {
		return routes;
	}

	public void setRoutes(Set<VisitRoute> routes) {
		this.routes = routes;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
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
		AgentArea other = (AgentArea) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
