package cn.hjiabin.bos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import cn.hjiabin.bos.domain.Standard;

public interface IStandardRepository extends JpaRepository<Standard, Integer> {

	public List<Standard> findByName(String name);
	
	@Query(value="from Standard where name = ?",nativeQuery=false)
	public List<Standard> queryName(String name);
	
	@Query(value="update Standard set minLength = ?2 where id = ?1")
	@Modifying
	public void updataMinLength(Integer id, Integer minLength);
}
