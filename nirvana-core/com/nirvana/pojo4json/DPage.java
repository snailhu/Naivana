package com.nirvana.pojo4json;

import java.util.List;

import org.springframework.data.domain.Page;

public class DPage<DATA> {

	private int totalPages;

	private long totalElements;

	private List<DATA> data;

	public DPage() {
	}

	public DPage(Page<DATA> page) {
		if(page==null){
			return;
		}
		this.totalPages = page.getTotalPages();
		this.totalElements = page.getTotalElements();
		this.data = page.getContent();
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}

	public List<DATA> getData() {
		return data;
	}

	public void setData(List<DATA> data) {
		this.data = data;
	}

}
