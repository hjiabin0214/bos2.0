package cn.hjiabin.bos.dao.take_delivery;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.hjiabin.bos.domain.base.Area;
import cn.hjiabin.bos.domain.take_delivery.Order;

public interface IOrderRepository extends JpaRepository<Order, Integer> {

	Order findByOrderNum(String orderNum);

}
