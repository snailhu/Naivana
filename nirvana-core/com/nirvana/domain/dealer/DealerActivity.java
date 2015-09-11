package com.nirvana.domain.dealer;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "dealer_dealersactivity")
public class DealerActivity {

	/** 主键类，由Dealer和Activity外键组成的联合主键 */
	@EmbeddedId
	private DealerActivityPK pk;

	/** 图片URL */
	@Column(length = 100)
	private String url;

	/** 经销商参加活动的状态 */
	@Enumerated(EnumType.STRING)
	private DealerActState state;

	/** 参加时间 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date joinDate;

	/** 审核通过时间 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date agreeDate;

	/** 审核拒绝时间 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date rejectDate;

	public DealerActivity() {
	}

	public DealerActivityPK getPk() {
		return pk;
	}

	public void setPk(DealerActivityPK pk) {
		this.pk = pk;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public DealerActState getState() {
		return state;
	}

	public void setState(DealerActState state) {
		this.state = state;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public Date getAgreeDate() {
		return agreeDate;
	}

	public void setAgreeDate(Date agreeDate) {
		this.agreeDate = agreeDate;
	}

	public Date getRejectDate() {
		return rejectDate;
	}

	public void setRejectDate(Date rejectDate) {
		this.rejectDate = rejectDate;
	}

}
