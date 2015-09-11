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
 * ���µĴ���ʵ�塣
 * 
 * */
@Entity
@Table(name = "backend_pepsipromotion")
public class PepsiPromotion {
	/** ID */
	@Id
	@GeneratedValue
	private Integer id;

	/** �Ƿ�ͨ������ */
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private PromotionState state;

	/** ����� */
	@Column(length = 100, nullable = false)
	private String title;

	/** �������� */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date createDate = new Date();

	/** ͨ������ */
	@Temporal(TemporalType.TIMESTAMP)
	private Date passDate;

	/** ��ʼ����,��ʽΪ20101125 */
	@Column(nullable = false)
	private Integer startDate;

	/** �������� ,��ʽΪ20101125 */
	@Column(nullable = false)
	private Integer endDate;

	/** �����������˵���� */
	@Column(length = 1000, nullable = false)
	private String reward;

	/** ���Ե����� */
	@ManyToOne(optional = false)
	@JoinColumn(name = "channel_id")
	private Channel channel;

	/** ǩ���ļ�ͼƬ */
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
