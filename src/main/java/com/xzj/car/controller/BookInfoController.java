package com.xzj.car.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xzj.car.entity.BookInfo;
import com.xzj.car.service.BookInfoService;

@Controller
@RequestMapping("/book")
public class BookInfoController {

	@Autowired
	private BookInfoService bookInfoService;
	//显示部门所有员工
	@RequestMapping("/query")
	public String query(Model model) {
		List<BookInfo> list = bookInfoService.query();
		model.addAttribute("list", list);
		return "forward:/booklist.jsp";
	}
	@RequestMapping("/save")
	public String save(BookInfo b,HttpServletRequest request){
		b.setBookId(System.currentTimeMillis()+"");
		b.setBookName(request.getParameter("bookName"));
		b.setBookNum(request.getParameter("bookNum"));
		b.setBookType(request.getParameter("bookType"));
		b.setAuthor(request.getParameter("author"));
		b.setBookPrice(request.getParameter("bookPrice"));
		b.setPress(request.getParameter("press"));
		b.setState(Integer.parseInt(request.getParameter("state")));
		b.setCount(Integer.parseInt(request.getParameter("count")));
		bookInfoService.insert(b);
		return "redirect:/book/query";
	}
	@RequestMapping("/remove")
	public String remove(String id,String dep_name) {
		bookInfoService.delete(id);
		return "redirect:/book/query";
	}
	
	//单个查询
	@RequestMapping("/queryById")
	public String queryById(String id,Model model) {
		BookInfo e = bookInfoService.queryById(id);
		model.addAttribute("e", e);
		return "forward:/updateEmp.jsp";
	}
	
	//更新
	@RequestMapping("/update")
	public String update(BookInfo b) {
		bookInfoService.update(b);
		return "redirect:/book/query";
	}
	
}
