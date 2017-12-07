package cn.hjiabin.bos.service.impl.transit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.hjiabin.bos.dao.transit.ISignInfoRepository;
import cn.hjiabin.bos.dao.transit.ITransitInfoRepository;
import cn.hjiabin.bos.domain.transit.SignInfo;
import cn.hjiabin.bos.domain.transit.TransitInfo;
import cn.hjiabin.bos.index.IWayBillIndexRepository;
import cn.hjiabin.bos.service.transit.ISignInfoService;

@Service
@Transactional
public class SignInfoServiceImpl implements ISignInfoService {

	@Autowired
	private ISignInfoRepository signInfoRepository;
	
	@Autowired
	private ITransitInfoRepository transitInfoRepository;
	
	@Autowired
	private IWayBillIndexRepository wayBillIndexRepository;
	
	@Override
	public void save(String transitInfoId, SignInfo signInfo) {
		signInfoRepository.save(signInfo);
		
		TransitInfo transitInfo = transitInfoRepository.findOne(Integer.parseInt(transitInfoId));
		transitInfo.setSignInfo(signInfo);
		
		if(signInfo.getSignType().equals("正常")){
			transitInfo.setStatus("正常签收");
			transitInfo.getWayBill().setSignStatus(3);
			wayBillIndexRepository.save(transitInfo.getWayBill());
		}else {
			transitInfo.setStatus("异常");
			transitInfo.getWayBill().setSignStatus(4);
			wayBillIndexRepository.save(transitInfo.getWayBill());
		}
	}

}
