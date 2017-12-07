package cn.hjiabin.bos.service.impl.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.hjiabin.bos.dao.system.IRoleRepository;
import cn.hjiabin.bos.dao.system.IUserRepository;
import cn.hjiabin.bos.domain.system.Role;
import cn.hjiabin.bos.domain.system.User;
import cn.hjiabin.bos.service.system.IUserService;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	private IRoleRepository roleRepository;
	
	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public void saveUser(User user, String[] roleIds) {
		userRepository.save(user);
		if(roleIds != null){
			for (String roleId : roleIds) {
				Role role = roleRepository.findOne(Integer.parseInt(roleId));
				user.getRoles().add(role);
			}
		}
	}

}
