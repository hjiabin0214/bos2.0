package cn.hjiabin.bos.action;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;

import cn.hjiabin.bos.action.base.BaseAction;
import cn.hjiabin.bos.domain.FixedArea;
import cn.hjiabin.bos.service.IFixedAreaService;

@ParentPackage("json-default")
@Namespace("/")
@Controller
@Scope("prototype")
public class FixedAreaAction extends BaseAction<FixedArea> {

	@Autowired
	private IFixedAreaService fixedAreaServiceImpl;
	
	@Action(value="fixedArea_save",results={@Result(name="success",type="redirect",location="./pages/base/fixed_area.html")})
	public String save(){
		fixedAreaServiceImpl.save(model);
		return SUCCESS;
	}
	
	@Action(value="fixedArea_pageQuery",results={@Result(name="success",type="json")})
	public String pageQuery(){
		Pageable pageable = new PageRequest(page-1, rows);
		Specification<FixedArea> specification = new Specification<FixedArea>() {
			@Override
			public Predicate toPredicate(Root<FixedArea> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				if(StringUtils.isNotBlank(model.getId())){
					Predicate p1 = cb.equal(root.get("id").as(String.class), model.getId());
					list.add(p1);
				}
				if(StringUtils.isNotBlank(model.getCompany())){
					Predicate p2 = cb.like(root.get("company").as(String.class), "%"+model.getCompany()+"%");
					list.add(p2);
				}
				return cb.and(list.toArray(new Predicate[0]));
			}
		};
		
		Page<FixedArea> pageData = fixedAreaServiceImpl.findPageQuery(specification,pageable);
		pushPageDataToValueStack(pageData);
		return SUCCESS;
	}
	
	@Action(value="fixedArea_findNoAssociationCustomers",results={@Result(name="success",type="json")})
	public String findNoAssociationCustomers(){
		WebClient.create("http://localhost:9002/crm_management/services/customerService/noassociationcustomers").accept(MediaType.APPLICATION_JSON).getCollection(Cust)
		return SUCCESS;
	}
}
