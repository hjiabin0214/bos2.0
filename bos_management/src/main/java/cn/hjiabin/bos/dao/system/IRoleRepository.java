package cn.hjiabin.bos.dao.system;

import java.util.List;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import cn.hjiabin.bos.domain.system.Role;

public interface IRoleRepository extends JpaRepository<Role, Integer> {

	@Query("select r from Role r inner join r.users u where u.id = ?")
	List<Role> findByUser(Integer id);

}
