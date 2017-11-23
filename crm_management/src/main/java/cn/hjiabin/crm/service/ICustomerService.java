package cn.hjiabin.crm.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.hibernate.annotations.Columns;

import cn.hjiabin.crm.domain.Customer;

public interface ICustomerService {

	//查询所有为关联客户
	@Path("/noassociationcustomers")
	@GET
	@Produces({"application/xml","application/json"})
	public List<Customer> findNoAssociationCustomers();
	
	//查询已关联到定区的客户
	@Path("associationfixedareacustomers/{fixedareaid}")
	@GET
	@Produces({"application/xml","application/json"})
	public List<Customer> findHasAssociationCustomers(@PathParam("fixedareaid") String fixedAreaId);
	
	//将客户关联到定区上,将所有客户id拼成1,2,3
	@Path("associationcustomerstofixedarea")
	@PUT
	public void associationCustomersToFixedArea(@QueryParam("customerIdStr") String customerIdStr, @QueryParam("fixedAreaId") String fixedAreaId);
	
	@Path("customer")
	@POST
	@Consumes({"application/xml","application/json"})
	public void regist(Customer customer);
	
	@Path("/customer/telephone/{telephone}")
	@GET
	@Consumes({"application/xml","application/json"})
	public Customer findByTelephone(@PathParam("telephone") String telephone);
	
	@Path("/customer/updateType/{telephone}")
	@GET
	public void updateType(@PathParam("telephone") String telephone);
}
