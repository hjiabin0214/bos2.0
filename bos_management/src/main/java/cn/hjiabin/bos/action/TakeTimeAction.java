package cn.hjiabin.bos.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.hjiabin.bos.action.base.BaseAction;
import cn.hjiabin.bos.domain.base.TakeTime;
import cn.hjiabin.bos.service.ITakeTimeService;

@ParentPackage("json-default")
@Namespace("/")
@Controller
@Scope("prototype")
public class TakeTimeAction extends BaseAction<TakeTime> {

	@Autowired
	private ITakeTimeService takeTimeServiceImpl;
	
	@Action(value="takeTime_findAll",results={@Result(name="success",type="json")})
	public String findAll(){
		List<TakeTime> takeTimes = takeTimeServiceImpl.findAll();
		ActionContext.getContext().getValueStack().push(takeTimes);
		return SUCCESS;
	}
}
