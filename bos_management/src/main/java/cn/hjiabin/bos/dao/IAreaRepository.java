package cn.hjiabin.bos.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cn.hjiabin.bos.domain.base.Area;

public interface IAreaRepository extends JpaRepository<Area, String>,JpaSpecificationExecutor<Area> {

	Area findByProvinceAndCityAndDistrict(String province, String city, String district);

}
