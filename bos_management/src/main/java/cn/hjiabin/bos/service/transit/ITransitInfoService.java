package cn.hjiabin.bos.service.transit;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cn.hjiabin.bos.domain.transit.TransitInfo;

public interface ITransitInfoService {


	void createTransits(String wayBillIds);

	Page<TransitInfo> findPageQuery(Pageable pageable);

}
