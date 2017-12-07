package cn.hjiabin.bos.service.transit;

import cn.hjiabin.bos.domain.transit.SignInfo;

public interface ISignInfoService {

	void save(String transitInfoId, SignInfo signInfo);

}
