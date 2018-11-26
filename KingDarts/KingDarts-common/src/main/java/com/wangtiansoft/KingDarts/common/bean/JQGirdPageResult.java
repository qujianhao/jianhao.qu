package com.wangtiansoft.KingDarts.common.bean;

import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.common.utils.BeanUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/25 0025.
 */
public class JQGirdPageResult implements Serializable {

    private Integer total;   //  总页数
    private List rows;   //  数据列表
    private Long records;   //    总记录数
    private Integer page;   //   当前页数

    public JQGirdPageResult(Page page) {
        this.total = page.getPages();
        this.rows = page.getResult();
        this.records = page.getTotal();
        this.page = page.getPageNum();
    }



    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }

    public Long getRecords() {
        return records;
    }

    public void setRecords(Long records) {
        this.records = records;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }
}
