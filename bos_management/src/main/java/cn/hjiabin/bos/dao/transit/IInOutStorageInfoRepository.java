package cn.hjiabin.bos.dao.transit;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.hjiabin.bos.domain.transit.InOutStorageInfo;

public interface IInOutStorageInfoRepository extends JpaRepository<InOutStorageInfo, Integer> {

}
