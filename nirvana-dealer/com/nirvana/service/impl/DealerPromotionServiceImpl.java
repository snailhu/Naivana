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
import org.springframework.util.Assert;

import com.nirvana.domain.backend.PepsiPromotion;
import com.nirvana.domain.backend.PromotionState;
import com.nirvana.domain.common.Channel;
import com.nirvana.domain.common.Product;
import com.nirvana.domain.dealer.Dealer;
import com.nirvana.domain.dealer.DealerPromotion;
import com.nirvana.domain.dealer.DealerStoreCategory;
import com.nirvana.domain.dealer.PromCondition;
import com.nirvana.domain.dealer.PromWay;
import com.nirvana.domain.dealer.PromotionGoods;
import com.nirvana.exception.RecordAcessDeniedException;
import com.nirvana.exception.RecordExistedException;
import com.nirvana.exception.RecordNotFoundException;
import com.nirvana.repository.backend.PepsiPromotionRepository;
import com.nirvana.repository.common.BrandRepository;
import com.nirvana.repository.common.ProductRepository;
import com.nirvana.repository.dealer.DealerPromotionRepository;
import com.nirvana.repository.dealer.DealerRepository;
import com.nirvana.repository.dealer.DealerStoreCategoryRepository;
import com.nirvana.service.DealerCurrentLoginService;
import com.nirvana.service.DealerPromotionService;

@Transactional
@Service
public class DealerPromotionServiceImpl implements DealerPromotionService {

	@Resource
	private DealerPromotionRepository dealerPromotionRepository;
	@Resource
	private DealerCurrentLoginService dealerCurrentAccountService;
	@Resource
	private BrandRepository brandRepository;
	@Resource
	private DealerRepository dealerRepository;
	@Resource
	private PepsiPromotionRepository pepsiPromotionRepository;
	@Resource
	private DealerStoreCategoryRepository dealerStoreCategoryRepository;
	@Resource
	private ProductRepository productRepository;

	private Pageable getPageable(int page, int size) {
		if (page <= 0) {
			page = 1;
		}
		if (size <= 0) {
			size = 20;
		}
		return new PageRequest(page - 1, size);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Page<DealerPromotion> getMyPromotions(int page, int size) {
		if (page < 1 || size > 100) {
			throw new IllegalArgumentException("page��������С��1��size�������ܴ���100��");
		}
		long dealerId = dealerCurrentAccountService.getCurrentLoginDealerId();
		Page<DealerPromotion> dealerPromotionPage = dealerPromotionRepository
				.findByDealerIdOrderByEditDateDesc(dealerId,
						getPageable(page, size));
		return dealerPromotionPage;
	}

	/**
	 * �϶��˴�����Ϣ���ڴ˾����̡�
	 * 
	 * */
	private void judgeDealerPromotion(DealerPromotion promotion, Dealer dealer) {
		Assert.notNull(dealer, "��������Ϊ�ա�");
		Assert.notNull(promotion, "��������Ϊ�ա�");
		if (promotion.getDealer() == null) {
			throw new IllegalArgumentException("�˴�����Ϣ�����ڴ˾����̡�");
		} else {
			if (!promotion.getDealer().equals(dealer)) {
				throw new IllegalArgumentException("�˴�����Ϣ�����ڴ˾����̡�");
			}
		}
	};

	@Override
	public void editDealerPromotion(String title, long promotionId,
			int startDate, int endDate, List<String> catList,
			Set<String> products, PromCondition pCondition, int cdtParam,
			PromWay way, float wayParam, Set<String> goods) {

		Dealer dealer = dealerCurrentAccountService.getCurrentLoginDealer();
		DealerPromotion promotion = dealerPromotionRepository
				.findOne(promotionId);
		if (promotion == null || promotion.getIsEffect()) {
			throw new RecordAcessDeniedException("û�пɱ༭�Ĵ������ߴ����Ѿ���Ч���ɱ༭");
		}
		judgeDealerPromotion(promotion, dealer);

		Set<Product> productsRepeat = new HashSet<Product>();
		Set<Product> productz = new HashSet<Product>();
		Set<Product> productsave = new HashSet<Product>();
		for (String productCode : products) {
			Product product = productRepository.findOne(productCode);
			if (product == null) {
				throw new IllegalArgumentException("������ĳ����ƷCODE�����ڡ�");
			}
			productz.add(product);
			productsave.add(product);
			for (Product p : promotion.getProducts()) {
				if (p.getCode().equals(product.getCode())) {
					productsRepeat.add(product);
				}
			}

		}
		productz.removeAll(productsRepeat);

		Set<DealerStoreCategory> dealerStoreCategoriesRepeat = new HashSet<DealerStoreCategory>();
		Set<DealerStoreCategory> dealerStoreCategories = new HashSet<DealerStoreCategory>();
		Set<DealerStoreCategory> dealerStoreCategoriesSave = new HashSet<DealerStoreCategory>();
		for (String category : catList) {
			DealerStoreCategory storeCategory = dealerStoreCategoryRepository
					.findByDealerIdAndCategoryName(dealer.getId(), category);
			if (storeCategory == null) {
				throw new RecordNotFoundException("δ�ҵ��˷��ࡣ");
			}
			dealerStoreCategories.add(storeCategory);
			dealerStoreCategoriesSave.add(storeCategory);
			for (DealerStoreCategory c : promotion.getCategories()) {
				if (c.getId() == storeCategory.getId()) {
					dealerStoreCategoriesRepeat.add(storeCategory);
				}
			}
		}
		dealerStoreCategories.removeAll(dealerStoreCategoriesRepeat);

		for (DealerStoreCategory storeCategory : dealerStoreCategories) {
			for (Product p : productz) {
				if (dealerPromotionRepository
						.countByDealerAndProductsAndCategoriesAndEndDateGreaterThanAndBeginDateLessThanC(
								dealer.getId(), p.getCode(),
								storeCategory.getId(), startDate, endDate) > 0) {
					throw new RecordExistedException(p.getDescription() + "��"
							+ storeCategory.getCategoryName()
							+ "�����ϴ������ʱ����غϵĴ���");
				}
			}
		}
		for (DealerStoreCategory storeCategory : dealerStoreCategories) {
			for (Product p : productsRepeat) {
				if (dealerPromotionRepository
						.countByDealerAndProductsAndCategoriesAndEndDateGreaterThanAndBeginDateLessThanC(
								dealer.getId(), p.getCode(),
								storeCategory.getId(), startDate, endDate) > 0) {
					throw new RecordExistedException(p.getDescription() + "��"
							+ storeCategory.getCategoryName()
							+ "�����ϴ������ʱ����غϵĴ���");
				}
			}
		}
		for (DealerStoreCategory storeCategory : dealerStoreCategoriesRepeat) {
			for (Product p : productz) {
				if (dealerPromotionRepository
						.countByDealerAndProductsAndCategoriesAndEndDateGreaterThanAndBeginDateLessThanC(
								dealer.getId(), p.getCode(),
								storeCategory.getId(), startDate, endDate) > 0) {
					throw new RecordExistedException(p.getDescription() + "��"
							+ storeCategory.getCategoryName()
							+ "�����ϴ������ʱ����غϵĴ���");
				}
			}
		}

		int unrepStart = startDate;
		int unrepEnd = endDate;
		if (promotion.getBeginDate() > startDate
				&& promotion.getBeginDate() <= endDate
				&& endDate <= promotion.getEndDate()) {
			unrepEnd = promotion.getBeginDate();
			for (DealerStoreCategory reC : dealerStoreCategoriesRepeat) {
				for (Product reP : productsRepeat) {
					if (dealerPromotionRepository
							.countByDealerAndProductsAndCategoriesAndEndDateGreaterThanAndBeginDateLessThan(
									dealer.getId(), reP.getCode(), reC.getId(),
									unrepStart, unrepEnd) > 0) {
						throw new RecordExistedException(reP.getDescription()
								+ "��" + reC.getCategoryName()
								+ "�����ϴ������ʱ����غϵĴ���");
					}
				}
			}
		} else if (endDate > promotion.getEndDate()
				&& startDate <= promotion.getEndDate()
				&& startDate >= promotion.getBeginDate()) {
			unrepStart = promotion.getEndDate();
			for (DealerStoreCategory reC : dealerStoreCategoriesRepeat) {
				for (Product reP : productsRepeat) {
					if (dealerPromotionRepository
							.countByDealerAndProductsAndCategoriesAndEndDateGreaterThanAndBeginDateLessThanB(
									dealer.getId(), reP.getCode(), reC.getId(),
									unrepStart, unrepEnd) > 0) {
						throw new RecordExistedException(reP.getDescription()
								+ "��" + reC.getCategoryName()
								+ "�����ϴ������ʱ����غϵĴ���");
					}
				}
			}
		} else if (endDate > promotion.getEndDate()
				&& startDate < promotion.getBeginDate()) {
			for (DealerStoreCategory reC : dealerStoreCategoriesRepeat) {
				for (Product reP : productsRepeat) {
					if (dealerPromotionRepository
							.countByDealerAndProductsAndCategoriesAndEndDateGreaterThanAndBeginDateLessThan(
									dealer.getId(), reP.getCode(), reC.getId(),
									unrepStart, promotion.getBeginDate()) > 0) {
						throw new RecordExistedException(reP.getDescription()
								+ "��" + reC.getCategoryName()
								+ "�����ϴ������ʱ����غϵĴ���");
					}
					if (dealerPromotionRepository
							.countByDealerAndProductsAndCategoriesAndEndDateGreaterThanAndBeginDateLessThanB(
									dealer.getId(), reP.getCode(), reC.getId(),
									promotion.getEndDate(), unrepEnd) > 0) {
						throw new RecordExistedException(reP.getDescription()
								+ "��" + reC.getCategoryName()
								+ "�����ϴ������ʱ����غϵĴ���");
					}
				}
			}
		} else if (promotion.getEndDate() < startDate) {
			for (DealerStoreCategory reC : dealerStoreCategoriesRepeat) {
				for (Product reP : productsRepeat) {
					if (dealerPromotionRepository
							.countByDealerAndProductsAndCategoriesAndEndDateGreaterThanAndBeginDateLessThanC(
									dealer.getId(), reP.getCode(), reC.getId(),
									startDate, endDate) > 0) {
						throw new RecordExistedException(reP.getDescription()
								+ "��" + reC.getCategoryName()
								+ "�����ϴ������ʱ����غϵĴ���");
					}
				}
			}
		} else if (promotion.getBeginDate() > endDate) {
			for (DealerStoreCategory reC : dealerStoreCategoriesRepeat) {
				for (Product reP : productsRepeat) {
					if (dealerPromotionRepository
							.countByDealerAndProductsAndCategoriesAndEndDateGreaterThanAndBeginDateLessThanC(
									dealer.getId(), reP.getCode(), reC.getId(),
									startDate, endDate) > 0) {
						throw new RecordExistedException(reP.getDescription()
								+ "��" + reC.getCategoryName()
								+ "�����ϴ������ʱ����غϵĴ���");
					}
				}
			}
		} else if (startDate >= promotion.getBeginDate()
				&& endDate <= promotion.getEndDate()) {
			// donothing
		}

		else {
			throw new RecordExistedException("δ֧�ֵ����������");
		}

		Set<PromotionGoods> goodz = new HashSet<PromotionGoods>();
		if (goods != null) {
			for (String goood : goods) {
				PromotionGoods promotionGoods = new PromotionGoods();
				promotionGoods.setGoods(goood);
				goodz.add(promotionGoods);
			}
		}
		promotion.setTitle(title);
		promotion.setIsEffect(false);
		promotion.setBeginDate(startDate);
		promotion.setEndDate(endDate);
		promotion.setCategories(dealerStoreCategoriesSave);
		promotion.setProducts(productsave);
		promotion.setpCondition(pCondition);
		promotion.setCdtParam(cdtParam);
		promotion.setWay(way);
		promotion.setWayParam(wayParam);
		promotion.setGoods(goodz);
		promotion.setEditDate(new Date());
		dealerPromotionRepository.save(promotion);

	}

	@Override
	public void withdrawDealerPromotion(long promotionId) {
		Dealer dealer = dealerCurrentAccountService.getCurrentLoginDealer();
		DealerPromotion promotion = dealerPromotionRepository
				.findOne(promotionId);
		if (promotion == null) {
			throw new RecordNotFoundException("�˴�����Ϣδ�ҵ���");
		}
		judgeDealerPromotion(promotion, dealer);
		dealerPromotionRepository.delete(promotionId);
	}

	@Override
	public void createDealerPromotion(String title, int startDate, int endDate,
			List<String> catList, Set<String> products,
			PromCondition pCondition, int cdtParam, PromWay way,
			float wayParam, Set<String> goods) {

		Dealer dealer = dealerCurrentAccountService.getCurrentLoginDealer();

		Set<DealerStoreCategory> dealerStoreCategories = new HashSet<DealerStoreCategory>();
		for (String category : catList) {
			DealerStoreCategory storeCategory = dealerStoreCategoryRepository
					.findByDealerIdAndCategoryName(dealer.getId(), category);
			if (storeCategory == null) {
				throw new IllegalArgumentException("����������󣬷���δ�ҵ���");
			}
			dealerStoreCategories.add(storeCategory);
		}
		Set<Product> productz = new HashSet<Product>();
		for (String productCode : products) {
			Product product = productRepository.findOne(productCode);
			if (product == null) {
				throw new IllegalArgumentException("������ĳ����ƷCODE�����ڡ�");
			}
			productz.add(product);
		}
		for (DealerStoreCategory storeCategory : dealerStoreCategories) {
			for (Product p : productz) {
				if (dealerPromotionRepository
						.countByDealerAndProductsAndCategoriesAndEndDateGreaterThanAndBeginDateLessThanC(
								dealer.getId(), p.getCode(),
								storeCategory.getId(), startDate, endDate) > 0) {
					throw new RecordExistedException(p.getDescription() + "��"
							+ storeCategory.getCategoryName()
							+ "�����ϴ������ʱ����غϵĴ���");
				}
			}
		}
		DealerPromotion promotion = new DealerPromotion();
		Set<PromotionGoods> goodz = new HashSet<PromotionGoods>();
		if (goods != null) {
			for (String goood : goods) {
				PromotionGoods promotionGoods = new PromotionGoods();
				promotionGoods.setGoods(goood);
				goodz.add(promotionGoods);
			}
		}
		promotion.setIsEffect(false);
		promotion.setDealer(dealer);
		promotion.setTitle(title);
		promotion.setBeginDate(startDate);
		promotion.setEndDate(endDate);
		promotion.setCategories(dealerStoreCategories);
		promotion.setProducts(productz);
		promotion.setpCondition(pCondition);
		promotion.setCdtParam(cdtParam);
		promotion.setWay(way);
		promotion.setWayParam(wayParam);
		promotion.setGoods(goodz);
		promotion.setEditDate(new Date());
		dealerPromotionRepository.save(promotion);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Page<PepsiPromotion> getPepsiPromotions(int page, int size) {
		Dealer dealer = dealerCurrentAccountService.getCurrentLoginDealer();
		Channel channel = dealer.getChannel();
		if (channel == null) {
			return null;
		}
		Page<PepsiPromotion> promotions = pepsiPromotionRepository
				.findByStateAndChannelId(PromotionState.ADOPTED,
						channel.getCode(), getPageable(page, size));
		return promotions;
	}
}
