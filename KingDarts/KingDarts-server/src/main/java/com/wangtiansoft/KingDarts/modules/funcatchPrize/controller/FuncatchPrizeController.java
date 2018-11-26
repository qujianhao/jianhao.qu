package com.wangtiansoft.KingDarts.modules.funcatchPrize.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.common.bean.ApiResult;
import com.wangtiansoft.KingDarts.common.bean.JQGirdPageResult;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.common.utils.StringUtils;
import com.wangtiansoft.KingDarts.constants.Constants;
import com.wangtiansoft.KingDarts.core.support.common.BaseController;
import com.wangtiansoft.KingDarts.modules.funcatchPrize.service.FuncatchPrizeService;
import com.wangtiansoft.KingDarts.persistence.entity.FuncatchPrize;
import com.wangtiansoft.KingDarts.results.core.FuncatchPrizeResult;

import tk.mybatis.mapper.entity.Example;

/**
 * Created by wt-templete-helper.
 */
@Controller
@RequestMapping("/a/funcatchPrize")
public class FuncatchPrizeController extends BaseController {

    @Resource
    private FuncatchPrizeService funcatchPrizeService;

    //  列表
    @PreAuthorize("hasPermission('','_FUNCATCHPRIZE:VIEW')")
    @RequestMapping("/funcatchPrize_list")
    public String funcatchPrize_list(@RequestParam Map<String, Object> paramMap) {
    	request.setAttribute("balance", funcatchPrizeService.getConsumeBalance());
        request.setAttribute("paramMap", paramMap);
        return "/a/funcatchPrize/funcatchPrize_list";
    }

    //  列表分页
    @PreAuthorize("hasPermission('','_FUNCATCHPRIZE:VIEW')")
    @PostMapping("/funcatchPrize_search")
    public
    @ResponseBody
    JQGirdPageResult funcatchPrize_search(@RequestParam Map<String, Object> paramMap, @ModelAttribute PageBean pageBean) {
        Page<Map> page = funcatchPrizeService.queryFuncatchPrizePageList(paramMap, pageBean);
        return makePageResult(page, FuncatchPrizeResult.class);
    }


    //  编辑页面
    @PreAuthorize("hasPermission('','_FUNCATCHPRIZE:EDIT')")
    @GetMapping("/funcatchPrize_edit")
    public String funcatchPrize_edit() {
        String prize_id = getParaValue("prize_id");
        FuncatchPrize entity = funcatchPrizeService.findById(Integer.valueOf(prize_id));
        request.setAttribute("entity", entity);
        return "/a/funcatchPrize/funcatchPrize_edit";
    }

    //  编辑保存
    @PreAuthorize("hasPermission('','_FUNCATCHPRIZE:EDIT')")
    @PostMapping("/funcatchPrize_edit")
    public
    @ResponseBody
    ApiResult funcatchPrize_edit(@ModelAttribute("entity") FuncatchPrize entity) {
    	if(StringUtils.isEmpty(entity.getIcon_url())) {
    		entity.setIcon_url("");
    		entity.setIcon_name("");
    	}else {
    		String icon_name = getParaValue("icon_url_names");
        	entity.setIcon_name(icon_name);
    	}
        funcatchPrizeService.updateByIdSelective(entity);
        FuncatchPrizeResult result = makeResult(entity, FuncatchPrizeResult.class);
        return ApiResult.success(result);
    }
    
   //  编辑消耗游戏点数
    @PreAuthorize("hasPermission('','_FUNCATCHPRIZE:EDIT')")
    @PostMapping("/funcatchPrize_consume")
    public
    @ResponseBody
    ApiResult funcatchPrize_consume() {
    	String worth = getParaValue("worth");
		Example example=new Example(FuncatchPrize.class);
		example.createCriteria().andEqualTo("position_num","5");
		FuncatchPrize funcatchPrize=funcatchPrizeService.findOneByExample(example);
		funcatchPrize.setWorth(new BigDecimal(worth));
		funcatchPrizeService.updateByIdSelective(funcatchPrize);
        FuncatchPrizeResult result = makeResult(funcatchPrize, FuncatchPrizeResult.class);
        return ApiResult.success(result);
    }
    

    //  新建页面
    @PreAuthorize("hasPermission('','_FUNCATCHPRIZE:ADD')")
    @GetMapping("/funcatchPrize_add")
    public String funcatchPrize_add() {
        return "/a/funcatchPrize/funcatchPrize_edit";
    }

    //  新建保存
    @PreAuthorize("hasPermission('','_FUNCATCHPRIZE:ADD')")
    @PostMapping("/funcatchPrize_add")
    public
    @ResponseBody
    ApiResult funcatchPrize_add(@ModelAttribute("entity") FuncatchPrize entity) {
    	String icon_name = getParaValue("icon_url_names");
    	entity.setIcon_name(icon_name);
    	entity.setIs_valid(Constants.True);
        funcatchPrizeService.save(entity);
        FuncatchPrizeResult result = makeResult(entity, FuncatchPrizeResult.class);
        return ApiResult.success(result);
    }

    //  保存前校验概率值
    @PreAuthorize("hasPermission('','_FUNCATCHPRIZE:EDIT')")
    @PostMapping("/funcatchPrize_valid")
    public
    @ResponseBody
    ApiResult funcatchPrize_valid() {
    	String prize_id=getParaValue("prize_id");
    	String win_rate=getParaValue("win_rate");
    	//查询除了 谢谢参与和当前的概率和值
    	Example example=new Example(FuncatchPrize.class);
		example.createCriteria().andNotEqualTo("prize_name", "谢谢参与")
		.andNotEqualTo("prize_id", prize_id);
		List<FuncatchPrize> funcatchPrizes=funcatchPrizeService.findAllByExample(example);
		BigDecimal sumWinRate=new BigDecimal(0);
		for (FuncatchPrize funcatchPrize : funcatchPrizes) {
			if(funcatchPrize.getWin_rate()!=null) {
				sumWinRate=sumWinRate.add(funcatchPrize.getWin_rate());
			}
		}
		//加上当前的概率值
		sumWinRate=sumWinRate.add(new BigDecimal(win_rate));
		//当前概率值是否小于等于1
		if(sumWinRate.compareTo(new BigDecimal(1))<=0) {
			//修改谢谢参与的概率值
			Example exampleOne=new Example(FuncatchPrize.class);
			exampleOne.createCriteria().andEqualTo("position_num","3");
			FuncatchPrize funcatchPrize=funcatchPrizeService.findOneByExample(exampleOne);
			funcatchPrize.setWin_rate(new BigDecimal(1).subtract(sumWinRate));
			funcatchPrizeService.updateByIdSelective(funcatchPrize);
			return ApiResult.success();
		}
        else {
	    	return ApiResult.fail("总概率不等于1,请重新填写中奖率");
	    }
    }
    
   //  重置配置
    @PreAuthorize("hasPermission('','_FUNCATCHPRIZE:EDIT')")
    @PostMapping("/funcatchPrize_reset")
    public
    @ResponseBody
    ApiResult funcatchPrize_reset() {
        List<FuncatchPrize> list=funcatchPrizeService.findValidFuncatchPrizeList();
        for (FuncatchPrize funcatchPrize : list) {
        	funcatchPrize.setIs_valid(Constants.False);
        	funcatchPrizeService.updateByIdSelective(funcatchPrize);
		}
        return ApiResult.success();
    }

    //  删除
    @PreAuthorize("hasPermission('','_FUNCATCHPRIZE:DELETE')")
    @PostMapping("/funcatchPrize_delete")
    public
    @ResponseBody
    ApiResult funcatchPrize_delete() {
        String prize_id = getParaValue("prize_id");
        FuncatchPrize entity = funcatchPrizeService.findById(Integer.valueOf(prize_id));
        entity.setIs_delete(Constants.True);
        funcatchPrizeService.updateByIdSelective(entity);
        return ApiResult.success();
    }

}



