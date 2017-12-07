package cn.hjiabin.bos.service.transit;

import cn.hjiabin.bos.domain.transit.DeliveryInfo;

public interface IDeliveryInfoService {

	void save(String transitInfoId, DeliveryInfo deliveryInfo);

}
