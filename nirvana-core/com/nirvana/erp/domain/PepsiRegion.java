package com.nirvana.erp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "NJPEPSI_APP_REGION", schema = "IFSAPP")
public class PepsiRegion implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "UM_CODE", nullable = false, length = 4)
	private String umCode;

	@Column(name = "UM_DESC", nullable = false, length = 120)
	private String umDesc;

	@Column(name = "TDM_CODE", nullable = false, length = 12)
	private String tdmCode;

	@Column(name = "TDM_DESC", nullable = false, length = 120)
	private String tdmDesc;

	@Column(name = "REGION_CODE", nullable = false, length = 40)
	private String regionCode;

	@Column(name = "REGION_DESC", nullable = false, length = 140)
	private String regionDesc;

	@Id
	@Column(name = "DISTRICT_CODE", nullable = false, length = 40)
	private String districtCode;

	@Column(name = "DIST_DESC", nullable = false, length = 140)
	private String distDesc;

	public PepsiRegion() {
	}

	public String getUmCode() {
		return this.umCode;
	}

	public void setUmCode(String umCode) {
		this.umCode = umCode;
	}

	public String getUmDesc() {
		return this.umDesc;
	}

	public void setUmDesc(String umDesc) {
		this.umDesc = umDesc;
	}

	public String getTdmCode() {
		return this.tdmCode;
	}

	public void setTdmCode(String tdmCode) {
		this.tdmCode = tdmCode;
	}

	public String getTdmDesc() {
		return this.tdmDesc;
	}

	public void setTdmDesc(String tdmDesc) {
		this.tdmDesc = tdmDesc;
	}

	public String getRegionCode() {
		return this.regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	public String getRegionDesc() {
		return this.regionDesc;
	}

	public void setRegionDesc(String regionDesc) {
		this.regionDesc = regionDesc;
	}

	public String getDistrictCode() {
		return this.districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public String getDistDesc() {
		return this.distDesc;
	}

	public void setDistDesc(String distDesc) {
		this.distDesc = distDesc;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PepsiRegion))
			return false;
		PepsiRegion castOther = (PepsiRegion) other;

		return ((this.getDistrictCode() == castOther.getDistrictCode()) || (this.getDistrictCode() != null && castOther.getDistrictCode() != null && this.getDistrictCode().equals(
				castOther.getDistrictCode())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getDistrictCode() == null ? 0 : this.getDistrictCode().hashCode());

		return result;
	}

}