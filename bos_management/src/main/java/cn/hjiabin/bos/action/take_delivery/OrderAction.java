package cn.hjiabin.bos.action.take_delivery;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.hjiabin.bos.action.base.BaseAction;
import cn.hjiabin.bos.domain.take_delivery.Order;
import cn.hjiabin.bos.service.take_delivery.IOrderService;

@ParentPackage("json-default")
@Namespace("/")
@Controller
@Scope("prototype")
public class OrderAction extends BaseAction<Order> {

	@Autowired
	private IOrderService orderServiceImpl;
	
	@Action(value="order_findByOrderNum",results={@Result(name="success",type="json")})
	public String findByOrderNum(){
		
		Order order = orderServiceImpl.findByOrderNum(model.getOrderNum());
		Map<String, Object> result = new HashMap<String, Object>();
		if(order == null){
			result.put("success", false);
		}else {
			result.put("success", true);
			result.put("orderData", order);
		}
		ActionContext.getContext().getValueStack().push(result);
		return SUCCESS;
	}
}
