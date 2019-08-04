/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.Service.UserService;
import com.model.NguoiDung;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author vital
 */
//@Controller
@Controller
public class UserController {
    @Autowired
    UserService userServiceImpl;
    
    @RequestMapping(value = { "/Login"}, method = RequestMethod.GET)
    public String login(Model model,HttpServletRequest req,HttpServletResponse res){
           NguoiDung nguoiDung = (NguoiDung) req.getSession().getAttribute("nguoiDung");
            try {
                if(nguoiDung != null)
                {
                       model.addAttribute("success","Đăng nhập thành công");
                       RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/");
                       dispatcher.forward(req, res);
                       return "index";
                }
                else{
                     model.addAttribute("nguoiDung",new NguoiDung());
                     return "Login"; 
                }
            } catch (Exception ex) {
                   Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    @RequestMapping(value={"/Login"},method=RequestMethod.POST)
    public String exceCuteLogin(HttpServletRequest request,HttpServletResponse res,@ModelAttribute("nguoiDung")NguoiDung nguoiDung,Model model)
    {
        String email=request.getParameter("email");
        String password=request.getParameter("password");
        Boolean bool= userServiceImpl.login(request,email,password);
        try {
            if(bool){
                    model.addAttribute("success","Đăng nhập thành công");
                    RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/");
                    dispatcher.forward(request, res);
            }else{
                model.addAttribute("failed","Đăng nhập thất bại sai tên tài khoản hoặc mật khẩu");
                return "Login";
            }
        } catch (Exception ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return "/";
    }
     @RequestMapping(value="/datMuon", method=RequestMethod.GET, produces = "text/plain;charset=UTF-8")
     @ResponseBody
    public String datMuon(HttpServletRequest request,HttpServletResponse res,Model model){
        try {
            res.setContentType("text/html;charset=UTF-8");
            if( userServiceImpl.datMuon(request) )
            {
                return "<p>Đặt giữ thành công</p>";
            }
            
            else
            {
                  return "<p>Đặt giữ thất bại</p>";
            }
        } catch (Exception ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            return "<p> "+ex.getMessage()+"</p>";
            
        }
    }

}
