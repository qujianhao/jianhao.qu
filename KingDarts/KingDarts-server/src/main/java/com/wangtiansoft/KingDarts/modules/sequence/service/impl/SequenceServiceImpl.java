package com.wangtiansoft.KingDarts.modules.sequence.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangtiansoft.KingDarts.core.support.common.BaseService;
import com.wangtiansoft.KingDarts.modules.robFloors.service.RobFloorsService;
import com.wangtiansoft.KingDarts.modules.sequence.service.SequenceService;
import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.dao.master.SequenceMapper;
import com.wangtiansoft.KingDarts.persistence.entity.RobFloors;
import com.wangtiansoft.KingDarts.persistence.entity.Sequence;

@Transactional
@Service("sequenceService")
public class SequenceServiceImpl extends BaseService<Sequence, Integer> implements SequenceService{

	@Autowired
	private SequenceMapper sequenceMapper;

	@Override
	public BaseMapper getBaseMapper() {
		return sequenceMapper;
	}

	@Override
	public synchronized String getNextSeq(String name, int stepBy, int len) {
		int num = sequenceMapper.getCurrNum(name);
		int nextNum = num + stepBy;
		sequenceMapper.updateSeq(name, nextNum);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < len - String.valueOf(nextNum).length(); i++) {
			sb.append("0");
		}
		sb.append(nextNum);
		return sb.toString();
	}
}
