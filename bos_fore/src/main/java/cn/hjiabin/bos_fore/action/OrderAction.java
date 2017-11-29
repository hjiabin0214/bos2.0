package cn.hjiabin.bos_fore.action;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.hjiabin.bos.constans.Constants;
import cn.hjiabin.bos.domain.base.Area;
import cn.hjiabin.bos.domain.take_delivery.Order;
import cn.hjiabin.crm.domain.Customer;

@ParentPackage("json-default")
@Namespace("/")
@Controller
@Scope("prototype")
public class OrderAction extends BaseAction<Order> {

	private String sendAreaInfo;
	private String recAreaInfo;
	
	public void setSendAreaInfo(String sendAreaInfo) {
		this.sendAreaInfo = sendAreaInfo;
	}

	public void setRecAreaInfo(String recAreaInfo) {
		this.recAreaInfo = recAreaInfo;
	}

	@Action(value="order_add",results={@Result(name="success",type="redirect",location="index.html")})
	public String add(){
		
		Area sendArea = new Area();
		String[] sendAreaData = sendAreaInfo.split("/");
		sendArea.setProvince(sendAreaData[0]);
		sendArea.setCity(sendAreaData[1]);
		sendArea.setDistrict(sendAreaData[2]);
		
		Area recArea = new Area();
		String[] recAreaData = recAreaInfo.split("/");
		recArea.setProvince(recAreaData[0]);
		recArea.setCity(recAreaData[1]);
		recArea.setDistrict(recAreaData[2]);
		
		model.setSendArea(sendArea);
		model.setRecArea(recArea);
		
		Customer customer = (Customer) ServletActionContext.getRequest().getSession().getAttribute("customer");
		model.setCustomer_id(customer.getId());
		
		WebClient.create(Constants.BOS_MANAGEMENT_URL+"/services/orderService/order").type(MediaType.APPLICATION_JSON).post(model);
		
		return SUCCESS;
	}
}
