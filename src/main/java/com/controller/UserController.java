/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import Class.CacheMap;
import com.Service.DanhMucService;
import com.Service.UserService;
import com.model.NguoiDung;
import com.securityImpl.CustomUser;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    @Autowired
    DanhMucService danhMucServiceImpl;

    private static final Logger LOG = Logger.getLogger(UserController.class.getName());
    
    @RequestMapping(value = { "/Login"}, method = RequestMethod.GET)
    public String login(Model model,HttpServletRequest req,HttpServletResponse res){
           
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            try {
                if(principal instanceof CustomUser) //Xác thực người dùng đã đăng nhập chưa
                {
                    LOG.info("request to : /Login - "+((CustomUser) principal).getUsername());
                    RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/");
                    dispatcher.forward(req, res);
                }
                else{ //xảy ra khi người dùng chưa xác thực
                     model.addAttribute("category", danhMucServiceImpl.getDanhMucAll(CacheMap.getDanhMucAll));
                     return "Login"; 
                }
            } catch (Exception ex) {
                   Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            }
        return "/";
    }
    
    @RequestMapping(value={"/Login"},method=RequestMethod.POST)
    public void exceCuteLogin(HttpServletRequest request,HttpServletResponse res,Model model)
    {
        login(model, request, res);
    }
    
    
    @RequestMapping(value="/datMuon", method=RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String datMuon(HttpServletRequest request,HttpServletResponse res,Model model){
         Authentication auth = SecurityContextHolder.getContext().getAuthentication();
         Object printical= auth.getPrincipal();
         LOG.info("request to : /datMuon - "+ request.getUserPrincipal());
        try {
            res.setContentType("text/html;charset=UTF-8");
            if(printical instanceof UserDetails)
            {
                if( userServiceImpl.datMuon(request) )
                {
                    return "<p>Đặt giữ thành công</p>";
                }

                else
                {
                      return "<p>Đặt giữ thất bại</p>";
                }
            }
            else
            {
                request.getServletContext().getRequestDispatcher("/Login").forward(request, res);
            }
        } catch (Exception ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            return "<p> "+ex.getMessage()+"</p>";
            
        }
        return "";
    }
    
    @RequestMapping(value="/LogOut",method = RequestMethod.GET)
    public void LogOut(HttpServletRequest req,HttpServletResponse res) {
        try {
            req.getSession().removeAttribute("nguoiDung");
            RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/");
            dispatcher.forward(req, res);
        } catch (ServletException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @RequestMapping(value="/Register",method=RequestMethod.POST)
    public void Register(@Valid NguoiDung nguoiDung,BindingResult binDing){
        
    }
}
