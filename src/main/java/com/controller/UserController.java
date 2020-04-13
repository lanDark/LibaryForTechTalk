/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.Class.CacheMap;
import static com.Class.ForwardHandling.*;
import com.Service.DanhMucService;
import com.Service.UserService;
import com.Validator.*;
import com.securityImpl.CustomUser;
import java.util.logging.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    

    
    public void getCacheForLoginPage(Model map){
        map.addAttribute("category", danhMucServiceImpl.getDanhMucAll(CacheMap.getDanhMucAll));
        if(!map.containsAttribute("registerForm"))
            map.addAttribute("registerForm",new RegisterForm());
    }   
    
    @RequestMapping(value = { "/Login"}, method = RequestMethod.GET,produces = "text/plain;charset=UTF-8")
    public String login(Model model,HttpServletRequest req,HttpServletResponse res){
           
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(principal instanceof CustomUser) 
        {
            LOG.info("request to : /Login - "+((CustomUser) principal).getUsername());
            forwardSRC(req, res, "/");
        }else{
            this.getCacheForLoginPage(model);
            return "Login"; 
        }
            return "/";
    }
    
    @RequestMapping(value={"/Login"},method=RequestMethod.POST,produces = "text/plain;charset=UTF-8")
    public void exceCuteLogin(HttpServletRequest request,HttpServletResponse res,Model model)
    {
        this.login(model, request, res);
    }
    
    

    

}
