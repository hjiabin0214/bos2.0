package cn.hjiabin.bos.service.impl.system;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.hjiabin.bos.dao.system.IMenuRepository;
import cn.hjiabin.bos.dao.system.IPermissionRepository;
import cn.hjiabin.bos.dao.system.IRoleRepository;
import cn.hjiabin.bos.domain.system.Menu;
import cn.hjiabin.bos.domain.system.Permission;
import cn.hjiabin.bos.domain.system.Role;
import cn.hjiabin.bos.domain.system.User;
import cn.hjiabin.bos.service.system.IRoleService;

@Service
@Transactional
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private IRoleRepository roleRepository;
	
	@Autowired
	private IPermissionRepository permissionRepository;
	
	@Autowired
	private IMenuRepository menuRepository;
	
	@Override
	public List<Role> findByUser(User user) {
		if(user.getUsername().equals("admin")){
			return roleRepository.findAll();
		}else {
			return roleRepository.findByUser(user.getId());
		}
	}

	@Override
	public List<Role> findAll() {
		return roleRepository.findAll();
	}

	@Override
	public void saveRole(Role role, String[] permissionIds, String menuIds) {
		roleRepository.save(role);
		
		if(permissionIds != null){
			for (String permissionId : permissionIds) {
				Permission permission = permissionRepository.findOne(Integer.parseInt(permissionId));
				role.getPermissions().add(permission);
			}
		}
		if(StringUtils.isNoneBlank(menuIds)){
			String[] menuIdArray = menuIds.split(",");
			for (String menuId : menuIdArray) {
				Menu menu = menuRepository.findOne(Integer.parseInt(menuId));
				role.getMenus().add(menu);
			}
		}
	}

}
