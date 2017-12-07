package cn.hjiabin.bos.dao.transit;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.hjiabin.bos.domain.transit.TransitInfo;

public interface ITransitInfoRepository extends JpaRepository<TransitInfo, Integer> {

}
