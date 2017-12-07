package cn.hjiabin.bos.service.system;

import java.util.List;

import cn.hjiabin.bos.domain.system.Role;
import cn.hjiabin.bos.domain.system.User;

public interface IRoleService {

	List<Role> findByUser(User user);

	List<Role> findAll();

	void saveRole(Role role, String[] permissionIds, String menuIds);

}
