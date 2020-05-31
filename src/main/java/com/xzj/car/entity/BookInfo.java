package com.xzj.car.entity;

import java.io.Serializable;

public class BookInfo implements Serializable{

	private String bookId;
	private String bookName;
	private String bookType;
	private String bookNum;
	private String author;
	private String bookPrice;
	private String press;
	private int state;
	private int count;
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookType() {
		return bookType;
	}
	public void setBookType(String bookType) {
		this.bookType = bookType;
	}
	public String getBookNum() {
		return bookNum;
	}
	public void setBookNum(String bookNum) {
		this.bookNum = bookNum;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getBookPrice() {
		return bookPrice;
	}
	public void setBookPrice(String bookPrice) {
		this.bookPrice = bookPrice;
	}
	public String getPress() {
		return press;
	}
	public void setPress(String press) {
		this.press = press;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "BOOK [bookId=" + bookId + ", bookName=" + bookName + ", bookType=" + bookType + ", bookNum=" + bookNum
				+ ", author=" + author + ", bookPrice=" + bookPrice + ", press=" + press + ", state=" + state
				+ ", count=" + count + "]";
	}
	public BookInfo(String bookId, String bookName, String bookType, String bookNum, String author, String bookPrice,
			String press, int state, int count) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.bookType = bookType;
		this.bookNum = bookNum;
		this.author = author;
		this.bookPrice = bookPrice;
		this.press = press;
		this.state = state;
		this.count = count;
	}
	public BookInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
