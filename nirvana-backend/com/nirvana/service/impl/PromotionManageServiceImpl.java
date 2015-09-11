package com.nirvana.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nirvana.domain.backend.PepsiPromotion;
import com.nirvana.domain.backend.PepsiPromotionPic;
import com.nirvana.domain.backend.PromotionState;
import com.nirvana.domain.common.Channel;
import com.nirvana.exception.RecordNotFoundException;
import com.nirvana.exception.UnexpectedStateException;
import com.nirvana.repository.backend.PepsiPromotionRepository;
import com.nirvana.repository.common.ChannelRepository;
import com.nirvana.service.CloudPushService;
import com.nirvana.service.PromotionManageService;
import com.nirvana.utils.Assert;

@Service
@Transactional
public class PromotionManageServiceImpl implements PromotionManageService {

	@Resource
	private PepsiPromotionRepository pepsiPromotionRepository;
	@Resource
	private ChannelRepository channelRepository;
	@Resource
	private CloudPushService cloudPushService;

	@Override
	public void createNewPromotion(String title, String channelId, int beginDate, int endDate, String content, List<String> pictures) {
		PepsiPromotion pepsiPromotion = new PepsiPromotion();
		pepsiPromotion.setTitle(title);
		Channel channel = channelRepository.findOne(channelId);
		if (channel == null) {
			throw new RecordNotFoundException("渠道不存在。");
		}
		pepsiPromotion.setChannel(channel);
		pepsiPromotion.setStartDate(beginDate);
		pepsiPromotion.setEndDate(endDate);
		pepsiPromotion.setReward(content);
		pepsiPromotion.setState(PromotionState.WAITVERIFY);
		Set<PepsiPromotionPic> pepsiPromotionPics = new HashSet<PepsiPromotionPic>();
		for (String picture : pictures) {
			PepsiPromotionPic pepsiPromotionPic = new PepsiPromotionPic();
			pepsiPromotionPic.setUrl(picture);
			pepsiPromotionPics.add(pepsiPromotionPic);

		}
		pepsiPromotion.setPics(pepsiPromotionPics);
		pepsiPromotionRepository.save(pepsiPromotion);

	}

	@Override
	public void adoptPromotion(int promotionId) {

		PepsiPromotion pepsiPromotion = pepsiPromotionRepository.findOne(promotionId);
		if (pepsiPromotion.getState() != PromotionState.WAITVERIFY) {
			throw new UnexpectedStateException("此促销方案未处于待审核状态。");
		}
		pepsiPromotion.setPassDate(new Date());
		pepsiPromotion.setState(PromotionState.ADOPTED);
		pepsiPromotionRepository.save(pepsiPromotion);
		String title = "您有一个新的促销信息";
		String description = pepsiPromotion.getTitle();
		String content = pepsiPromotion.getReward();
		new Thread(new SendMsgThread(pepsiPromotion.getChannel(), title, description, content)).start();

	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Page<PepsiPromotion> getHistoryPromotions(PromotionState state, int page, int size) {
		Assert.notNull(state);
		Pageable pageable = new PageRequest(page - 1, size);
		Page<PepsiPromotion> pepsiPromotions;
		if (state == PromotionState.ADOPTED) {
			pepsiPromotions = pepsiPromotionRepository.findByStateOrderByPassDate(state, pageable);
		} else {
			pepsiPromotions = pepsiPromotionRepository.findByStateOrderByCreateDate(state, pageable);
		}
		return pepsiPromotions;
	}

	@Override
	public void deletePromotion(int promotionId) {
		pepsiPromotionRepository.delete(promotionId);
	}

	@Override
	public void editPromotion(int promotionId, String title, String channelId, int beginDate, int endDate, String content, List<String> pictures) {
		PepsiPromotion promotion = pepsiPromotionRepository.findOne(promotionId);
		if (promotion == null) {
			throw new RecordNotFoundException("促销方案未找到。");
		}
		if (promotion.getState() == PromotionState.ADOPTED) {
			throw new UnexpectedStateException("此促销方案已通过。");
		}
		promotion.setTitle(title);
		Channel channel = channelRepository.findOne(channelId);
		if (channel == null) {
			throw new RecordNotFoundException("渠道不存在。");
		}
		promotion.setChannel(channel);
		promotion.setStartDate(beginDate);
		promotion.setEndDate(endDate);
		promotion.setReward(content);
		promotion.setState(PromotionState.WAITVERIFY);
		Set<PepsiPromotionPic> pepsiPromotionPics = promotion.getPics();
		pepsiPromotionPics.clear();
		for (String picture : pictures) {
			PepsiPromotionPic pepsiPromotionPic = new PepsiPromotionPic();
			pepsiPromotionPic.setUrl(picture);
			pepsiPromotionPics.add(pepsiPromotionPic);
		}
		pepsiPromotionRepository.save(promotion);
	}

	@Override
	public void rejectPromotion(int promotionId) {
		PepsiPromotion promotion = pepsiPromotionRepository.findOne(promotionId);
		if (promotion == null) {
			throw new RecordNotFoundException("促销方案未找到。");
		}
		if (promotion.getState() == PromotionState.ADOPTED) {
			throw new UnexpectedStateException("此促销方案已通过。");
		}
		promotion.setState(PromotionState.REJECTED);
		pepsiPromotionRepository.save(promotion);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public PepsiPromotion getOnePromotion(int promotionId) {
		PepsiPromotion promotion = pepsiPromotionRepository.findOne(promotionId);
		if (promotion == null) {
			throw new RecordNotFoundException();
		}
		return promotion;
	}

	class SendMsgThread implements Runnable {

		private Channel channel;
		private String title;
		private String description;
		private String content;

		public SendMsgThread(Channel channel, String title, String description, String content) {
			Assert.notNull(channel, title, description, content);
			this.channel = channel;
			this.title = title;
			this.description = description;
			this.content = content;
		}

		@Override
		public void run() {
			cloudPushService.pushToChannel(channel, title, description, content);

		}
	}

}
