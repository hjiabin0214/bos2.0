package cn.hjiabin.bos.action.take_delivery;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import cn.hjiabin.bos.action.base.BaseAction;
import cn.hjiabin.bos.domain.take_delivery.Promotion;
import cn.hjiabin.bos.service.take_delivery.IPromotionService;

@ParentPackage("json-default")
@Namespace("/")
@Controller
@Scope("prototype")
public class PromotionAction extends BaseAction<Promotion> {

	private File titleImgFile;
	private String titleImgFileFileName;
	public void setTitleImgFile(File titleImgFile) {
		this.titleImgFile = titleImgFile;
	}
	public void setTitleImgFileFileName(String titleImgFileFileName) {
		this.titleImgFileFileName = titleImgFileFileName;
	}
	
	@Autowired
	private IPromotionService promotionServiceImpl;
	
	@Action(value="promotion_save",results={@Result(name="success",type="redirect",location="./pages/take_delivery/promotion.html")})
	public String save() throws IOException{
		String savePath = ServletActionContext.getServletContext().getRealPath("/attached/");
		String saveUrl = ServletActionContext.getRequest().getContextPath()+"/attached/";
		UUID uuid = UUID.randomUUID();
		String ext = titleImgFileFileName.substring(titleImgFileFileName.lastIndexOf("."));
		String randomFileName = uuid + ext;
		FileUtil.copyFile(titleImgFile, new File(savePath + "/" + randomFileName));
		model.setTitleImg(ServletActionContext.getRequest().getContextPath()+"/attached/"+randomFileName);
		promotionServiceImpl.save(model);
		return SUCCESS;
	}
	
	@Action(value="promotion_pageQuery",results={@Result(name="success",type="json")})
	public String pageQuery(){
		Pageable pageable = new PageRequest(page - 1, rows);
		Page<Promotion> pageData = promotionServiceImpl.findPageData(pageable);
		pushPageDataToValueStack(pageData);
		return SUCCESS;
	}
}
