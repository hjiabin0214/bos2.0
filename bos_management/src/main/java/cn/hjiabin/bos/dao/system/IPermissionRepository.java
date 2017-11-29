package cn.hjiabin.bos.dao.system;

import java.util.List;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import cn.hjiabin.bos.domain.system.Permission;

public interface IPermissionRepository extends JpaRepository<Permission, Integer> {

	@Query("from Permission p inner join fetch p.roles r inner join fetch r.users u where u.id = ?")
	List<Permission> findByUser(Integer id);

}
