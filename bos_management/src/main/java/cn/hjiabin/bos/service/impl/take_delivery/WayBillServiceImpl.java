package cn.hjiabin.bos.service.impl.take_delivery;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.index.query.QueryStringQueryBuilder.Operator;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.index.query.WildcardQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.hjiabin.bos.dao.take_delivery.IWayBillRepository;
import cn.hjiabin.bos.domain.take_delivery.WayBill;
import cn.hjiabin.bos.index.IWayBillIndexRepository;
import cn.hjiabin.bos.service.take_delivery.IWayBillService;

@Service
@Transactional
public class WayBillServiceImpl implements IWayBillService {

	@Autowired
	private IWayBillRepository wayBillRepository;
	
	@Autowired
	private IWayBillIndexRepository wayBillIndexRepository;
	
	@Override
	public void save(WayBill wayBill) {

		wayBillRepository.save(wayBill);
		wayBillIndexRepository.save(wayBill);
	}

	@Override
	public Page<WayBill> findPageData(WayBill wayBill, Pageable pageable) {
		if(StringUtils.isBlank(wayBill.getWayBillNum())
				&& StringUtils.isBlank(wayBill.getSendAddress())
				&& StringUtils.isBlank(wayBill.getRecAddress())
				&& StringUtils.isBlank(wayBill.getSendProNum())
				&& (wayBill.getSignStatus() == null || wayBill.getSignStatus() == 0)){
			
			return wayBillRepository.findAll(pageable);
		}else {
			BoolQueryBuilder queryBuilder = new BoolQueryBuilder();
			
			if(StringUtils.isNoneBlank(wayBill.getWayBillNum())){
				TermQueryBuilder termQueryBuilder = new TermQueryBuilder("wayBillNum", wayBill.getWayBillNum());
				queryBuilder.must(termQueryBuilder);
			}
			if(StringUtils.isNoneBlank(wayBill.getSendAddress())){
				WildcardQueryBuilder wildcardQueryBuilder = new WildcardQueryBuilder("sendAddress", "*" + wayBill.getSendAddress() + "*");
				
				QueryStringQueryBuilder queryStringQueryBuilder = new QueryStringQueryBuilder(wayBill.getSendAddress()).field("sendAddress").defaultOperator(Operator.AND);
				
				BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
				boolQueryBuilder.should(wildcardQueryBuilder);
				boolQueryBuilder.should(queryStringQueryBuilder);
				
				queryBuilder.must(boolQueryBuilder);
				
			}
			if(StringUtils.isNoneBlank(wayBill.getRecAddress())){
				WildcardQueryBuilder wildcardQueryBuilder = new WildcardQueryBuilder("recAddress", "*" + wayBill.getRecAddress() + "*");
				
				QueryStringQueryBuilder queryStringQueryBuilder = new QueryStringQueryBuilder(wayBill.getRecAddress()).field("recAddress").defaultOperator(Operator.AND);
				
				BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
				boolQueryBuilder.should(wildcardQueryBuilder);
				boolQueryBuilder.should(queryStringQueryBuilder);
				
				queryBuilder.must(boolQueryBuilder);
			}
			if(StringUtils.isNoneBlank(wayBill.getSendProNum())){
				TermQueryBuilder termQueryBuilder = new TermQueryBuilder("sendProNum", wayBill.getSendProNum());
				queryBuilder.must(termQueryBuilder);
			}
			if(wayBill.getSignStatus() != null && wayBill.getSignStatus() != 0){
				TermQueryBuilder termQueryBuilder = new TermQueryBuilder("signStatus", wayBill.getSignStatus());
				queryBuilder.must(termQueryBuilder);
			}
			SearchQuery searchQuery = new NativeSearchQuery(queryBuilder);
			searchQuery.setPageable(pageable);
			return wayBillIndexRepository.search(searchQuery);
		}
	}

	@Override
	public WayBill findByWayBillNum(String wayBillNum) {
		return wayBillRepository.findByWayBillNum(wayBillNum);
	}

	@Override
	public List<WayBill> findwayBills(WayBill wayBill) {

		if(StringUtils.isBlank(wayBill.getWayBillNum())
				&& StringUtils.isBlank(wayBill.getSendAddress())
				&& StringUtils.isBlank(wayBill.getRecAddress())
				&& StringUtils.isBlank(wayBill.getSendProNum())
				&& (wayBill.getSignStatus() == null || wayBill.getSignStatus() == 0)){
			
			return wayBillRepository.findAll();
		}else {
			BoolQueryBuilder queryBuilder = new BoolQueryBuilder();
			
			if(StringUtils.isNoneBlank(wayBill.getWayBillNum())){
				TermQueryBuilder termQueryBuilder = new TermQueryBuilder("wayBillNum", wayBill.getWayBillNum());
				queryBuilder.must(termQueryBuilder);
			}
			if(StringUtils.isNoneBlank(wayBill.getSendAddress())){
				WildcardQueryBuilder wildcardQueryBuilder = new WildcardQueryBuilder("sendAddress", "*" + wayBill.getSendAddress() + "*");
				
				QueryStringQueryBuilder queryStringQueryBuilder = new QueryStringQueryBuilder(wayBill.getSendAddress()).field("sendAddress").defaultOperator(Operator.AND);
				
				BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
				boolQueryBuilder.should(wildcardQueryBuilder);
				boolQueryBuilder.should(queryStringQueryBuilder);
				
				queryBuilder.must(boolQueryBuilder);
				
			}
			if(StringUtils.isNoneBlank(wayBill.getRecAddress())){
				WildcardQueryBuilder wildcardQueryBuilder = new WildcardQueryBuilder("recAddress", "*" + wayBill.getRecAddress() + "*");
				
				QueryStringQueryBuilder queryStringQueryBuilder = new QueryStringQueryBuilder(wayBill.getRecAddress()).field("recAddress").defaultOperator(Operator.AND);
				
				BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
				boolQueryBuilder.should(wildcardQueryBuilder);
				boolQueryBuilder.should(queryStringQueryBuilder);
				
				queryBuilder.must(boolQueryBuilder);
			}
			if(StringUtils.isNoneBlank(wayBill.getSendProNum())){
				TermQueryBuilder termQueryBuilder = new TermQueryBuilder("sendProNum", wayBill.getSendProNum());
				queryBuilder.must(termQueryBuilder);
			}
			if(wayBill.getSignStatus() != null && wayBill.getSignStatus() != 0){
				TermQueryBuilder termQueryBuilder = new TermQueryBuilder("signStatus", wayBill.getSignStatus());
				queryBuilder.must(termQueryBuilder);
			}
			SearchQuery searchQuery = new NativeSearchQuery(queryBuilder);
			Pageable pageable = new PageRequest(0, Integer.MAX_VALUE);
			return wayBillIndexRepository.search(searchQuery).getContent();
		}
	
	}

}
