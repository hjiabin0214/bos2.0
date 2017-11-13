package cn.hjiabin.bos.service.impl;

import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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

	@Override
	public List<Courier> findNoassociation() {
		Specification<Courier> specification = new Specification<Courier>() {
			@Override
			public Predicate toPredicate(Root<Courier> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate p = cb.isEmpty(root.get("fixedAreas").as(Set.class));
				return p;
			}
		};
		return courierRepository.findAll(specification);
	}
}
