package com.nirvana.controller.dto.app;

import com.nirvana.domain.backend.Memorandum;
import com.nirvana.pojo4json.BaseDTO;
import com.nirvana.utils.DateUtil;

public class MemorandumDTO extends BaseDTO<Memorandum> {

	Long id;
	String time;
	String content;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public BaseDTO<Memorandum> convert(Memorandum domain) {
		MemorandumDTO dto = new MemorandumDTO();
		if (domain == null) {
			return dto;
		}
		dto.setContent(domain.getContent());
		dto.setId(domain.getId());
		if (domain.getEditDate() != null)
			dto.setTime(DateUtil.dateToString(domain.getEditDate(), "yy/MM/dd"));
		return dto;
	}
}
