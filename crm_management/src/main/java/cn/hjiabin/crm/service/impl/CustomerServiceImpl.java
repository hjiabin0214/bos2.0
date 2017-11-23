package cn.hjiabin.crm.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.hjiabin.crm.dao.ICustomerRepository;
import cn.hjiabin.crm.domain.Customer;
import cn.hjiabin.crm.service.ICustomerService;

@Service
@Transactional
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	private ICustomerRepository customerRepository;
	
	@Override
	public List<Customer> findNoAssociationCustomers() {
		return customerRepository.findByFixedAreaIdIsNull();
	}

	@Override
	public List<Customer> findHasAssociationCustomers(String fixedAreaId) {
		return customerRepository.findByFixedAreaId(fixedAreaId);
	}

	@Override
	public void associationCustomersToFixedArea(String customerIdStr, String fixedAreaId) {
		customerRepository.clearFixedAreaId(fixedAreaId);
		if("null".equals(customerIdStr) || StringUtils.isBlank(customerIdStr)){
			return;
		}
		String[] ids = customerIdStr.split(",");
		for (String idStr : ids) {
			Integer id = Integer.parseInt(idStr);
			customerRepository.updateFixedAreaId(fixedAreaId, id);
		}
	}

	@Override
	public void regist(Customer customer) {
		customerRepository.save(customer);
	}

	@Override
	public Customer findByTelephone(String telephone) {
		return customerRepository.findByTelephone(telephone);
	}

	@Override
	public void updateType(String telephone) {
		customerRepository.updateType(telephone);
	}

}
