/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.configuration;

import static com.Class.ForwardHandling.*;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 *
 * @author vital Class này được tạo ra để xử lý các lỗi ngoại lệ
 */
@ControllerAdvice()
public class GlobalExceptionHandler {

    @ExceptionHandler(SQLException.class)
    public String handleSQLException(HttpServletRequest req, HttpServletResponse res, Exception ex, Model model) {
        model.addAttribute("status", 500);
        model.addAttribute("messenger", "Lỗi phía database");
        
        isJsonRequest(req, res, 500, ex.getMessage());
        return "/ErrorPage";
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "IOException occured")
    @ExceptionHandler(IOException.class)
    public String handleIOException(HttpServletRequest req, HttpServletResponse res, Model model) {
        model.addAttribute("status", 500);
        model.addAttribute("messenger", "Lỗi IO trên hệ thống");

        isJsonRequest(req, res, 500, "IOException : URL= " + req.getPathInfo());

        return "/ErrorPage";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(HttpServletRequest request, HttpServletResponse res, Model model, RuntimeException e) {
        System.out.println(request.getRequestURI());
        model.addAttribute("status", 500);
        model.addAttribute("messenger", "Có lỗi xảy ra vui lòng thử lại sau");

        isJsonRequest(request, res, 500, "Có lỗi xảy ra vui lòng thử lại sau");

        return "/ErrorPage";
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public String handleError404(HttpServletRequest request, HttpServletResponse res, Model model, NoHandlerFoundException e) {
        model.addAttribute("status", 404);
        model.addAttribute("messeenger", "NOT FOUND");

        isJsonRequest(request, res, 404, "URL not found:  URL=" + request.getPathInfo());
        System.out.println("Có vào thằng này nhé");
        return "/ErrorPage";
    }

    private void isJsonRequest(HttpServletRequest req, HttpServletResponse res, int status, String messenger) {
        if (req.getHeader("Content-Type") != null) {
            if (req.getHeader("Content-Type").contains("json")) {
                req.setAttribute("messeenger", messenger);
                req.setAttribute("status", status);
                forwardSRC(req, res, "/parseExceptionToJson_29051982");
            }
        }
    }

}
