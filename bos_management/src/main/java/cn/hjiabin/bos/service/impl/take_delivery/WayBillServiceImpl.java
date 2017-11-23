package cn.hjiabin.bos.service.impl.take_delivery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.hjiabin.bos.dao.take_delivery.IWayBillRepository;
import cn.hjiabin.bos.domain.take_delivery.WayBill;
import cn.hjiabin.bos.service.take_delivery.IWayBillService;

@Service
@Transactional
public class WayBillServiceImpl implements IWayBillService {

	@Autowired
	private IWayBillRepository wayBillRepository;
	
	@Override
	public void save(WayBill wayBill) {

		wayBillRepository.save(wayBill);
	}

	@Override
	public Page<WayBill> findPageData(Pageable pageable) {
		return wayBillRepository.findAll(pageable);
	}

	@Override
	public WayBill findByWayBillNum(String wayBillNum) {
		return wayBillRepository.findByWayBillNum(wayBillNum);
	}

}
