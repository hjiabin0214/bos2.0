package cn.hjiabin.bos_fore.action;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Controller;

import cn.hjiabin.bos.constans.Constants;
import cn.hjiabin.bos_fore.utils.MailUtils;
import cn.hjiabin.crm.domain.Customer;

@ParentPackage("json-default")
@Namespace("/")
@Controller
@Scope("prototype")
public class CustomerAction extends BaseAction<Customer> {

	@Autowired
	@Qualifier("jmsQueueTemplate")
	private JmsTemplate jmsTemplate;
	
	@Action(value="customer_sendSms")
	public void sendSms() throws Exception{
		String randomCode = RandomStringUtils.randomNumeric(6);
		ServletActionContext.getRequest().getSession().setAttribute(model.getTelephone(), randomCode);
		System.out.println("生成的手机验证码是:" + randomCode);
		
		final String msg = "尊敬的客户您好,本次获取的验证码为:" + randomCode + "服务电话:13750861246";
		//AliMessageUtils.sendSmsByHTTP(model.getTelephone(), msg);
		jmsTemplate.send("bos_sms", new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				MapMessage mapMessage = session.createMapMessage();
				mapMessage.setString("telephone", model.getTelephone());
				mapMessage.setString("msg", msg);
				return mapMessage;
			}
		});
	}
	
	private String checkCode;

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	@Action(value="customer_regist",results={@Result(name="success",type="redirect",location="signup-success.html"),
			@Result(name="input",type="redirect",location="signup.html")})
	public String regist(){
		String checkCodeSession = (String) ServletActionContext.getRequest().getSession().getAttribute(model.getTelephone());
		if(checkCodeSession == null || !checkCodeSession.equals(checkCode)){
			System.out.println("短信验证码错误!");
			return INPUT;
		}
		WebClient.create("http://localhost:9002/crm_management/services/customerService/customer").type(MediaType.APPLICATION_JSON).post(model);
		System.out.println("客户注册成功!");
		
		String activeCode = RandomStringUtils.randomNumeric(32);
		
		redisTemplate.opsForValue().set(model.getTelephone(), activeCode, 24, TimeUnit.HOURS);
		
		String content = "尊敬的客户,请您于24小时内.进行邮箱的绑定,点击下面的地址完成绑定:<br/><a href='"+MailUtils.activeUrl+"?telephone="+model.getTelephone()+"&activeCode="+activeCode+"'>倪胖子包子铺</a>";
		
		MailUtils.sendMail("倪胖子包子铺激活邮件", content, model.getEmail());
		
		return SUCCESS;
	}
	
	private String activeCode;

	public void setActiveCode(String activeCode) {
		this.activeCode = activeCode;
	}
	
	@Action(value="customer_activeMail")
	public String activeMail() throws IOException{
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		String activeCodeRedis = redisTemplate.opsForValue().get(model.getTelephone());
		if(activeCodeRedis == null || !activeCodeRedis.equals(activeCode)){
			ServletActionContext.getResponse().getWriter().println("激活码无效,请重新登录系统绑定邮箱!");
		}else {
			Customer customer = WebClient.create("http://localhost:9002/crm_management/services/customerService/customer/telephone/"+model.getTelephone()).accept(MediaType.APPLICATION_JSON).get(Customer.class);
			if(customer.getType() == null || customer.getType() != 1){
				WebClient.create("http://localhost:9002/crm_management/services/customerService/customer/updateType/"+model.getTelephone()).get();
				ServletActionContext.getResponse().getWriter().println("邮箱绑定成功!");
			}else {
				ServletActionContext.getResponse().getWriter().println("邮箱已经绑定,无需重新绑定!");
			}
			redisTemplate.delete(model.getTelephone());
		}
		return NONE;
	}
	
	@Action(value="customer_login",results={@Result(name="login",type="redirect",location="login.html"), @Result(name="success",type="redirect",location="index.html#/myhome")})
	public String login(){
		
		Customer customer = WebClient.create(Constants.CRM_MANAGEMENT_URL+"/services/customerService/customer/login?telephone="+model.getTelephone()+"&password="+model.getPassword()).accept(MediaType.APPLICATION_JSON).get(Customer.class);
		if(customer == null){
			return LOGIN;
		}else {
			ServletActionContext.getRequest().getSession().setAttribute("customer", customer);
			return SUCCESS;
		}
	}
}
