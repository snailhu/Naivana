package com.nirvana.domain.backend;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.nirvana.domain.common.Channel;

/**
 * 百事的促销实体。
 * 
 * */
@Entity
@Table(name = "backend_pepsipromotion")
public class PepsiPromotion {
	/** ID */
	@Id
	@GeneratedValue
	private Integer id;

	/** 是否通过采用 */
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private PromotionState state;

	/** 活动标题 */
	@Column(length = 100, nullable = false)
	private String title;

	/** 创建日期 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date createDate = new Date();

	/** 通过日期 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date passDate;

	/** 开始日期,形式为20101125 */
	@Column(nullable = false)
	private Integer startDate;

	/** 结束日期 ,形式为20101125 */
	@Column(nullable = false)
	private Integer endDate;

	/** 活动奖励（文字说明） */
	@Column(length = 1000, nullable = false)
	private String reward;

	/** 活动针对的渠道 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "channel_id")
	private Channel channel;

	/** 签核文件图片 */
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "promotion_id")
	private Set<PepsiPromotionPic> pics;

	public PepsiPromotion() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getStartDate() {
		return startDate;
	}

	public void setStartDate(Integer startDate) {
		this.startDate = startDate;
	}

	public Integer getEndDate() {
		return endDate;
	}

	public void setEndDate(Integer endDate) {
		this.endDate = endDate;
	}

	public String getReward() {
		return reward;
	}

	public void setReward(String reward) {
		this.reward = reward;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public Set<PepsiPromotionPic> getPics() {
		return pics;
	}

	public void setPics(Set<PepsiPromotionPic> pics) {
		this.pics = pics;
	}

	public PromotionState getState() {
		return state;
	}

	public void setState(PromotionState state) {
		this.state = state;
	}

	public Date getPassDate() {
		return passDate;
	}

	public void setPassDate(Date passDate) {
		this.passDate = passDate;
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
		PepsiPromotion other = (PepsiPromotion) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
