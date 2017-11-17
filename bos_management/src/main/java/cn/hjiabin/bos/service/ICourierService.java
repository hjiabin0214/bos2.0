package cn.hjiabin.bos.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import cn.hjiabin.bos.domain.base.Courier;

public interface ICourierService {

	public void save(Courier courier);

	public Page<Courier> findPageData(Specification<Courier> specification, Pageable pageable);

	public void delBatch(String[] idArray);

	public List<Courier> findNoAssociation();

}
