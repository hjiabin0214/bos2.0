package cn.hjiabin.bos.dao.take_delivery;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.hjiabin.bos.domain.take_delivery.WayBill;

public interface IWayBillRepository extends JpaRepository<WayBill, Integer> {

	WayBill findByWayBillNum(String wayBillNum);


}
