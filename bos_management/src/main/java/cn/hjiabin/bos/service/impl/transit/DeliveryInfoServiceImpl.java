package cn.hjiabin.bos.service.impl.transit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.hjiabin.bos.dao.transit.IDeliveryInfoRepository;
import cn.hjiabin.bos.dao.transit.ITransitInfoRepository;
import cn.hjiabin.bos.domain.transit.DeliveryInfo;
import cn.hjiabin.bos.domain.transit.TransitInfo;
import cn.hjiabin.bos.service.transit.IDeliveryInfoService;

@Service
@Transactional
public class DeliveryInfoServiceImpl implements IDeliveryInfoService {

	@Autowired
	private IDeliveryInfoRepository deliveryInfoRepository;
	
	@Autowired
	private ITransitInfoRepository transitInfoRepository;
	
	@Override
	public void save(String transitInfoId, DeliveryInfo deliveryInfo) {
		deliveryInfoRepository.save(deliveryInfo);
		
		TransitInfo transitInfo = transitInfoRepository.findOne(Integer.parseInt(transitInfoId));
		transitInfo.setDeliveryInfo(deliveryInfo);
		transitInfo.setStatus("开始配送");
	}

}
