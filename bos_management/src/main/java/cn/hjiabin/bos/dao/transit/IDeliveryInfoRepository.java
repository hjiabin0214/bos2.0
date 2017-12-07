package cn.hjiabin.bos.dao.transit;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.hjiabin.bos.domain.transit.DeliveryInfo;

public interface IDeliveryInfoRepository extends JpaRepository<DeliveryInfo, Integer> {

}
