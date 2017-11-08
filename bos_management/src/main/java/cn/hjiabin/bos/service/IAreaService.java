package cn.hjiabin.bos.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import cn.hjiabin.bos.domain.Area;

public interface IAreaService {

	public void saveBatch(List<Area> areas);

	public Page<Area> findPageData(Specification<Area> specification, Pageable pageable);

}
