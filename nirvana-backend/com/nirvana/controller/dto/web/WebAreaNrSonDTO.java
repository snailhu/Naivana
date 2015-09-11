package com.nirvana.controller.dto.web;

import com.nirvana.pojo4json.BaseDTO;

public class WebAreaNrSonDTO<Domain> extends BaseDTO<Domain> {

	private Long id;

	private Integer date;

	private Float nr;

	/* (non-Javadoc)
	 * @see com.nirvana.pojo4json.BaseDTO#convert(java.lang.Object)
	 */
	@Override
	public BaseDTO<Domain> convert(Domain domain) {
		// TODO Auto-generated method stub
		return null;
	}

}
