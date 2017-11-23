package cn.hjiabin.bos.service.take_delivery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cn.hjiabin.bos.domain.take_delivery.WayBill;

public interface IWayBillService {

	void save(WayBill model);

	Page<WayBill> findPageData(Pageable pageable);

	WayBill findByWayBillNum(String wayBillNum);

}
