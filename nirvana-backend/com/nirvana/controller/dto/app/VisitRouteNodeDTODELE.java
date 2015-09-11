//package com.nirvana.controller.dto.app;
//
//import com.nirvana.controller.dto.web.WebVisitRouteNodeDTO;
//import com.nirvana.domain.backend.VisitRouteNode;
//import com.nirvana.domain.dealer.Dealer;
//import com.nirvana.domain.store.Store;
//import com.nirvana.pojo4json.BaseDTO;
//import com.nirvana.pojo4json.Converter;
//import com.nirvana.pojo4json.DTOContext;
//
//public class VisitRouteNodeDTODELE extends BaseDTO<VisitRouteNode> {
//
//	private Integer serialNum;
//	private String type;
//	private BEAppStoreDTO store;
//	private BEAppDealerDTO dealer;
//
//	public Integer getSerialNum() {
//		return serialNum;
//	}
//
//	public void setSerialNum(Integer serialNum) {
//		this.serialNum = serialNum;
//	}
//
//	@Override
//	public BaseDTO<VisitRouteNode> convert(VisitRouteNode domain) {
//		WebVisitRouteNodeDTO dto = new WebVisitRouteNodeDTO();
//		if (domain == null) {
//			return dto;
//		}
//		dto.setSerialNum(domain.getPk().getSerialNum());
//		dto.setType(domain.getType().name());
//		switch (domain.getType()) {
//		case DIRECT_STORE:
//			Converter<Dealer, BEAppDealerDTO> dConverter = DTOContext
//					.getConverter(BEAppDealerDTO.class);
//			dto.setDealer(dConverter.convert(domain.getDealer()));
//			break;
//		case DISTRIBUTE_STORE:
//			Converter<Store, BEAppStoreDTO> sConverter = DTOContext
//					.getConverter(BEAppStoreDTO.class);
//			dto.setStore(sConverter.convert(domain.getStore()));
//			break;
//		default:
//			break;
//		}
//		return dto;
//	}
//
//	public String getType() {
//		return type;
//	}
//
//	public void setType(String type) {
//		this.type = type;
//	}
//
//	public BEAppStoreDTO getStore() {
//		return store;
//	}
//
//	public void setStore(BEAppStoreDTO store) {
//		this.store = store;
//	}
//
//	public BEAppDealerDTO getDealer() {
//		return dealer;
//	}
//
//	public void setDealer(BEAppDealerDTO dealer) {
//		this.dealer = dealer;
//	}
//
//
// }
