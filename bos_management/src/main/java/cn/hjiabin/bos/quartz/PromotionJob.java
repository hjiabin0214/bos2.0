package cn.hjiabin.bos.quartz;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import cn.hjiabin.bos.service.take_delivery.IPromotionService;

public class PromotionJob implements Job {

	@Autowired
	private IPromotionService promotionService;
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		//System.out.println("活动过期处理程序启动...");	
		//promotionService.updateStatus(new Date());
	}

}
