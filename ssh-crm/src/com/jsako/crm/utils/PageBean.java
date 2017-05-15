package com.jsako.crm.utils;

import java.util.List;

import com.jsako.crm.domain.Customer;

public class PageBean {
	// 封装客户信息
	private List list;
	// 当前页数
	private Integer currentPage;
	// 总条数
	private Integer totalCount;
	// 每页显示条数
	private Integer pageSize;
	// 总页数
	private Integer totalPage;

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	// 获得起始条数
	public Integer getStart() {
		return (this.currentPage - 1) * this.pageSize;
	}

	public PageBean(Integer currentPage, Integer totalCount, Integer pageSize) {
		this.totalCount = totalCount;
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		if (this.currentPage == null) {
			// 如果页面没有指定显示哪一页,显示第一页
			this.currentPage = 1;
		}
		if (this.pageSize == null) {
			// 如果每页显示条数没有指定,默认一页显示3条
			this.pageSize = 3;
		}
		// 计算总页数
		this.totalPage = (this.totalCount + this.pageSize - 1) / this.pageSize;
		// 判断当前页数是否小于1且不大于总页数
		if (this.currentPage < 1) {
			// 小于一就等于一
			this.currentPage = 1;
		} else if (this.currentPage > this.totalPage) {
			// 大于总页数就等于总页数
			this.currentPage = this.totalPage;
		}

	}

}
