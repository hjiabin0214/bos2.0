package cn.hjiabin.bos.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
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

import cn.hjiabin.bos.domain.Courier;
import cn.hjiabin.bos.service.ICourierService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@ParentPackage("json-default")
@Namespace("/")
@Controller
@Scope("prototype")
public class CourierAction extends ActionSupport implements
		ModelDriven<Courier> {

	private Courier courier = new Courier();

	@Override
	public Courier getModel() {
		return courier;
	}

	@Autowired
	private ICourierService courierServiceImpl;

	@Action(value = "courier_save", results = { @Result(name = "success", type = "redirect", location = "./pages/base/courier.html") })
	public String save() {
		courierServiceImpl.save(courier);
		return SUCCESS;
	}

	private int page;
	private int rows;

	public void setPage(int page) {
		this.page = page;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	@Action(value = "courier_pageQuery", results = { @Result(name = "success", type = "json") })
	public String pageQuery() {
		Pageable pageable = new PageRequest(page - 1, rows);

		Specification<Courier> specification = new Specification<Courier>() {

			@Override
			public Predicate toPredicate(Root<Courier> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				if (StringUtils.isNotBlank(courier.getCourierNum())) {
					Predicate p1 = cb.equal(
							root.get("courierNum").as(String.class),
							courier.getCourierNum());
					list.add(p1);
				}
				if (StringUtils.isNotBlank(courier.getCompany())) {
					Predicate p2 = cb.like(
							root.get("company").as(String.class),
							"%" + courier.getCompany() + "%");
					list.add(p2);
				}
				if (StringUtils.isNotBlank(courier.getType())) {
					Predicate p3 = cb.equal(root.get("type").as(String.class),
							courier.getType());
					list.add(p3);
				}
				Join<Object, Object> standardRoot = root.join("standard",
						JoinType.INNER);
				if (courier.getStandard() != null
						&& StringUtils.isNotBlank(courier.getStandard()
								.getName())) {
					Predicate p4 = cb.like(
							standardRoot.get("name").as(String.class), "%"
									+ courier.getStandard() + "%");
					list.add(p4);
				}
				return cb.and(list.toArray(new Predicate[0]));
			}
		};
		Page<Courier> pageData = courierServiceImpl.findPageData(specification,pageable);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", pageData.getTotalElements());
		map.put("rows", pageData.getContent());
		ActionContext.getContext().getValueStack().push(map);
		return SUCCESS;
	}
	
	private String ids;
	
	public void setIds(String ids) {
		this.ids = ids;
	}

	@Action(value = "courier_delBatch", results = { @Result(name = "success", type = "redirect", location = "./pages/base/courier.html") })
	public String delBatch() {
		String[] idArray = ids.split(",");
		courierServiceImpl.delBatch(idArray);
		return SUCCESS;
	}
}
