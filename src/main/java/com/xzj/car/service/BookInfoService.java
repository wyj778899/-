package com.xzj.car.service;

import java.util.List;

import com.xzj.car.entity.BookInfo;


public interface BookInfoService{
	void insert(BookInfo b);
	void update(BookInfo b);
	void delete(String id);
	BookInfo queryById(String id);
	List<BookInfo> query();
}