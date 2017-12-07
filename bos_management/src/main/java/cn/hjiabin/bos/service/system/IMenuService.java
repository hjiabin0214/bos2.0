package cn.hjiabin.bos.service.system;

import java.util.List;

import cn.hjiabin.bos.domain.system.Menu;
import cn.hjiabin.bos.domain.system.User;

public interface IMenuService {

	List<Menu> findAll();

	void save(Menu menu);

	List<Menu> findByUser(User user);

}
