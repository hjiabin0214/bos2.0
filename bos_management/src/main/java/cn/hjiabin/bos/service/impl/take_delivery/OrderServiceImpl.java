package cn.hjiabin.bos.service.impl.take_delivery;

import java.util.Date;
import java.util.Iterator;
import java.util.UUID;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.cxf.jaxrs.client.WebClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.hjiabin.bos.constans.Constants;
import cn.hjiabin.bos.dao.IAreaRepository;
import cn.hjiabin.bos.dao.IFixedAreaRepository;
import cn.hjiabin.bos.dao.take_delivery.IOrderRepository;
import cn.hjiabin.bos.dao.take_delivery.IWorkBillRepository;
import cn.hjiabin.bos.domain.base.Area;
import cn.hjiabin.bos.domain.base.Courier;
import cn.hjiabin.bos.domain.base.FixedArea;
import cn.hjiabin.bos.domain.base.SubArea;
import cn.hjiabin.bos.domain.take_delivery.Order;
import cn.hjiabin.bos.domain.take_delivery.WorkBill;
import cn.hjiabin.bos.service.take_delivery.IOrderService;

@Service
@Transactional
public class OrderServiceImpl implements IOrderService {

	@Autowired
	private IOrderRepository orderRepository;
	
	@Autowired
	private IFixedAreaRepository fixedAreaRepository;
	
	@Autowired
	private IAreaRepository areaRepository;
	
	@Autowired
	private IWorkBillRepository workBillRepository;
	
	@Autowired
	@Qualifier("jmsQueueTemplate")
	private JmsTemplate jmsTemplate;
	
	@Override
	public Order findByOrderNum(String orderNum) {
		return orderRepository.findByOrderNum(orderNum);
	}

	@Override
	public void saveOrder(Order order) {
		
		order.setOrderNum(UUID.randomUUID().toString());
		order.setOrderTime(new Date());
		order.setStatus("1");
		
		Area area = order.getSendArea();
		Area persistArea = areaRepository.findByProvinceAndCityAndDistrict(area.getProvince(), area.getCity(), area.getDistrict());
		
		Area recArea = order.getSendArea();
		Area persistRecArea = areaRepository.findByProvinceAndCityAndDistrict(recArea.getProvince(), recArea.getCity(), recArea.getDistrict());
		order.setSendArea(persistArea);
		order.setRecArea(persistRecArea);
		
		String fixedAreaId = WebClient.create(Constants.CRM_MANAGEMENT_URL+"/services/customerService/customer/findFixedAreaIdByAddress?address="+order.getSendAddress()).accept(MediaType.APPLICATION_JSON).get(String.class);
		if(fixedAreaId != null){
			FixedArea fixedArea = fixedAreaRepository.findOne(fixedAreaId);
			Iterator<Courier> iterator = fixedArea.getCouriers().iterator();
			if(iterator.hasNext()){
				Courier courier = iterator.next();
				if(courier != null){
					System.out.println("自动分单成功...");
					saveOrder(order, courier);
					geberateWorkBill(order);
					return;
				}
			}
		}
		
		for (SubArea subArea : persistArea.getSubareas()) {
			if(order.getSendAddress().contains(subArea.getKeyWords())){
				Iterator<Courier> iterator = subArea.getFixedArea().getCouriers().iterator();
				if(iterator.hasNext()){
					Courier courier = iterator.next();
					if(courier != null){
						System.out.println("自动分单成功...");
						saveOrder(order, courier);
						geberateWorkBill(order);
						return;
					}
				}
			}
		}
		
		order.setOrderType("2");
		orderRepository.save(order);
	}

	private void geberateWorkBill(final Order order) {
		WorkBill workBill = new WorkBill();
		workBill.setType("新");
		workBill.setPickstate("新单");
		workBill.setBuildtime(new Date());
		workBill.setRemark(order.getRemark());
		final String smsNumber = RandomStringUtils.randomNumeric(4);
		workBill.setSmsNumber(smsNumber);
		workBill.setOrder(order);
		workBill.setCourier(order.getCourier());
		workBillRepository.save(workBill);
		
		jmsTemplate.send("bos_sms", new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				MapMessage mapMessage = session.createMapMessage();
				mapMessage.setString("telephone", order.getCourier().getTelephone());
				mapMessage.setString("msg", "短信序号:"+smsNumber+",取件地址:"+order.getSendAddress()+",联系人:"+order.getSendName()+",手机:"+order.getSendMobile()+",快递员捎话:"+order.getSendMobileMsg());
				return mapMessage;
			}
		});
		workBill.setPickstate("已通知");
	}

	private void saveOrder(Order order, Courier courier) {
		order.setCourier(courier);
		order.setOrderType("1");
		orderRepository.save(order);
	}

}
