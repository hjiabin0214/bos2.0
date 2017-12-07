package cn.hjiabin.bos.service.transit;

import cn.hjiabin.bos.domain.transit.InOutStorageInfo;

public interface IInOutStorageInfoService {

	void save(String transitInfoId, InOutStorageInfo inOutStorageInfo);

}
