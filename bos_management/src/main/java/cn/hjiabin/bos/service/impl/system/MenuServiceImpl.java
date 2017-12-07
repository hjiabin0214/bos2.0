package cn.hjiabin.bos.service.impl.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.hjiabin.bos.dao.system.IMenuRepository;
import cn.hjiabin.bos.domain.system.Menu;
import cn.hjiabin.bos.domain.system.User;
import cn.hjiabin.bos.service.system.IMenuService;

@Service
@Transactional
public class MenuServiceImpl implements IMenuService {

	@Autowired
	private IMenuRepository menuRepository;
	
	@Override
	public List<Menu> findAll() {
		return menuRepository.findAll();
	}

	@Override
	public void save(Menu menu) {
		if(menu.getParentMenu() != null && menu.getParentMenu().getId() == 0){
			menu.setParentMenu(null);
		}
		menuRepository.save(menu);
	}

	@Override
	public List<Menu> findByUser(User user) {
		if(user.getUsername().equals("admin")){
			return menuRepository.findAll();
		}else {
			return menuRepository.findByUser(user.getId());
		}
	}

}
