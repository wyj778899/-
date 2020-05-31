package com.xzj.car.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xzj.car.dao.BookInfoDao;
import com.xzj.car.entity.BookInfo;
import com.xzj.car.service.BookInfoService;

@Service
@Transactional
public class BookInfoServiceImpl implements BookInfoService{

	@Autowired
	private BookInfoDao bookInfoDao;
	
	@Override
	public void insert(BookInfo b) {
		// TODO Auto-generated method stub
		bookInfoDao.insert(b);
	}

	@Override
	public void update(BookInfo b) {
		// TODO Auto-generated method stub
		bookInfoDao.update(b);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		bookInfoDao.delete(id);
	}

	@Override
	@Transactional(readOnly=true,propagation=Propagation.SUPPORTS)
	public BookInfo queryById(String id) {
		// TODO Auto-generated method stub
		return bookInfoDao.queryById(id);
	}

	@Override
	@Transactional(readOnly=true,propagation=Propagation.SUPPORTS)
	public List<BookInfo> query() {
		// TODO Auto-generated method stub
		return bookInfoDao.query();
	}

	
}