package cn.hjiabin.bos.action.system;

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
import cn.hjiabin.bos.domain.system.Role;
import cn.hjiabin.bos.service.system.IRoleService;

@ParentPackage("json-default")
@Namespace("/")
@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {

	@Autowired
	private IRoleService roleServiceImpl;
	
	@Action(value="role_list", results={@Result(name="success", type="json")})
	public String list(){
		
		List<Role> roles = roleServiceImpl.findAll();
		ActionContext.getContext().getValueStack().push(roles);
		return SUCCESS;
	}
	
	private String[] permissionIds;
	private String menuIds;
	
	public void setPermissionIds(String[] permissionIds) {
		this.permissionIds = permissionIds;
	}

	public void setMenuIds(String menuIds) {
		this.menuIds = menuIds;
	}

	@Action(value="role_save", results={@Result(name="success", type="redirect", location="./pages/system/role.html")})
	public String save(){
		roleServiceImpl.saveRole(model, permissionIds, menuIds);
		return SUCCESS;
	}
}
