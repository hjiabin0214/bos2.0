package cn.hjiabin.bos.action.system;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.hjiabin.bos.action.base.BaseAction;
import cn.hjiabin.bos.domain.system.Menu;
import cn.hjiabin.bos.domain.system.User;
import cn.hjiabin.bos.service.system.IMenuService;

@ParentPackage("json-default")
@Namespace("/")
@Controller
@Scope("prototype")
public class MenuAction extends BaseAction<Menu> {

	@Autowired
	private IMenuService menuServiceImpl;
	
	@Action(value="menu_list", results={@Result(name="success", type="json")})
	public String list(){
		
		List<Menu> menus = menuServiceImpl.findAll();
		ActionContext.getContext().getValueStack().push(menus);
		
		return SUCCESS;
	}
	
	@Action(value="menu_save", results={@Result(name="success", type="redirect", location="./pages/system/menu.html")})
	public String save(){
		
		menuServiceImpl.save(model);
		return SUCCESS;
	}
	
	@Action(value="menu_showMenu", results={@Result(name="success", type="json")})
	public String showMenu(){
		
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();
		List<Menu> menus = menuServiceImpl.findByUser(user);
		ActionContext.getContext().getValueStack().push(menus);
		return SUCCESS;
	}
}
