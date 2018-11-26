package com.wangtiansoft.KingDarts.modules.sequence.service;

import com.wangtiansoft.KingDarts.core.support.common.IBaseService;
import com.wangtiansoft.KingDarts.persistence.entity.Sequence;

public interface SequenceService extends IBaseService<Sequence, Integer> {

	/***
	 * 获取下一序列值
	 * @param name
	 * @param stepBy
	 * @param len
	 * @return
	 */
	public String getNextSeq(String name, int stepBy ,int len);
}
