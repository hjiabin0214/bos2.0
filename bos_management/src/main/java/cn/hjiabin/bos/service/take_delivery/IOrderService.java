package cn.hjiabin.bos.service.take_delivery;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import cn.hjiabin.bos.domain.take_delivery.Order;

public interface IOrderService {

	Order findByOrderNum(String orderNum);

	@Path("/order")
	@POST
	@Consumes({"application/xml","application/json"})
	public void saveOrder(Order order);
}
