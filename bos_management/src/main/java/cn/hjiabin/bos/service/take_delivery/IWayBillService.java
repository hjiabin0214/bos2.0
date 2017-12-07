package cn.hjiabin.bos.service.take_delivery;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cn.hjiabin.bos.domain.take_delivery.WayBill;

public interface IWayBillService {

	void save(WayBill model);

	Page<WayBill> findPageData(WayBill model, Pageable pageable);

	WayBill findByWayBillNum(String wayBillNum);
	
	List<WayBill> findwayBills(WayBill wayBill);

}
