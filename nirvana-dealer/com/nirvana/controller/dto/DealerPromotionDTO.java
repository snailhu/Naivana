package com.nirvana.controller.dto;

import java.util.List;

import com.nirvana.domain.common.Product;
import com.nirvana.domain.dealer.DealerPromotion;
import com.nirvana.domain.dealer.DealerStoreCategory;
import com.nirvana.domain.dealer.PromotionGoods;
import com.nirvana.pojo4json.BaseDTO;
import com.nirvana.pojo4json.Converter;
import com.nirvana.pojo4json.DTOContext;
import com.nirvana.utils.DateUtil;

public class DealerPromotionDTO extends BaseDTO<DealerPromotion> {
	/** ID */
	private Long id;

	/** ���� */
	private String title;

	/** ����ʱ�� */
	private String createDate;

	/** �༭���� */
	private String editDate;

	/** ��ʼ���� */
	private Integer beginDate;

	/** �������� */
	private Integer endDate;

	/** �Ƿ��Ѿ���Ч */
	private Boolean isEffect;

	/** ����Ŀ���̵���� */
	// private String category;
	// private Long categoryId;
	private List<DealerStoreCategoryDTO> categories;

	/** ����Ŀ��Ʒ���б� */
	private List<ProductDTO> products;

	/** �Ż����� */
	private String condition;

	/** �������� */
	private Integer cdtParam;

	/** �Żݷ�ʽ */
	private String way;

	/** ��ʽ���� */
	private Float wayParam;

	/** �Ż���Ʒ */
	private List<PromotionGoodsDTO> goods;

	private String promotion;

	@Override
	public BaseDTO<DealerPromotion> convert(DealerPromotion domain) {
		DealerPromotionDTO dto = new DealerPromotionDTO();
		if (domain == null) {
			return dto;
		}

		dto.setBeginDate(domain.getBeginDate());
		Converter<Product, ProductDTO> converter = DTOContext
				.getConverter(ProductDTO.class);
		dto.setProducts(converter.convert(domain.getProducts()));
		Converter<DealerStoreCategory, DealerStoreCategoryDTO> catConverter = DTOContext
				.getConverter(DealerStoreCategoryDTO.class);
		dto.setCategories(catConverter.convert(domain.getCategories()));
		// if (domain.getCategory() != null) {
		// dto.setCategory(domain.getCategory().getCategoryName());
		// dto.setCategoryId(domain.getCategory().getId());
		// }
		dto.setCdtParam(domain.getCdtParam());
		dto.setCondition(domain.getpCondition().name());
		dto.setCreateDate(domain.getCreateDate() == null ? null : DateUtil
				.dateToString(domain.getCreateDate()));
		dto.setEditDate(domain.getEditDate() == null ? null : DateUtil
				.dateToString(domain.getEditDate()));
		dto.setEndDate(domain.getEndDate());
		Converter<PromotionGoods, PromotionGoodsDTO> prConverter = DTOContext
				.getConverter(PromotionGoodsDTO.class);
		dto.setGoods(prConverter.convert(domain.getGoods()));
		dto.setId(domain.getId());
		dto.setIsEffect(domain.getIsEffect());
		dto.setTitle(domain.getTitle());
		dto.setWay(domain.getWay().name());
		dto.setWayParam(domain.getWayParam());
		dto.setPromotion(getStrPromotion(domain));
		return dto;
	}

	private String getStrPromotion(DealerPromotion promotion) {
		String discountStr = "";
		if (promotion != null) {
			switch (promotion.getpCondition()) {
			case ACHIEVECASE:
				discountStr += "��" + promotion.getCdtParam() + "��";
				switch (promotion.getWay()) {
				case GIVEAWAY:
					discountStr += "����";
					for (PromotionGoods good : promotion.getGoods()) {
						discountStr += good.getGoods() + " | ";
					}
					break;
				case MONEYREDUCTION:
					discountStr += "�����ֽ�" + promotion.getWayParam() + "Ԫ";
					break;
				case PRICEDISCOUNT:
					discountStr += "���ܣ�" + promotion.getWayParam() / 10f
							+ "���Ż�";
					break;
				default:
					break;
				}
				break;
			case ACHIEVEMONEY:
				discountStr += "��" + promotion.getCdtParam() + "Ԫ���";
				switch (promotion.getWay()) {
				case GIVEAWAY:
					discountStr += "����";
					for (PromotionGoods good : promotion.getGoods()) {
						discountStr += good.getGoods() + " | ";
					}
					break;
				case MONEYREDUCTION:
					discountStr += "�����ֽ�" + promotion.getWayParam() + "Ԫ";
					break;
				case PRICEDISCOUNT:
					discountStr += "���ܣ�" + promotion.getWayParam() / 10f
							+ "���Ż�";
					break;
				default:
					break;
				}
				break;
			default:
				break;
			}
		}
		return discountStr;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getEditDate() {
		return editDate;
	}

	public void setEditDate(String editDate) {
		this.editDate = editDate;
	}

	public Integer getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Integer beginDate) {
		this.beginDate = beginDate;
	}

	public Integer getEndDate() {
		return endDate;
	}

	public void setEndDate(Integer endDate) {
		this.endDate = endDate;
	}

	public Boolean getIsEffect() {
		return isEffect;
	}

	public void setIsEffect(Boolean isEffect) {
		this.isEffect = isEffect;
	}

	// public String getCategory() {
	// return category;
	// }
	//
	// public void setCategory(String category) {
	// this.category = category;
	// }
	//
	// public Long getCategoryId() {
	// return categoryId;
	// }
	//
	// public void setCategoryId(Long categoryId) {
	// this.categoryId = categoryId;
	// }

	public List<ProductDTO> getProducts() {
		return products;
	}

	public void setProducts(List<ProductDTO> products) {
		this.products = products;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public Integer getCdtParam() {
		return cdtParam;
	}

	public void setCdtParam(Integer cdtParam) {
		this.cdtParam = cdtParam;
	}

	public String getWay() {
		return way;
	}

	public void setWay(String way) {
		this.way = way;
	}

	public Float getWayParam() {
		return wayParam;
	}

	public void setWayParam(Float wayParam) {
		this.wayParam = wayParam;
	}

	public List<PromotionGoodsDTO> getGoods() {
		return goods;
	}

	public void setGoods(List<PromotionGoodsDTO> goods) {
		this.goods = goods;
	}

	public String getPromotion() {
		return promotion;
	}

	public void setPromotion(String promotion) {
		this.promotion = promotion;
	}

	public List<DealerStoreCategoryDTO> getCategories() {
		return categories;
	}

	public void setCategories(List<DealerStoreCategoryDTO> categories) {
		this.categories = categories;
	}

}
