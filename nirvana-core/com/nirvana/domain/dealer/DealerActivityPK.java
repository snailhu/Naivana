package com.nirvana.domain.dealer;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.nirvana.domain.backend.Activity;

@Embeddable
public class DealerActivityPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8985772447363825078L;

	/** 经销商 */
	@ManyToOne
	@JoinColumn(name = "dealer_id")
	private Dealer dealer;
	/** 活动 */
	@ManyToOne
	@JoinColumn(name = "activity_id")
	private Activity activity;

	public DealerActivityPK() {
	}

	public Dealer getDealer() {
		return dealer;
	}

	public void setDealer(Dealer dealer) {
		this.dealer = dealer;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dealer.getId() == null) ? 0 : dealer.getId().hashCode()) + 3 * ((activity.getId() == null) ? 0 : activity.getId().hashCode());
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
		final DealerActivityPK other = (DealerActivityPK) obj;
		if (dealer == null) {
			if (other.dealer != null)
				return false;
		} else if (!dealer.equals(other.dealer)) {
			return false;
		}
		if (activity == null) {
			if (other.activity != null)
				return false;
		} else if (!activity.equals(other.activity)) {
			return false;
		}
		return true;
	}

}
