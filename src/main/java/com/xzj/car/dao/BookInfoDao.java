package com.xzj.car.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xzj.car.entity.BookInfo;


@Repository
public interface BookInfoDao {

	void insert(@Param("b")BookInfo b);
	void update(@Param("b")BookInfo b);
	void delete(@Param("id")String id);
	BookInfo queryById(@Param("id")String id);
	List<BookInfo> query();
}
