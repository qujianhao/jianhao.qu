package com.wangtiansoft.KingDarts.common.bean;

import java.io.Serializable;

/**
 * Created by weitong on 17/3/21.
 */
public class PageBean implements Serializable{

	public static final int DEFAULT_PAGE_SIZE = 10;
	
    private String _search;
    private String nd;
    private Integer rows;
    private Integer page;
    private String sidx;
    private String sord;

    public PageBean() {
    	this.page = 1;
        this.rows = DEFAULT_PAGE_SIZE;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public String get_search() {
        return _search;
    }

    public void set_search(String _search) {
        this._search = _search;
    }

    public String getNd() {
        return nd;
    }

    public void setNd(String nd) {
        this.nd = nd;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public String getSidx() {
        return sidx;
    }

    public void setSidx(String sidx) {
        this.sidx = sidx;
    }

    public String getSord() {
        return sord;
    }

    public void setSord(String sord) {
        this.sord = sord;
    }


//    public PageRequest buildPageRequest(){
//        int iPage = NumberUtils.toInt(page, 1) - 1;
//        int iRow = NumberUtils.toInt(rows, Constants.kAdmin_PageSize);
//        Sort sort = null;
//        if (sidx != null){
//
//        }
//        PageRequest pageRequest = new PageRequest(iPage, iRow, sort);
//        return pageRequest;
//    }

}
