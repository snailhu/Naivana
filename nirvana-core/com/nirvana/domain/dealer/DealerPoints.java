package com.nirvana.domain.dealer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "dealer_dealerpoints")
public class DealerPoints {

	/** ID */
	@Id
	@GeneratedValue(generator = "pkGenerator")
	@GenericGenerator(name = "pkGenerator", strategy = "foreign", parameters = @Parameter(name = "property", value = "dealer"))
	private Long id;

	/** 可用积分 */
	private Integer availablePoints;

	/** 已使用积分 */
	private Integer consumedPoints;

	@OneToOne(optional = false)
	@PrimaryKeyJoinColumn
	private Dealer dealer;

	public DealerPoints() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getAvailablePoints() {
		return availablePoints;
	}

	public void setAvailablePoints(Integer availablePoints) {
		this.availablePoints = availablePoints;
	}

	public Integer getConsumedPoints() {
		return consumedPoints;
	}

	public void setConsumedPoints(Integer consumedPoints) {
		this.consumedPoints = consumedPoints;
	}

	public Dealer getDealer() {
		return dealer;
	}

	public void setDealer(Dealer dealer) {
		this.dealer = dealer;
	}

}
