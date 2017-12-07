package cn.hjiabin.bos.service.impl.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.hjiabin.bos.dao.system.IPermissionRepository;
import cn.hjiabin.bos.domain.system.Permission;
import cn.hjiabin.bos.domain.system.User;
import cn.hjiabin.bos.service.system.IPermissionService;

@Service
@Transactional
public class PermissionServiceImpl implements IPermissionService {

	@Autowired
	private IPermissionRepository permissionRepository;
	
	@Override
	public List<Permission> findByUser(User user) {
		if(user.getUsername().equals("admin")){
			return permissionRepository.findAll();
		}else {
			return permissionRepository.findByUser(user.getId());
		}
	}

	@Override
	public List<Permission> findAll() {
		return permissionRepository.findAll();
	}

	@Override
	public void save(Permission permission) {
		permissionRepository.save(permission);
	}

}
