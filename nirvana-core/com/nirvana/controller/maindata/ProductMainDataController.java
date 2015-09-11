package com.nirvana.controller.maindata;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nirvana.controller.dto.BrandDTO;
import com.nirvana.controller.dto.ProductDTO;
import com.nirvana.domain.common.Brand;
import com.nirvana.domain.common.Product;
import com.nirvana.pojo4json.Converter;
import com.nirvana.pojo4json.DTOContext;
import com.nirvana.pojo4json.message.JsonResponseBean;
import com.nirvana.service.ProductService;

/**
 * ��Ʒ�����ݻ�ȡ��
 * 
 * */
@Controller
@RequestMapping(value = "/maindata/api", produces = "application/json;charset=utf-8")
public class ProductMainDataController {

	@Resource
	private ProductService mainDataCommonService;

	/**
	 * ��ȡ����Ʒ�ơ�
	 * 
	 * @param page
	 * @param size
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/brands", method = { RequestMethod.GET })
	public String getBrands(
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "size", required = false, defaultValue = "20") int size) {
		Page<Brand> pBrand = mainDataCommonService.getBrands(page, size);
		Converter<Brand, BrandDTO> converter = DTOContext.getConverter(BrandDTO.class);
		return new JsonResponseBean(converter.convert(pBrand)).toJson();
	}

	/**
	 * ����Ʒ�ƻ�ȡ������Ʒ��
	 * 
	 * @param name
	 *            Ʒ����
	 * @param page
	 * @param size
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/brands/{name}/products", method = { RequestMethod.GET })
	public String getProductByBrand(
			@PathVariable("name") String name,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "size", required = false, defaultValue = "20") int size) {
		Page<Product> pProduct = mainDataCommonService.getProductByBrand(name, page, size);
		Converter<Product, ProductDTO> converter = DTOContext.getConverter(ProductDTO.class);
		return new JsonResponseBean(converter.convert(pProduct)).toJson();
	}

	/**
	 * ��ȡ������Ʒ��
	 * 
	 * @param page
	 * @param size
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/products", method = { RequestMethod.GET })
	public String getAllProducts(
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "size", required = false, defaultValue = "20") int size) {
		Page<Product> pProduct = mainDataCommonService.getAllProducts(page, size);
		Converter<Product, ProductDTO> converter = DTOContext.getConverter(ProductDTO.class);
		return new JsonResponseBean(converter.convert(pProduct)).toJson();
	}

	/**
	 * ������Ʒ�����ȡĳ����Ʒ��
	 * 
	 * @param code
	 *            ��Ʒ���롣
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/products/{code}", method = { RequestMethod.GET })
	public String getProductByCode(@PathVariable("code") String code) {
		Product product = mainDataCommonService.getProductByCode(code);
		JsonResponseBean bean = new JsonResponseBean();
		Converter<Product, ProductDTO> converter = DTOContext.getConverter(ProductDTO.class);
		bean.setData(converter.convert(product));
		return bean.toJson();
	}
}
