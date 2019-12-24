/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import static com.Class.ForwardHandling.forwardSRC;
import com.Service.UserService;
import com.Validator.RegisterForm;
import com.Validator.RegisterFormValidator;
import com.model.NguoiDung;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author vital
 */
@Controller
public class RegisterController {
    @Autowired
    UserService userServiceImpl;

    @Autowired
    RegisterFormValidator registerFormValidator;
    
    @Autowired
    protected AuthenticationManager authenticationManager;
    
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
            binder.setValidator(registerFormValidator);
    }
    
    //https://stackoverflow.com/questions/3813028/auto-login-after-successful-registration(link function)
    /*
     * link: https://stackoverflow.com/questions/3813028/auto-login-after-successful-registration(link function)
    
     * Hàm này tạo ra để làm tự động login cho Spring security sau khi đăng ký thành công
     * 
     */
    private void authenticateUserAndSetSession(RegisterForm user, HttpServletRequest request) {
        String username = user.getEmail();
        String password = user.getMatKhau();
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);

        // generate session if one deosn't exist 
        request.getSession();

        token.setDetails(new WebAuthenticationDetails(request));
        Authentication authenticatedUser = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
    }
    
    private boolean executeRegisterAndAutoLogin(RegisterForm form,HttpServletRequest req,HttpServletResponse res){
        boolean checkRegisterSuccess = userServiceImpl.signUp(form.convertToNguoiDungClass());
        if(checkRegisterSuccess){
            this.authenticateUserAndSetSession(form,req);
            return true;
        }
        else{
            return false;
        }
    }
    
    
    @RequestMapping(value="/Register",method=RequestMethod.POST,produces = "text/plain;charset=UTF-8")
    public String Register(@Valid RegisterForm form,BindingResult binDing,HttpServletRequest req,HttpServletResponse res){
        NguoiDung nguoiDung =  userServiceImpl.getUserByEmail(form.getEmail());
        
        if(binDing.hasErrors())
        {
            return "/Login";
        }else{ 
            
            if(nguoiDung != null){
              binDing.rejectValue("email","", "Email này đã được đăng ký");
              return "/Login";
            }
            if(this.executeRegisterAndAutoLogin(form,req,res))
                return "redirect:";
            else
                return"rediect:Login";
        }
    }
}
