package com.wangtiansoft.KingDarts.common.utils;

import com.github.pagehelper.PageHelper;
import com.wangtiansoft.KingDarts.common.bean.PageResult;
import com.wangtiansoft.KingDarts.constants.Constants;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;

import java.util.List;

/**
 *
 * pageNum 表示查询第几页, 从 0 开始,表示查询第一页
 *
 *
 // 复杂写法
 ApiPageHelper apiPageHelper = ApiPageHelper.startPage(0, 10);
 BaseExample example = new BaseExample(PermEntity.class);
 ApiPageHelper.build(apiPageHelper, permRepository.selectByExample(example));

 // 简单写法
 BaseExample example = new BaseExample(PermEntity.class);
 ApiPageHelper.startPage(0, 10).run(permRepository.selectByExample(example));

 */
public class ApiPageHelper {

    private Integer pageNum;
    private Integer begin;
    private Integer size;
    private Integer querySize;

    public ApiPageHelper() {

    }

    //  必须先调用分页方法
    public static ApiPageHelper startPage(Integer pageNum, Integer pageSize){
        ApiPageHelper helper = new ApiPageHelper();
        helper.pageNum = pageNum;
        helper.size = ObjectUtils.firstNonNull(pageSize, Constants.kApi_PageSize);
        helper.querySize = helper.size + 1;
        helper.begin = pageNum * helper.size;
        PageHelper.offsetPage(helper.begin, helper.begin + helper.querySize, false);
        return helper;
    }

    //  多查询一条数据，如果数量不足，则返回没有下一页
    public <T> PageResult<T> run(List<T> page){
        PageResult pageResult = new PageResult();
        if (CollectionUtils.isEmpty(page)){
            pageResult.setPager("-1");
        }else if (CollectionUtils.size(page) < size){
            pageResult.setPager("-1");
            pageResult.setList(page.subList(0, CollectionUtils.size(page)));
        }else if (CollectionUtils.size(page) >= size){
            pageResult.setPager(String.valueOf(pageNum + 1));
            pageResult.setList(page.subList(0, size));
        }
        return pageResult;
    }

    //  多查询一条数据，如果数量不足，则返回没有下一页
    public static <T> PageResult<T> build(ApiPageHelper helper, List<T> page){
        return helper.run(page);
    }


}
