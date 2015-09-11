package com.nirvana.controller.web.api;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.reflect.TypeToken;
import com.nirvana.controller.dto.app.PepsiPromotionDTO;
import com.nirvana.controller.dto.web.WebPictureDTO;
import com.nirvana.domain.backend.PepsiPromotion;
import com.nirvana.domain.backend.PromotionState;
import com.nirvana.fdfs.FileOperator;
import com.nirvana.pojo4json.Converter;
import com.nirvana.pojo4json.DTOContext;
import com.nirvana.pojo4json.json.Parser;
import com.nirvana.pojo4json.json.SimpleParser;
import com.nirvana.pojo4json.message.JsonResponseBean;
import com.nirvana.service.PromotionManageService;

@Controller
@RequestMapping(value = "/backend/web/api", produces = "application/json;charset=utf-8")
public class PromotionManageController {

	@Resource
	private PromotionManageService promotionManageService;
	
	Parser parser = new SimpleParser();

	/**
	 * 创建新的促销方案。
	 * 
	 * @param title
	 *            促销标题
	 * @param channelId
	 *            渠道ID
	 * @param beginDate
	 *            开始时间
	 * @param endDate
	 *            结束时间
	 * @param content
	 *            内容（奖励措施）
	 * @param pictures
	 *            签核文件
	 * 
	 * */
	@RequestMapping(value = "/promotions", method = { RequestMethod.POST })
	@ResponseBody
	public String createNewPromotion(
			@RequestParam("title") String title,
			@RequestParam("channelId") String channelId,
			@RequestParam("beginDate") int beginDate,
			@RequestParam("endDate") int endDate,
			@RequestParam("content") String content,
			@RequestParam("pictures") String pictures) {
		List<String> data = parser.parseJSON(pictures, new TypeToken<List<String>>() {
		});
		promotionManageService.createNewPromotion(title, channelId, beginDate, endDate, content, data);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * 编辑促销方案。
	 * 
	 * */
	@RequestMapping(value = "/promotions/{id}", method = { RequestMethod.POST })
	@ResponseBody
	public String editPromotion(
			@PathVariable("id") int promotionId,
			@RequestParam("title") String title,
			@RequestParam("channelId") String channelId,
			@RequestParam("beginDate") int beginDate,
			@RequestParam("endDate") int endDate,
			@RequestParam("content") String content,
			@RequestParam("pictures") String pictures) {
		List<String> data = parser.parseJSON(pictures, new TypeToken<List<String>>() {
		});
		promotionManageService.editPromotion(promotionId, title, channelId, beginDate, endDate, content, data);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * 通过促销方案。
	 * 
	 * */
	@RequestMapping(value = "/promotions/{promotionId}/adopt", method = { RequestMethod.PUT })
	@ResponseBody
	public String adoptPromotion(@PathVariable("promotionId") int promotionId) {
		promotionManageService.adoptPromotion(promotionId);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * 拒绝促销方案。
	 * 
	 * */
	@RequestMapping(value = "/promotions/{promotionId}/reject", method = { RequestMethod.PUT })
	@ResponseBody
	public String rejectPromotion(@PathVariable("promotionId") int promotionId) {
		promotionManageService.rejectPromotion(promotionId);
		return JsonResponseBean.getSuccessResponse().toJson();
	}

	/**
	 * 获取历史促销信息。
	 * 
	 * */
	@RequestMapping(value = "/promotions", method = { RequestMethod.GET })
	@ResponseBody
	public String getHistoryPromotions(
			@RequestParam(value = "state") String state,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "size", required = false, defaultValue = "20") int size) {
		PromotionState promotionState = PromotionState.parsePromotionState(state);
		Page<PepsiPromotion> pepsiPromotions = promotionManageService.getHistoryPromotions(promotionState, page, size);
		Converter<PepsiPromotion, PepsiPromotionDTO> converter = DTOContext.getConverter(PepsiPromotionDTO.class);
		return new JsonResponseBean(converter.convert(pepsiPromotions)).toJson();

	}
	
	/**
	 *上传促销图片。
	 * 
	 * */
	@RequestMapping(value = "/promotions/upload", method = { RequestMethod.POST },produces="text/json")
	@ResponseBody
	public String uploadPromotionspic(
			@RequestParam("pictures") MultipartFile pictures) {
		String fileId = FileOperator.savePicture(pictures);
		Converter<String, WebPictureDTO> converter = DTOContext.getConverter(WebPictureDTO.class);
		return new JsonResponseBean(converter.convert(fileId)).toJson();
	}
}
