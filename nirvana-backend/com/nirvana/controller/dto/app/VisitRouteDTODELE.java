//package com.nirvana.controller.dto.app;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import com.nirvana.domain.backend.VisitRoute;
//import com.nirvana.domain.backend.VisitRouteNode;
//import com.nirvana.pojo4json.BaseDTO;
//import com.nirvana.pojo4json.Converter;
//import com.nirvana.pojo4json.DTOContext;
//
//public class VisitRouteDTODELE extends BaseDTO<VisitRoute> {
//
//	private Integer code = 0;
//
//	private Integer agentAreaId = 1;
//
//	private List<VisitRouteNodeDTO> nodes = new ArrayList<VisitRouteNodeDTO>();
//
//	public Integer getCode() {
//		return code;
//	}
//
//	public void setCode(Integer code) {
//		this.code = code;
//	}
//
//	public List<VisitRouteNodeDTO> getNodes() {
//		return nodes;
//	}
//
//	public void setNodes(List<VisitRouteNodeDTO> nodes) {
//		this.nodes = nodes;
//	}
//
//	@Override
//	public BaseDTO<VisitRoute> convert(VisitRoute domain) {
//		VisitRouteDTO dto = new VisitRouteDTO();
//		if (domain == null) {
//			return dto;
//		}
//		
//		dto.setCode(domain.getCode());
//		dto.setAgentAreaId(domain.getArea().getId());
//		Converter<VisitRouteNode, VisitRouteNodeDTO> converter = DTOContext
//				.getConverter(VisitRouteNodeDTO.class);
//		dto.setNodes(converter.convert(domain.getNodes()));
//		return dto;
//	}
//
//	public Integer getAgentAreaId() {
//		return agentAreaId;
//	}
//
//	public void setAgentAreaId(Integer agentAreaId) {
//		this.agentAreaId = agentAreaId;
//	}
//
//}
