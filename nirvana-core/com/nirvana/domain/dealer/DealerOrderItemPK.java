package com.nirvana.domain.dealer;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * 经销商订单项联合主键。
 * 
 * */
@Embeddable
public class DealerOrderItemPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7846978383596805481L;

	/** 关联的订单 */
	@ManyToOne
	@JoinColumn(name = "order_id")
	private DealerOrder order;

	/** 订单项行号 */
	private Integer lineNo;

	public DealerOrderItemPK() {
	}

	public DealerOrder getOrder() {
		return order;
	}

	public void setOrder(DealerOrder order) {
		this.order = order;
	}

	public Integer getLineNo() {
		return lineNo;
	}

	public void setLineNo(Integer lineNo) {
		this.lineNo = lineNo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((order.getId() == null) ? 0 : order.getId().hashCode()) + 3 * ((lineNo == null) ? 0 : lineNo.hashCode());
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
		final DealerOrderItemPK other = (DealerOrderItemPK) obj;
		if (order == null) {
			if (other.order != null)
				return false;
		} else if (!order.equals(other.order)) {
			return false;
		}
		if (lineNo == null) {
			if (other.lineNo != null)
				return false;
		} else if (!lineNo.equals(other.lineNo)) {
			return false;
		}
		return true;
	}

}
