package cn.hjiabin.bos.service.take_delivery;

import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cn.hjiabin.bos.domain.page.PageBean;
import cn.hjiabin.bos.domain.take_delivery.Promotion;

public interface IPromotionService {

	void save(Promotion promotion);

	Page<Promotion> findPageData(Pageable pageable);
	
	@Path("pageQuery")
	@GET
	@Produces({"application/xml","application/json"})
	PageBean<Promotion> findPageData(@QueryParam("page") int page, @QueryParam("rows") int rows);
	
	@Path("/promotion/{id}")
	@GET
	@Produces({"application/xml","application/json"})
	Promotion findById(@PathParam("id") Integer id);

	void updateStatus(Date date);
}
