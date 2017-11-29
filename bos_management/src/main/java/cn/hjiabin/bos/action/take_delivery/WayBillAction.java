package cn.hjiabin.bos.action.take_delivery;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.hjiabin.bos.action.base.BaseAction;
import cn.hjiabin.bos.domain.take_delivery.WayBill;
import cn.hjiabin.bos.service.take_delivery.IWayBillService;

@ParentPackage("json-default")
@Namespace("/")
@Controller
@Scope("prototype")
public class WayBillAction extends BaseAction<WayBill> {

	private static final Logger logger = Logger.getLogger(WayBill.class);
	
	@Autowired
	private IWayBillService wayBillServiceImpl;
	
	@Action(value="wayBill_save",results={@Result(name="success",type="json")})
	public String save(){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if(model.getOrder() != null && (model.getOrder().getId() == null || model.getOrder().getId() == 0)){
				model.setOrder(null);
			}
			
			wayBillServiceImpl.save(model);
			result.put("success", true);
			result.put("msg", "保存运单成功!");
			logger.info("保存运单成功,运单号:"+model.getWayBillNum());
		}catch (Exception e){
			e.printStackTrace();
			result.put("success", false);
			result.put("msg", "保存运单失败!");
			logger.error("保存运单失败,运单号:"+model.getWayBillNum());
		}
		ActionContext.getContext().getValueStack().push(result);
		return SUCCESS;
	}
	
	@Action(value="wayBill_pageQuery",results={@Result(name="success",type="json")})
	public String pageQuery(){
		
		Pageable pageable = new PageRequest(page-1, rows, new Sort(new Sort.Order(Sort.Direction.DESC, "id")));
		Page<WayBill> pageData = wayBillServiceImpl.findPageData(model, pageable);
		pushPageDataToValueStack(pageData);
		return SUCCESS;
	}
	
	@Action(value="wayBill_findByWayBillNum",results={@Result(name="success",type="json")})
	public String findByWayBillNum(){
		
		WayBill wayBill = wayBillServiceImpl.findByWayBillNum(model.getWayBillNum());
		Map<String, Object> result = new HashMap<String, Object>();
		if(wayBill == null){
			result.put("success", false);
		}else {
			result.put("success", true);
			result.put("wayBillData", wayBill);
		}
		ActionContext.getContext().getValueStack().push(result);
		return SUCCESS;
	}
}
