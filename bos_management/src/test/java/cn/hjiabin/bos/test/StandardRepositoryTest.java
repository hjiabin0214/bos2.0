package cn.hjiabin.bos.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import cn.hjiabin.bos.dao.IStandardRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
@Transactional
public class StandardRepositoryTest {

	@Autowired
	private IStandardRepository standardRepository;
	
	@Test
	public void test1(){
		System.out.println(standardRepository.findByName("10-20公斤"));
	}
	
	@Test
	public void test2(){
		System.out.println(standardRepository.queryName("30-40公斤"));
	}
	
	@Test
	@Rollback(false)
	public void test3(){
		standardRepository.updataMinLength(1, 15);
	}
}
