package cn.hjiabin.bos.service.impl.take_delivery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.hjiabin.bos.dao.take_delivery.IOrderRepository;
import cn.hjiabin.bos.domain.take_delivery.Order;
import cn.hjiabin.bos.service.take_delivery.IOrderService;

@Service
@Transactional
public class OrderServiceImpl implements IOrderService {

	@Autowired
	private IOrderRepository orderRepository;
	
	@Override
	public Order findByOrderNum(String orderNum) {
		return orderRepository.findByOrderNum(orderNum);
	}

}
