package cn.hjiabin.bos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.hjiabin.bos.dao.ICourierRepository;
import cn.hjiabin.bos.domain.Courier;
import cn.hjiabin.bos.service.ICourierService;

@Service
@Transactional
public class CourierServiceImpl implements ICourierService {

	@Autowired
	private ICourierRepository courierRepository;
	
	@Override
	public void save(Courier courier) {
		courierRepository.save(courier);
	}

	@Override
	public Page<Courier> findPageData(Specification<Courier> specification, Pageable pageable) {
		return courierRepository.findAll(specification,pageable);
	}

	@Override
	public void delBatch(String[] idArray) {
		for (String idStr : idArray) {
			Integer id = Integer.parseInt(idStr);
			courierRepository.updateDelTag(id);
		}
	}


}
