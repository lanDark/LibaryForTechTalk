/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;
import com.Service.CartService;
import com.Service.ProductService;
import com.model.Sach;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
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
        ArrayList<Sach> cartList=(ArrayList<Sach>) request.getSession().getAttribute("cartItems");
        model.addAttribute("cartList",cartList);
        return "cart";
    }
    
    @RequestMapping(value="/addToCart",method=POST)
    public String addToCartItem(HttpServletRequest request,Model model,HttpServletResponse res){
        System.out.println(request.getParameter("maSach"));
        boolean bool;
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
            Logger.getLogger(CartController.class.getName()).log(Level.SEVERE, null, ex);
            model.addAttribute("messenger",ex.getMessage());
            return "ajax/ShowModal";
        }
    }
    
    
    @RequestMapping(value="/removeItemCart",method=GET)
    public void removeItems(HttpServletRequest req,HttpServletResponse res,Model model){
       
    }
}
