package cn.hjiabin.bos.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import cn.hjiabin.bos.domain.base.FixedArea;

public interface IFixedAreaService {

	void save(FixedArea model);

	Page<FixedArea> findPageQuery(Specification<FixedArea> specification, Pageable pageable);

	void associationCourierToFixedArea(FixedArea model, Integer courierId, Integer takeTimeId);

}
