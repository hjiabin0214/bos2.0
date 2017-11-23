package cn.hjiabin.bos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.hjiabin.bos.dao.ITakeTimeRepository;
import cn.hjiabin.bos.domain.base.TakeTime;
import cn.hjiabin.bos.service.ITakeTimeService;

@Service
@Transactional
public class TakeTimeServiceImpl implements ITakeTimeService {

	@Autowired
	private ITakeTimeRepository takeTimeRepository;
	
	@Override
	public List<TakeTime> findAll() {
		return takeTimeRepository.findAll();
	}

}
