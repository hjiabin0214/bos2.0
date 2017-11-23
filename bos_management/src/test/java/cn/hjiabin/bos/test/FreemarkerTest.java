package cn.hjiabin.bos.test;

import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class FreemarkerTest {

	@Test
	public void test() throws Exception{
		Configuration configuration = new Configuration(Configuration.VERSION_2_3_22);
		configuration.setDirectoryForTemplateLoading(new File("src/main/webapp/WEB-INF/templates"));
		Template template = configuration.getTemplate("hello.ftl");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("title", "黑马程序员");
		map.put("msg", "freemarker案例");
		template.process(map, new PrintWriter(System.out));
	}
}
