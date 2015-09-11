package com.nirvana.domain.dealer;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "dealer_inventoriescheckhistory")
public class InventoriesCheckHistory {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "dealer_id")
	private Dealer dealer;

	/** 盘点日期 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date date = new Date();

	/** 图片URL */
	@Column(nullable = false, length = 200)
	private String url;

	/** 盘点历史项 */
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "history_id")
	private Set<CheckHistoryItem> items;

	public InventoriesCheckHistory() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Dealer getDealer() {
		return dealer;
	}

	public void setDealer(Dealer dealer) {
		this.dealer = dealer;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Set<CheckHistoryItem> getItems() {
		return items;
	}

	public void setItems(Set<CheckHistoryItem> items) {
		this.items = items;
	}

}
