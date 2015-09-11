package com.nirvana.domain.dealer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * 流水号实体。 此表用来维护每个经销商当天的流水号。
 * 
 * 每个经销商唯一对应一个流水号。
 * 
 * 
 * */
@Entity
@Table(name = "dealer_serialnumber")
public class DealerSerialNumber {
	/** ID */
	@Id
	@GeneratedValue(generator = "pkGenerator")
	@GenericGenerator(name = "pkGenerator", strategy = "foreign", parameters = @Parameter(name = "property", value = "dealer"))
	private Long id;

	/** 此经销商的本日流水号 */
	private Integer serialNum;

	/** 本日的时间，共8位，例如本日日期为：2015-10-31,那么此Date为：20151031 */
	private Integer date;

	@OneToOne(optional = false)
	@PrimaryKeyJoinColumn
	private Dealer dealer;

	@Version
	private Integer version;

	public DealerSerialNumber() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getDate() {
		return date;
	}

	public void setDate(Integer date) {
		this.date = date;
	}

	public Integer getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(Integer serialNum) {
		this.serialNum = serialNum;
	}

	public Dealer getDealer() {
		return dealer;
	}

	public void setDealer(Dealer dealer) {
		this.dealer = dealer;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}
