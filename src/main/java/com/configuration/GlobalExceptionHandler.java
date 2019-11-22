/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.configuration;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 *
 * @author vital
 * Class này được tạo ra để xử lý các lỗi ngoại lệ
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(SQLException.class)
	public String handleSQLException(HttpServletRequest request, Exception ex,Model model){
		model.addAttribute("status",500);
		model.addAttribute("messenger","Lỗi phía database");
		return "/ErrorPage";
	}
	
	@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="IOException occured")
	@ExceptionHandler(IOException.class)
	public String handleIOException(Model model){
		model.addAttribute("status",404);
		model.addAttribute("messenger","Lỗi IO trên hệ thống");
                return "/ErrorPage";
	}
   @ExceptionHandler(NoHandlerFoundException.class)
    public String handleError404(HttpServletRequest request,Model model ,Exception e)   {
            model.addAttribute("status",404);
            model.addAttribute("messenger","NOT FOUND");
            return "/ErrorPage";
    }
}
