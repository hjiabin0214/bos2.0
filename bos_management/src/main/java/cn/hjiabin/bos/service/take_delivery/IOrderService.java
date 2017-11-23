package cn.hjiabin.bos.service.take_delivery;

import cn.hjiabin.bos.domain.take_delivery.Order;

public interface IOrderService {

	Order findByOrderNum(String orderNum);

}
