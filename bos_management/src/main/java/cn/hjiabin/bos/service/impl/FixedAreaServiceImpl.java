package cn.hjiabin.bos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.hjiabin.bos.dao.IFixedAreaRepository;
import cn.hjiabin.bos.domain.FixedArea;
import cn.hjiabin.bos.service.IFixedAreaService;

@Service
@Transactional
public class FixedAreaServiceImpl implements IFixedAreaService {

	@Autowired
	private IFixedAreaRepository fixedAreaRepository;

	@Override
	public void save(FixedArea fixedArea) {
		fixedAreaRepository.save(fixedArea);
	}

	@Override
	public Page<FixedArea> findPageQuery(Specification<FixedArea> specification, Pageable pageable) {
		return fixedAreaRepository.findAll(specification, pageable);
	}
}
