package com.nirvana.repository.dealer;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.nirvana.domain.dealer.DealerOrder;
import com.nirvana.domain.dealer.DealerOrderState;

public interface DealerOrderRepository extends JpaRepository<DealerOrder, Long> {

	public Page<DealerOrder> findByDealerIdOrderByIdDesc(long dealerId, Pageable page);

	public Page<DealerOrder> findByDealerIdAndStateOrderByIdDesc(long dealerId, DealerOrderState state, Pageable page);

	@Query("from DealerOrder do where do.dealer.agentArea.id=?1 and do.isAgentHelped=?2 order by id desc")
	public Page<DealerOrder> findByAgentAreaIdAndIsAgentHelped(int agentAreaId, boolean isAgentHelped, Pageable pageable);

	public Page<DealerOrder> findByDealerAgentAreaIdAndIsAgentHelpedAndEnterDateBetweenOrderByIdDesc(int agentAreaId, boolean isAgentHelped, Date start, Date end, Pageable pageable);

	@Modifying
	@Query("delete DealerOrder do where do.finishDate<?1 and do.state=?2")
	public Integer deleteByDateAndState(Date date, DealerOrderState state);

	@Query("from DealerOrder do where do.dealer.agentArea.id=?1 and do.isAgentHelped=?2 and do.createDate >=?3 and do.createDate <?4")
	public List<DealerOrder> findByAreaIdAndisAgentHelpedAndDate(Integer id, boolean b, Date startTime, Date endTime);

	@Query("from DealerOrder do where do.codeInERP=?1")
	public DealerOrder findByCodeInERP(String orderNum);

}
