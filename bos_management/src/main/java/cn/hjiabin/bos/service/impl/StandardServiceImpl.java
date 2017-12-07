package cn.hjiabin.bos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.hjiabin.bos.dao.IStandardRepository;
import cn.hjiabin.bos.domain.base.Standard;
import cn.hjiabin.bos.service.IStandardService;

@Service
@Transactional
public class StandardServiceImpl implements IStandardService {

	@Autowired
	private IStandardRepository standardRepository;
	
	@Override
	@CacheEvict(value="standard", allEntries=true)
	public void save(Standard standard) {
		standardRepository.save(standard);
	}

	@Override
	@Cacheable(value="standard", key = "#pageable.pageNumber+'_'+#pageable.pageSize")
	public Page<Standard> findPageData(Pageable pageable) {
		return standardRepository.findAll(pageable);
	}

	@Override
	@Cacheable("standard")
	public List<Standard> findAll() {
		return standardRepository.findAll();
	}
}
