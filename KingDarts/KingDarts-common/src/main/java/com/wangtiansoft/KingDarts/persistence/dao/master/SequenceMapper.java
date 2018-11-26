package com.wangtiansoft.KingDarts.persistence.dao.master;

import org.apache.ibatis.annotations.Param;

import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.entity.Sequence;

/**
 * 序列数据库访问类
 * @author Administrator
 *
 */
public interface SequenceMapper extends BaseMapper<Sequence>{

	/***
	 * 获取序列数值
	 * @param name
	 * @return
	 */
	public int getCurrNum(String name);
	
	/***
	 * 更新序列值
	 * @param name
	 * @param nextNum
	 */
	public void updateSeq(@Param("name")String name, @Param("nextNum")int nextNum);
}
