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
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    public void addToCartItem(HttpServletRequest request,Model model){
        boolean bool = cartServiceImpl.addItem(request, productServiceImpl);
        if(bool)
        {
            
        }else{
            
        }
    }
}
