package com.nirvana.test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nirvana.domain.backend.PepsiPromotion;
import com.nirvana.domain.backend.PepsiPromotionPic;
import com.nirvana.domain.backend.PromotionState;
import com.nirvana.domain.common.Channel;
import com.nirvana.repository.backend.PepsiPromotionRepository;
import com.nirvana.repository.common.ChannelRepository;

/**
 * @Title:AddInventory.java
 * @Description:
 * @Version:v1.0
 */
@ContextConfiguration({ "classpath:applicationContext.xml", "classpath:applicationContext-jpa.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class AddPepsiPromotion {
	@Resource
	PepsiPromotionRepository pepsiPromotionRepository;
	@Resource
	ChannelRepository channelRepository;

	@Test
	public void addInventory() {
		List<PepsiPromotion> list = new ArrayList<PepsiPromotion>();
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, -20);
		List<Channel> l = channelRepository.findAll();
		final PepsiPromotionPic pic1 = new PepsiPromotionPic();
		final PepsiPromotionPic pic2 = new PepsiPromotionPic();
		Set<PepsiPromotionPic> pics = new HashSet<PepsiPromotionPic>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				add(pic1);
				add(pic2);
			}
		};
		for (int i = 0; i < 10; i++) {
			PepsiPromotion pp = new PepsiPromotion();
			c.add(Calendar.DATE, i);
			pp.setCreateDate(c.getTime());
			pp.setChannel(l.get(l.size() % 10));
			pp.setEndDate(20150610 + i);
			pp.setPics(pics);
			pp.setReward("reward" + i);
			pp.setStartDate(20150510 + i);
			pp.setState(randomState());
			pp.setTitle("title" + i);
			list.add(pp);
		}
		pepsiPromotionRepository.save(list);

	}

	private PromotionState randomState() {
		Random random = new Random();
		int a = random.nextInt(3);
		if (a == 0) {
			return PromotionState.ADOPTED;
		} else if (a == 1) {
			return PromotionState.REJECTED;
		} else {
			return PromotionState.WAITVERIFY;
		}
	}
}
