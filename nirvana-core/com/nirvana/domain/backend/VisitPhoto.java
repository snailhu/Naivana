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
 * �ݷ���Ƭʵ�塣
 * 
 * @deprecated �Ժ����еİݷü�¼����ֱ�Ӵ���MongoDB�С�
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

	/** ͼƬURL */
	@Column(nullable = false, length = 200)
	private String url;

	/** ���� */
	@Column(nullable = false)
	private Float longitude;

	/** γ�� */
	@Column(nullable = false)
	private Float latitude;

	/** �������� */
	@Column(nullable = false)
	private Date date;

	/** ��Ƭ���� */
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private PhotoType type;

	/** �������ŵ� */
	@ManyToOne
	@JoinColumn(name = "store_id")
	private Store store;

	/** �����İݷü�¼ */
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
