package cn.hjiabin.bos.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cn.hjiabin.bos.domain.FixedArea;

public interface IFixedAreaRepository extends JpaRepository<FixedArea, String>,JpaSpecificationExecutor<FixedArea> {

}
