package cn.hjiabin.bos.dao.system;

import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cn.hjiabin.bos.domain.system.Role;

public interface IRoleRepository extends JpaRepository<Role, Integer> {

	@Query("from Role r inner join fetch r.users u where u.id = ?")
	List<Role> findByUser(Integer id);

}
