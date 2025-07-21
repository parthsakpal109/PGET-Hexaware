package com.hexaware.web.apichallange.Exception;

public class BookNotFoundException extends Exception{
	String msg;
	public BookNotFoundException(String msg) {
		this.msg=msg;
	}
	
	public String getMessage() {
		return msg;
	}
}
