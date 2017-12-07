package cn.hjiabin.bos.action.take_delivery;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.hjiabin.bos.action.base.BaseAction;
import cn.hjiabin.bos.domain.take_delivery.WayBill;
import cn.hjiabin.bos.service.take_delivery.IWayBillService;
import cn.hjiabin.bos.utils.FileUtils;

@ParentPackage("json-default")
@Namespace("/")
@Controller
@Scope("prototype")
public class ReportAction extends BaseAction<WayBill> {

	@Autowired
	private IWayBillService wayBillServiceImpl;
	
	@Action("report_exportXlsx")
	public String exportXlsx() throws IOException{
		
		List<WayBill> wayBills = wayBillServiceImpl.findwayBills(model);
		
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
		XSSFSheet sheet = xssfWorkbook.createSheet("运单数据");
		
		XSSFRow headRow = sheet.createRow(0);
		headRow.createCell(0).setCellValue("运单号");
		headRow.createCell(1).setCellValue("寄件人");
		headRow.createCell(2).setCellValue("寄件人电话");
		headRow.createCell(3).setCellValue("寄件人地址");
		headRow.createCell(4).setCellValue("收件人");
		headRow.createCell(5).setCellValue("收件人电话");
		headRow.createCell(6).setCellValue("收件人地址");
		
		for (WayBill wayBill : wayBills) {
			XSSFRow dataRow = sheet.createRow(sheet.getLastRowNum()+1);
			dataRow.createCell(0).setCellValue(wayBill.getWayBillNum());
			dataRow.createCell(1).setCellValue(wayBill.getSendName());
			dataRow.createCell(2).setCellValue(wayBill.getSendMobile());
			dataRow.createCell(3).setCellValue(wayBill.getSendAddress());
			dataRow.createCell(4).setCellValue(wayBill.getRecName());
			dataRow.createCell(5).setCellValue(wayBill.getRecMobile());
			dataRow.createCell(6).setCellValue(wayBill.getRecAddress());
		}
		
		ServletActionContext.getResponse().setContentType("application/vnd-excel");
		String filename = "运单数据.xlsx";
		String agent = ServletActionContext.getRequest().getHeader("user-agent");
		filename = FileUtils.encodeDownloadFilename(filename, agent);
		ServletActionContext.getResponse().setHeader("Content-Disposition", "attachment;filename="+filename);
		ServletOutputStream outputStream = ServletActionContext.getResponse().getOutputStream();
		xssfWorkbook.write(outputStream);
		xssfWorkbook.close();
		
		return NONE;
	}
}
