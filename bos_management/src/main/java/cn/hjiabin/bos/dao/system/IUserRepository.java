package cn.hjiabin.bos.dao.system;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.hjiabin.bos.domain.system.User;

public interface IUserRepository extends JpaRepository<User, Integer> {

	User findByUsername(String username);

}
