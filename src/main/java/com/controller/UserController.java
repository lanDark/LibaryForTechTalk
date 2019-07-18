/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.Service.UserService;
import com.model.NguoiDung;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
            if(nguoiDung != null)
            {
                return "index";
            }
            else{
                 model.addAttribute("nguoiDung",new NguoiDung());
                 return "Login"; 
            }

    }
    
    @RequestMapping(value={"/Login"},method=RequestMethod.POST)
    public String exceCuteLogin(HttpServletRequest request,@ModelAttribute("nguoiDung")NguoiDung nguoiDung,Model model)
    {
        String email=request.getParameter("email");
        String password=request.getParameter("password");
        Boolean bool= userServiceImpl.login(request,email,password);
        if(bool){
            model.addAttribute("success","Đăng nhập thành công");
            return "index";
        }else{
            model.addAttribute("failed","Đăng nhập thất bại");
            return "Login";
        }
    }
 
}
