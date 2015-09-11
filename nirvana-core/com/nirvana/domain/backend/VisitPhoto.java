package com.nirvana.domain.backend;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.nirvana.domain.store.Store;

/**
 * 拜访照片实体。
 * 
 * @deprecated 以后所有的拜访记录数据直接存在MongoDB中。
 * 
 * */
// @Entity
// @Table(name = "backend_secondshelvesinfo")
@Deprecated
public class VisitPhoto {

	/** ID */
	@Id
	@GeneratedValue
	private Long id;

	/** 图片URL */
	@Column(nullable = false, length = 200)
	private String url;

	/** 经度 */
	@Column(nullable = false)
	private Float longitude;

	/** 纬度 */
	@Column(nullable = false)
	private Float latitude;

	/** 拍摄日期 */
	@Column(nullable = false)
	private Date date;

	/** 照片类型 */
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private PhotoType type;

	/** 关联的门店 */
	@ManyToOne
	@JoinColumn(name = "store_id")
	private Store store;

	/** 所属的拜访记录 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "record_id")
	private VisitRecord visitRecord;

	public VisitPhoto() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Float getLongitude() {
		return longitude;
	}

	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}

	public Float getLatitude() {
		return latitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public PhotoType getType() {
		return type;
	}

	public void setType(PhotoType type) {
		this.type = type;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public VisitRecord getVisitRecord() {
		return visitRecord;
	}

	public void setVisitRecord(VisitRecord visitRecord) {
		this.visitRecord = visitRecord;
	}

}
