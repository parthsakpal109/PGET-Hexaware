package com.hexaware.web.apichallange.Exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



@ControllerAdvice
public class GlobalExceptionController {
	@ExceptionHandler(BookNotFoundException.class)
	public ResponseEntity<ErrorDetails> noBookF(BookNotFoundException ex)
	{
		ErrorDetails error= new ErrorDetails(LocalDateTime.now(),ex.getMessage(),"location not found ","No_Book_Found");
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
}
