package com.wangtiansoft.KingDarts.common.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weitong on 17/4/1.
 */
public class PageResult<T> {

    private String pager;   //  下一页的请求参数，如果-1表示不存在下一页
    private List<T> list;   //  列表数据

    public PageResult(String pager, List<T> list) {
        this.pager = pager;
        this.list = list;
    }

    public PageResult(String pager) {
        this.list = new ArrayList<>();
        this.pager = pager;
    }

    public PageResult() {
        this.list = new ArrayList<>();
    }

    public String getPager() {
        return pager;
    }

    public void setPager(String pager) {
        this.pager = pager;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
