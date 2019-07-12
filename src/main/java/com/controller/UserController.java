/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

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
public class UserController {
//    @Autowired
//    UserService userService;
//    
//    @RequestMapping(value = { "/formUser"}, method = RequestMethod.GET)
//    public String addUser(Model model,HttpServletRequest req,HttpServletResponse res){
//        Users temp=(Users) req.getSession().getAttribute("userSession");
//       if(temp!=null){
//           model.addAttribute("user", temp);
//           return "view-employee";
//       }
//       else{   
//            model.addAttribute("user", new Users());
//            return "add-employee";
//       }
//    }
//    
//    @RequestMapping(value = {"/addUser"}, method=RequestMethod.POST)
//    public String addUser(@ModelAttribute("user")Users user, Model model,HttpServletRequest req,HttpServletResponse res){
//        Users temp=userService.login(user);
//        System.out.println(temp);
//        if(temp!=null){
//            req.getSession().setAttribute("userSession",temp);
//            model.addAttribute("user",temp);
//            return "view-employee";
//        }
//        else{
//            return "add-employee";
//        }
//    }
    
//    @RequestMapping (value={"/logout"}, method= GET)
//    public String logOut(Model model,HttpSession session){
//        if(session.getAttribute("user") !=null){
//            session.removeAttribute("user");
//            model.addAttribute("user",new User());
//            return "add-employee"; 
//        }
//        return null;
//    }
}
