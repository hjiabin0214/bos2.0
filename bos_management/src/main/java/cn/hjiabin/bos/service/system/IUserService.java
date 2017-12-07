package cn.hjiabin.bos.service.system;

import java.util.List;

import cn.hjiabin.bos.domain.system.User;

public interface IUserService {

	User findByUsername(String username);

	List<User> findAll();

	void saveUser(User user, String[] roleIds);


}
