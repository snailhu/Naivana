package com.nirvana.service;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;

import com.nirvana.domain.backend.PepsiPromotion;
import com.nirvana.domain.dealer.DealerPromotion;
import com.nirvana.domain.dealer.PromCondition;
import com.nirvana.domain.dealer.PromWay;

/**
 * �����̵Ĵ�����Ϣ����
 * 
 * �����Ӻ�˻�õĻ�ʹ������Լ���������һ���ŵ귢���Ļ�ʹ�����
 * 
 * */
public interface DealerPromotionService {

	/**
	 * ����ʱ�䵹���ȡ�ҵĴ�����Ϣ�ķ�ҳ���ݡ�
	 * 
	 * @param page
	 *            ҳ��
	 * @param size
	 *            ��ҳ��С
	 * */
	public Page<DealerPromotion> getMyPromotions(int page, int size);

	/**
	 * �༭������Ϣ��
	 * 
	 * */
	public void editDealerPromotion(
			String title,
			long promotionId,
			int startDate,
			int endDate,
			List<String> catList,
			Set<String> products,
			PromCondition pCondition,
			int cdtParam,
			PromWay way,
			float wayParam,
			Set<String> goods);

	/**
	 * ���ش�����Ϣ��
	 * 
	 * 
	 * */
	public void withdrawDealerPromotion(long promotionId);

	/**
	 * �½�������Ϣ��
	 * 
	 * */
	public void createDealerPromotion(
			String title,
			int startDate,
			int endDate,
			List<String> catList,
			Set<String> products,
			PromCondition pCondition,
			int cdtParam,
			PromWay way,
			float wayParam,
			Set<String> goods);

	/**
	 * ��ȡ���԰��µĴ�����Ϣ��
	 * 
	 * */
	public Page<PepsiPromotion> getPepsiPromotions(int page, int size);

}
