package cn.hjiabin.bos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.hjiabin.bos.dao.ICourierRepository;
import cn.hjiabin.bos.dao.IFixedAreaRepository;
import cn.hjiabin.bos.dao.ITakeTimeRepository;
import cn.hjiabin.bos.domain.base.Courier;
import cn.hjiabin.bos.domain.base.FixedArea;
import cn.hjiabin.bos.domain.base.TakeTime;
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

	@Autowired
	private ICourierRepository courierRepository;
	@Autowired
	private ITakeTimeRepository takeTimeRepository;
	
	
	public void setCourierRepository(ICourierRepository courierRepository) {
		this.courierRepository = courierRepository;
	}

	public void setTakeTimeRepository(ITakeTimeRepository takeTimeRepository) {
		this.takeTimeRepository = takeTimeRepository;
	}

	@Override
	public void associationCourierToFixedArea(FixedArea fixedAera, Integer courierId, Integer takeTimeId) {
		FixedArea persistFixedArea = fixedAreaRepository.findOne(fixedAera.getId());
		Courier courier = courierRepository.findOne(courierId);
		TakeTime takeTime = takeTimeRepository.findOne(takeTimeId);
		persistFixedArea.getCouriers().add(courier);
		courier.setTakeTime(takeTime);
	}
}
