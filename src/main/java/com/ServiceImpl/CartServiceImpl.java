/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ServiceImpl;

import Class.SearchItemInCart;
import com.model.Cart;
import com.Service.CartService;
import com.Service.ProductService;
import com.model.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

/**
 *
 * @author vital
 */
@Service
public class CartServiceImpl implements CartService{
    ArrayList<Cart> cartList;
    String maSach;
    
    @Override
    public boolean addItem(HttpServletRequest request, ProductService productServiceImpl) throws Exception {

        this.maSach = request.getParameter("maSach");
        NguoiDung nguoiDung = (NguoiDung) request.getSession().getAttribute("nguoiDung");
        if(nguoiDung == null)    throw new Exception("Bạn đọc chưa đăng nhập");
        try{
            
            if(nguoiDung.getRules().getId() == 2)
            {    
                if(request.getSession().getAttribute("cartItems")== null)
                {
                   Cart cart=new Cart();
                   cart.setSach(productServiceImpl.getSach(maSach));
                   cart.setSoLuong(cart.getSoLuong()+1);
                   this.cartList =new ArrayList();
                   this.cartList.add(cart);
                   request.getSession().setAttribute("cartItems", this.cartList);

                }else
                {
                    this.cartList=(ArrayList<Cart>) request.getSession().getAttribute("cartItems");
                    this.cartList=new SearchItemInCart().search(productServiceImpl,this.cartList,maSach);
                }
                System.out.println("** CartList "+cartList.size() + "  **\n**********\n*************");
            }
        }catch(Exception e)
        {
            Logger.getLogger(CartServiceImpl.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
        return true;
    }

    @Override
    public void deleteItem() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
