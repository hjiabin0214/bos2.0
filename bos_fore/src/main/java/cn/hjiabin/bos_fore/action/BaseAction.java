package cn.hjiabin.bos_fore.action;


import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public abstract class BaseAction<T> extends ActionSupport implements ModelDriven<T> {
	
	protected T model;
	
	public T getModel(){
		return model;
	}
	
	public BaseAction(){
		Type genericSuperclass = this.getClass().getGenericSuperclass();
		ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
		Class<T> modelClass = (Class<T>) parameterizedType.getActualTypeArguments()[0];
		
		try {
			model = modelClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			System.out.println("模型构造失败...");
			e.printStackTrace();
		}
	}
	
	protected int page;
	protected int rows;

	public void setPage(int page) {
		this.page = page;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}
	
	protected void pushPageDataToValueStack(Page<T> pageData){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", pageData.getTotalElements());
		map.put("rows", pageData.getContent());
		ActionContext.getContext().getValueStack().push(map);
	}
}
