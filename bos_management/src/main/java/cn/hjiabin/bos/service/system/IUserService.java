package cn.hjiabin.bos.service.system;

import cn.hjiabin.bos.domain.system.User;

public interface IUserService {

	User findByUsername(String username);


}
