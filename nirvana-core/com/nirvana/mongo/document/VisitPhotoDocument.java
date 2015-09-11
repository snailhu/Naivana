package com.nirvana.mongo.document;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import com.nirvana.domain.backend.PhotoType;
import com.nirvana.domain.backend.VisitNodeType;
import com.nirvana.utils.Assert;

@Document(collection = "VISIT_PHOTO")
public class VisitPhotoDocument {

	/** 图片URL */
	private String url;

	/** 经度 */
	private Float longitude;

	/** 纬度 */
	private Float latitude;

	/** 拍摄日期 */
	private Date date;

	/** 照片类型 */
	private PhotoType type;

	/** 索引标示,4位业务员ID+1位标识+6位门店ID */
	private Long indexTag;

	/** 所属的拜访记录 */
	private Long recordId;

	public VisitPhotoDocument() {
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

	public Long getIndexTag() {
		return indexTag;
	}

	public void setIndexTag(Long indexTag) {
		this.indexTag = indexTag;
	}

	public Long getRecordId() {
		return recordId;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}

	public static long generateIndexTag(long areaId, VisitNodeType type, long storeId) {
		Assert.notNull(type);

		long tagLong = 1l;
		if (type == VisitNodeType.DIRECT_STORE) {
			tagLong = 0l;
		}

		long id = areaId * 10000 * 1000 + tagLong * 10000 * 100 + storeId;
		return id;
	}

}
