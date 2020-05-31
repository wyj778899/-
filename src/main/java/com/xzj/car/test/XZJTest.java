package com.xzj.car.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xzj.car.dao.BookInfoDao;
import com.xzj.car.entity.BookInfo;

@RunWith(SpringJUnit4ClassRunner.class)
//RunWith使用spring测试环境测试    SpringJUnit4ClassRunner为spring的测试类

@SpringBootTest
//一个类变成测试类需要加@RunWith注解和@SpringBootTest注解
//表示使用带有Spring Boot支持的引导程序，该注解能在运行时配置Spring Boot的环境
public class XZJTest{
	
	@Autowired
	private BookInfoDao bookInfoDao;
	
	//BOOK_ID,BOOK_NAME,BOOK_TYPE,BOOK_NUM,AUTHOR,BOOK_PRICE,PRESS,STATE,COUNT
	@Test
	public void insert() {
		 long now = System.currentTimeMillis(); 
		BookInfo b = new BookInfo(now+"","图书1","001","都市小说","张三","78.00","北京",1,1);
		bookInfoDao.insert(b);
	}
	@Test
	public void update() {
		BookInfo b = new BookInfo("11","图书2","001","都市小说","李四","78.00","上海",1,1);
		bookInfoDao.update(b);
	}
	
	@Test
	public void delete() {
		bookInfoDao.delete("123456");
	}
	
	
	@Test
	public void queryById() {
	   BookInfo b = bookInfoDao.queryById("1590739059702");
	   System.out.println(b);
	}
	
	
	@Test
	public void query() {
		List<BookInfo> list = bookInfoDao.query();
		for(BookInfo b:list) {
			System.out.println(b);
		}
	}
}