package com.bank.dms.entity;

public class Page {

    private int totalCount=0;

	private int totalPage=1;
	
	private int page = 1;
	
	private int size=1;

    public Page() {
    }
	
    public Page(int totalPage, int page, int size) {
        this.totalPage = totalPage;
        this.page = page;
        this.size = size;
    }
	
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
}
