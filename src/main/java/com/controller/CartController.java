/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;
import com.Service.CartService;
import com.Service.SachService;
import com.model.Sach;
import com.securityImpl.CustomUser;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 *
 * @author vital
 */
@Controller
public class CartController {
    @Autowired
    SachService sachServiceImpl;
    
    @Autowired
    CartService cartServiceImpl;

    
    @RequestMapping(value="/CartView",method=RequestMethod.GET)
    public String CartView(HttpServletRequest request,Model model){
        return "cart";
    }
    
    
    @RequestMapping(value="/datMuon",method=RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public ResponseEntity addToCartItem(@RequestBody ArrayList<Sach> cart,HttpServletRequest request,Model model,HttpServletResponse res) throws IOException, Exception {
        boolean bool;
        Map<String,Object> result = new HashMap<String,Object>();
        
        Object printical= SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        try {
            if(printical instanceof CustomUser)//Kiểm tra bạn đọc đã xác thực chưa
            {

                if(cartServiceImpl.datMuon(cart,((CustomUser) printical).getMaNguoiDung())){
                    result.put("status",201);
                    result.put("success", "Xử lý đặt thành công");
                    return new ResponseEntity(result,HttpStatus.CREATED);
                }

            }
            else // nếu chưa xác thực thì chuyển hướng đến trang đăng nhập
            {
                result.put("status",403);
                result.put("messe", "bạn chưa đăng nhập");
                return new ResponseEntity(result,HttpStatus.FORBIDDEN);
            }
        
        }catch(SQLException sqlException){
            throw new SQLException(sqlException.getMessage());
            
        } 
        return new ResponseEntity(result,HttpStatus.CREATED);
    }

}
