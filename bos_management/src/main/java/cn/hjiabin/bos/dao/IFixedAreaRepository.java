package cn.hjiabin.bos.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cn.hjiabin.bos.domain.base.Area;
import cn.hjiabin.bos.domain.base.FixedArea;

public interface IFixedAreaRepository extends JpaRepository<FixedArea, String>,JpaSpecificationExecutor<FixedArea> {


}
