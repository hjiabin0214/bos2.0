package cn.hjiabin.bos.index;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import cn.hjiabin.bos.domain.take_delivery.WayBill;

public interface IWayBillIndexRepository extends ElasticsearchRepository<WayBill, Integer> {

}
