package cn.hjiabin.bos.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import cn.hjiabin.bos.domain.Courier;

public interface ICourierRepository extends JpaRepository<Courier, Integer>,JpaSpecificationExecutor<Courier> {

	@Query(value="update from Courier set deltag='1' where id = ?")
	@Modifying
	public void updateDelTag(Integer id);

}
