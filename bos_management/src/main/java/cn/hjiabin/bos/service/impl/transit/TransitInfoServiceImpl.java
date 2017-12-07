package cn.hjiabin.bos.service.impl.transit;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.hjiabin.bos.dao.take_delivery.IWayBillRepository;
import cn.hjiabin.bos.dao.transit.ITransitInfoRepository;
import cn.hjiabin.bos.domain.take_delivery.WayBill;
import cn.hjiabin.bos.domain.transit.TransitInfo;
import cn.hjiabin.bos.index.IWayBillIndexRepository;
import cn.hjiabin.bos.service.transit.ITransitInfoService;

@Service
@Transactional
public class TransitInfoServiceImpl implements ITransitInfoService {

	@Autowired
	private IWayBillRepository wayBillRepository;
	
	@Autowired
	private ITransitInfoRepository transitInfoRepository;
	
	@Autowired
	private IWayBillIndexRepository wayBillIndexRepository;
	
	@Override
	public void createTransits(String wayBillIds) {
		if(StringUtils.isNotBlank(wayBillIds)){
			for (String wayBillId : wayBillIds.split(",")) {
				WayBill wayBill = wayBillRepository.findOne(Integer.parseInt(wayBillId));
				if(wayBill.getSignStatus() == 1){
					TransitInfo transitInfo = new TransitInfo();
					transitInfo.setWayBill(wayBill);
					transitInfo.setStatus("出入库中转");
					transitInfoRepository.save(transitInfo);
					
					wayBill.setSignStatus(2);
					wayBillIndexRepository.save(wayBill);
				}
			}
		}
	}

	@Override
	public Page<TransitInfo> findPageQuery(Pageable pageable) {
		return transitInfoRepository.findAll(pageable);
	}

}
