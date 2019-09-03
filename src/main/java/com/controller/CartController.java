/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;
import Class.FunctionCart;
import com.Service.CartService;
import com.Service.ProductService;
import com.model.Cart;
import com.securityImpl.CustomUser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 *
 * @author vital
 */
@Controller
public class CartController {
    @Autowired
    ProductService productServiceImpl;
    
    @Autowired
    CartService cartServiceImpl;
    @RequestMapping(value="/CartView",method=RequestMethod.GET)
    
    public String CartView(HttpServletRequest request,Model model){
        ArrayList<Cart> cartList=(ArrayList<Cart>) request.getSession().getAttribute("cartItems");
        model.addAttribute("cartList",cartList);
        return "cart";
    }
    
    @RequestMapping(value="/addToCart",method=POST)
    public String addToCartItem(HttpServletRequest request,Model model,HttpServletResponse res) throws IOException {
        boolean bool;
        Object printical= SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(printical instanceof CustomUser)//Kiểm tra bạn đọc đã xác thực chưa
        {
            try {
                bool = cartServiceImpl.addItem(request, productServiceImpl);
                res.setContentType("text/html;charset=UTF-8");
                if(bool)
                {   model.addAttribute("messenger","Thêm vào giỏ thành công");
                    return "ajax/ShowModal";
                }else
                {
                      model.addAttribute("messenger","Thêm vào giỏ thất bại"); 
                      return "ajax/ShowModal";
                }
            } catch (Exception ex) {
                model.addAttribute("messenger",ex.getMessage());
                return "ajax/ShowModal";
            }
        }
        else // nếu chưa xác thực thì chuyển hướng đến trang đăng nhập
        {
            res.sendError(403);//Jquery bắt lỗi và tự động redirectt đến trang Login
            return "index";
        }
    }
    
    
    @RequestMapping(value="/deleteItemCart",method=GET,produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String deleteItem(HttpServletRequest req,HttpServletResponse res){
       try{
           this.cartServiceImpl.deleteItem(req);
       }catch(Exception ex){
           return "<pr> "+ex.getMessage()+" </p>";
       }
        return "";
    }
    
    @RequestMapping(value="/updateItemCart",method=GET,produces = "text/plain;charset=UTF-8")
    @ResponseBody
   public String updateItem(HttpServletRequest req ) {
        try {
            cartServiceImpl.updateItem(req);
        } catch (Exception ex) {
           return "<pr> "+ex.getMessage()+" </p>";
           
        }
        return "";
   }
}
