package cn.hjiabin.bos.service.impl.transit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.hjiabin.bos.dao.transit.IInOutStorageInfoRepository;
import cn.hjiabin.bos.dao.transit.ITransitInfoRepository;
import cn.hjiabin.bos.domain.transit.InOutStorageInfo;
import cn.hjiabin.bos.domain.transit.TransitInfo;
import cn.hjiabin.bos.service.transit.IInOutStorageInfoService;

@Service
@Transactional
public class InOutStorageInfoServiceImpl implements IInOutStorageInfoService {

	@Autowired
	private IInOutStorageInfoRepository inOutStorageInfoRepository;
	
	@Autowired
	private ITransitInfoRepository transitInfoRepository;
	
	@Override
	public void save(String transitInfoId, InOutStorageInfo inOutStorageInfo) {
		inOutStorageInfoRepository.save(inOutStorageInfo);
		
		TransitInfo transitInfo = transitInfoRepository.findOne(Integer.parseInt(transitInfoId));
		transitInfo.getInOutStorageInfos().add(inOutStorageInfo);
		if(inOutStorageInfo.getOperation().equals("到达网点")){
			transitInfo.setStatus("到达网点");
			transitInfo.setOutletAddress(inOutStorageInfo.getAddress());
		}
	}

}
