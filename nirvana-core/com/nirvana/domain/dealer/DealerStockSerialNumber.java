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
 * �������µ���ˮ��ʵ�塣
 * 
 * 
 * */
@Entity
@Table(name = "dealer_stockserialnumber")
public class DealerStockSerialNumber {

	/** ID */
	@Id
	@GeneratedValue(generator = "pkGenerator")
	@GenericGenerator(name = "pkGenerator", strategy = "foreign", parameters = @Parameter(name = "property", value = "dealer"))
	private Long id;

	/** �˾����̵ı�����ˮ�� */
	private Integer serialNum;

	/** ���յ�ʱ�䣬��8λ�����籾������Ϊ��2015-10-31,��ô��DateΪ��20151031 */
	private Integer date;

	@OneToOne(optional = false)
	@PrimaryKeyJoinColumn
	private Dealer dealer;

	@Version
	private Integer version;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(Integer serialNum) {
		this.serialNum = serialNum;
	}

	public Integer getDate() {
		return date;
	}

	public void setDate(Integer date) {
		this.date = date;
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
