package cn.hjiabin.bos.action.transit;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.hjiabin.bos.action.base.BaseAction;
import cn.hjiabin.bos.domain.transit.InOutStorageInfo;
import cn.hjiabin.bos.service.transit.IInOutStorageInfoService;

@ParentPackage("json-default")
@Namespace("/")
@Controller
@Scope("prototype")
public class InOutStorageInfoAction extends BaseAction<InOutStorageInfo> {

	@Autowired
	private IInOutStorageInfoService inOutStorageInfoServiceImpl;
	
	private String transitInfoId;
	
	public void setTransitInfoId(String transitInfoId) {
		this.transitInfoId = transitInfoId;
	}

	@Action(value="inOutStore_save", results={@Result(name="success", type="redirect", location="./pages/transit/transitinfo.html")})
	public String save(){
		
		inOutStorageInfoServiceImpl.save(transitInfoId, model);
		return SUCCESS;
	}
}
