package com.nirvana.domain.backend;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 活动实体。
 * 
 * */
@Entity
@Table(name = "backend_activity")
public class Activity {
	/** ID */
	@Id
	@GeneratedValue
	private Long id;

	/** 活动标题 */
	@Column(length = 20)
	private String title;

	/** 能否重新上传照片 */
	private Boolean retryable;

	/** 图片URL */
	@Column(length = 50)
	private String url;

	/** 活动规则 */
	@Column(length = 200)
	private String rule;

	/** 活动奖励 */
	@Column(length = 100)
	private String reward;

	/** 创建时间 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;

	/** 开始时间 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date beginDate;

	/** 结束时间 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;

	/** 活动状态 */
	@Enumerated(EnumType.STRING)
	private ActivityState state;

	/** 活动驳回原因1 */
	@Column(length = 100)
	private String rejectNote1;

	/** 活动驳回原因2 */
	@Column(length = 100)
	private String rejectNote2;

	public Activity() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public Boolean getRetryable() {
		return retryable;
	}

	public void setRetryable(Boolean retryable) {
		this.retryable = retryable;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	public String getReward() {
		return reward;
	}

	public void setReward(String reward) {
		this.reward = reward;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public ActivityState getState() {
		return state;
	}

	public void setState(ActivityState state) {
		this.state = state;
	}

	public String getRejectNote1() {
		return rejectNote1;
	}

	public void setRejectNote1(String rejectNote1) {
		this.rejectNote1 = rejectNote1;
	}

	public String getRejectNote2() {
		return rejectNote2;
	}

	public void setRejectNote2(String rejectNote2) {
		this.rejectNote2 = rejectNote2;
	}

}
