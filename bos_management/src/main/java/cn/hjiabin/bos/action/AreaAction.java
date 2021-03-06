package cn.hjiabin.bos.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
import cn.hjiabin.bos.domain.base.Area;
import cn.hjiabin.bos.service.IAreaService;
import cn.hjiabin.bos.utils.PinYin4jUtils;

import com.opensymphony.xwork2.ActionContext;

@ParentPackage("json-default")
@Namespace("/")
@Controller
@Scope("prototype")
public class AreaAction extends BaseAction<Area> {
	
	private File file;
	
	public void setFile(File file) {
		this.file = file;
	}
	
	@Autowired
	private IAreaService areaServiceImpl;

	@Action(value="area_batchImport")
	public String batchImport() throws IOException{
		XSSFWorkbook xssfworkbook = new XSSFWorkbook(new FileInputStream(file));
		XSSFSheet sheet = xssfworkbook.getSheetAt(0);
		List<Area> areas = new ArrayList<Area>();
		XSSFRow rowData;
		if(xssfworkbook != null){
			for (int i = sheet.getTopRow(); i < sheet.getLastRowNum(); i++) {
				rowData = sheet.getRow(i);
				if(i == sheet.getTopRow()){
					continue;
				}
				if(rowData != null){
					Area area = new Area();
					area.setId(rowData.getCell(0).getStringCellValue().toString());
					area.setProvince(rowData.getCell(1).getStringCellValue().toString());
					area.setCity(rowData.getCell(2).getStringCellValue().toString());
					area.setDistrict(rowData.getCell(3).getStringCellValue().toString());
					area.setPostcode(rowData.getCell(4).getStringCellValue().toString());
					
					String province = area.getProvince();
					String city = area.getCity();
					String district = area.getDistrict();
					province = province.substring(0, province.length()-1);
					city = city.substring(0, city.length()-1);
					district = district.substring(0, district.length()-1);
					String [] headArray = PinYin4jUtils.getHeadByString(province+city+district);
					
					StringBuffer sb = new StringBuffer();
					for (String head : headArray) {
						sb.append(head);
					}
					String shortcode = sb.toString();
					area.setShortcode(shortcode);
					
					String citycode = PinYin4jUtils.hanziToPinyin(city, "");
					area.setCitycode(citycode);
					
					areas.add(area);
				}
			}
		}
		areaServiceImpl.saveBatch(areas);
		xssfworkbook.close();
		return NONE;
	}

	@Action(value="area_pageQuery",results={@Result(name="success",type="json")})
	public String pageQuery(){
		Pageable pageable = new PageRequest(page-1, rows);
		Specification<Area> specification = new Specification<Area>() {
			@Override
			public Predicate toPredicate(Root<Area> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				if(StringUtils.isNotBlank(model.getProvince())){
					Predicate p1 = cb.like(root.get("province").as(String.class), "%"+model.getProvince()+"%");
					list.add(p1);
				}
				if(StringUtils.isNotBlank(model.getCity())){
					Predicate p2 = cb.like(root.get("city").as(String.class), "%"+model.getCity()+"%");
					list.add(p2);
				}
				if(StringUtils.isNotBlank(model.getDistrict())){
					Predicate p3 = cb.like(root.get("district").as(String.class), "%"+model.getDistrict()+"%");
					list.add(p3);
				}
				return cb.and(list.toArray(new Predicate[0]));
			}
		};
		
		Page<Area> pageData = areaServiceImpl.findPageData(specification,pageable);
		pushPageDataToValueStack(pageData);
		return SUCCESS;
	}
}
