package com.cbec.b2b.common;

import java.io.Serializable;
import java.util.List;

import com.github.pagehelper.Page;

public class PageInfo<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<T> list;
    private Pagination pagination = new Pagination();
    
    public PageInfo() {
    }

    /**
     * 包装Page对象
     *
     * @param list
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public PageInfo(List<T> list) {
        boolean isFirstPage = false;
        boolean isLastPage = false;
        Page page = (Page) list;
        int pageNum = page.getPageNum();
        int pageSize = page.getPageSize();
        int pages = page.getPages();
        long total = page.getTotal();
        isFirstPage = pageNum == 1;
        isLastPage = pageNum == pages;
        this.list = page;
        pagination.setPageNum(pageNum);
        pagination.setPageSize(pageSize);
        pagination.setPages(pages);
        pagination.setTotal(total);
        pagination.setFirstPage(isFirstPage);
        pagination.setLastPage(isLastPage);
    }

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

   
}
