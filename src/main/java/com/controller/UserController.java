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
import com.Validator.RegisterForm;
import com.Validator.RegisterFormValidator;
import com.model.NguoiDung;
import com.securityImpl.CustomUser;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
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

    @Autowired
    RegisterFormValidator registerFormValidator;
    
    
    private static final Logger LOG = Logger.getLogger(UserController.class.getName());
    
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
            binder.setValidator(registerFormValidator);
    }
    
    public void getCache(Model map){
        map.addAttribute("category", danhMucServiceImpl.getDanhMucAll(CacheMap.getDanhMucAll));
        if(!map.containsAttribute("registerForm"))
            map.addAttribute("registerForm",new RegisterForm());
    }   
    
    @RequestMapping(value = { "/Login"}, method = RequestMethod.GET)
    public String login(Model model,HttpServletRequest req,HttpServletResponse res){
           
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(principal instanceof CustomUser) 
        {
            LOG.info("request to : /Login - "+((CustomUser) principal).getUsername());
            forwardSRC(req, res, "/");
        }else{
            this.getCache(model);
            return "Login"; 
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

    }
    @RequestMapping(value="/Register",method=RequestMethod.POST)
    public String Register(@Valid RegisterForm form,BindingResult binDing){
        if(binDing.hasErrors())
        {
            return "/Login";
        }
        return"/";
    }
}
