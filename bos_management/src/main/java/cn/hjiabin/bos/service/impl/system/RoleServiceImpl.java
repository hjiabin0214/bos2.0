package cn.hjiabin.bos.service.impl.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.hjiabin.bos.dao.system.IRoleRepository;
import cn.hjiabin.bos.domain.system.Role;
import cn.hjiabin.bos.domain.system.User;
import cn.hjiabin.bos.service.system.IRoleService;

@Service
@Transactional
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private IRoleRepository roleRepository;
	
	@Override
	public List<Role> findByUser(User user) {
		if(user.getUsername().equals("admin")){
			return roleRepository.findAll();
		}else {
			return roleRepository.findByUser(user.getId());
		}
	}

}
