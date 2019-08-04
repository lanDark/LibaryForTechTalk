/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ServiceImpl;

import Class.FunctionCart;
import com.model.Cart;
import com.Service.CartService;
import com.Service.ProductService;
import com.model.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
                if(request.getSession().getAttribute("cartItems")== null) // kiểm tra session cart có bằng null không nếu có thì tạo session cart
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
                    this.cartList=new FunctionCart().search(productServiceImpl,this.cartList,maSach);
                }
            }
        }catch(Exception e)
        {
            Logger.getLogger(CartServiceImpl.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
        return true;
    }

    @Override
    public void deleteItem(HttpServletRequest req) throws Exception {
        try{
            String maSach=req.getParameter("maSach");
            ArrayList cartList =(ArrayList) req.getSession().getAttribute("cartItems");
            new FunctionCart().deleteItemCart(maSach, cartList);
        }catch(Exception ex)
        {
            Logger.getLogger(CartServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Có lỗi sảy ra vui lòng thử lại ");
        }

    }

    @Override
    public void updateItem(HttpServletRequest req) throws Exception {
        String maSach= req.getParameter("maSach");
        int soLuong= Integer.parseInt(req.getParameter("soLuong"));
        ArrayList<Cart> cartItems=(ArrayList<Cart>) req.getSession().getAttribute("cartItems");
        try{
            boolean bool=new FunctionCart().updateItemCart(maSach, soLuong, cartItems);
        }catch(Exception e){
            Logger.getLogger(CartServiceImpl.class.getName()).log(Level.SEVERE, null, e);
            throw new Exception("Có lỗi sảy ra vui lòng thử lại! ");
        }

    }
    
}
