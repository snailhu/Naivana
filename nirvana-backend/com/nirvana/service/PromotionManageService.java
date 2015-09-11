package com.nirvana.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.nirvana.domain.backend.PepsiPromotion;
import com.nirvana.domain.backend.PromotionState;

public interface PromotionManageService {

	/**
	 * �����µĴ���������
	 * 
	 * @param title
	 *            ��������
	 * @param channelId
	 *            ����ID
	 * @param beginDate
	 *            ��ʼʱ��
	 * @param endDate
	 *            ����ʱ��
	 * @param content
	 *            ���ݣ�������ʩ��
	 * @param pictures
	 *            ǩ���ļ�
	 * 
	 * */
	public void createNewPromotion(String title, String channelId, int beginDate, int endDate, String content, List<String> pictures);

	/**
	 * ɾ������������
	 * 
	 * */
	public void deletePromotion(int promotionId);

	/**
	 * �༭����������
	 * 
	 * */
	public void editPromotion(int promotionId, String title, String channelId, int beginDate, int endDate, String content, List<String> data);

	/**
	 * ͨ������������
	 * 
	 * */
	public void adoptPromotion(int promotionId);

	/**
	 * �ܾ�����������
	 * 
	 * */
	public void rejectPromotion(int promotionId);

	/**
	 * ��ȡ��ʷ������Ϣ��
	 * 
	 * */
	public Page<PepsiPromotion> getHistoryPromotions(PromotionState state, int page, int size);

	/**
	 * ��ȡ����������Ϣ��
	 * 
	 * */
	public PepsiPromotion getOnePromotion(int promotionId);


}
