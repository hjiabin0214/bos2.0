package cn.hjiabin.bos.service.system;

import java.util.List;

import cn.hjiabin.bos.domain.system.Permission;
import cn.hjiabin.bos.domain.system.User;

public interface IPermissionService {

	List<Permission> findByUser(User user);

}
