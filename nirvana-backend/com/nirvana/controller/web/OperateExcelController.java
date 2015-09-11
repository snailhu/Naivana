package com.nirvana.controller.web;

import java.io.IOException;
import java.util.Calendar;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.nirvana.domain.backend.usersys.ERole;
import com.nirvana.pojo4json.message.JsonResponseBean;
import com.nirvana.security.annotation.NeedERole;
import com.nirvana.security.annotation.SecurityUnit;
import com.nirvana.security.annotation.SecurityUnit.Warehouse;
import com.nirvana.service.OperateSdoExcelService;
import com.nirvana.utils.DateUtil;

@Controller
@RequestMapping(value = "/backend/web/", produces = "application/json;charset=utf-8")
@SecurityUnit(Warehouse.BACKEND)
public class OperateExcelController {

	@Resource
	private OperateSdoExcelService operateExcelService;

	@RequestMapping(value = "/sdoexcels", method = RequestMethod.POST)
	@NeedERole({ERole.SIS})
	public @ResponseBody
	String uploadExcel(@RequestParam(required = true, value = "uploadDate") String uploadDate, MultipartFile uploadExcel) {
		if (StringUtils.isAnyBlank(uploadDate)) {
			throw new IllegalArgumentException("传入参数不能为空");
		}
		Calendar start = Calendar.getInstance();
		try {
			start.setTime(DateUtil.parseDate(uploadDate, "yyyy-MM-dd"));
		} catch (Exception e) {
			throw new IllegalArgumentException("uploadDate格式yyyy-MM-dd");
		}
		int intDate = start.get(Calendar.YEAR) * 10000 + (start.get(Calendar.MONTH) + 1) * 100 + start.get(Calendar.DATE);
		try {
			operateExcelService.uploadExcelSdoFile(uploadExcel.getInputStream(), intDate);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return JsonResponseBean.getSuccessResponse().toJson();
	}
}
