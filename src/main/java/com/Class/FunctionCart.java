/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Class;

import com.Service.ProductService;
import com.model.Cart;
import com.model.Sach;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author vital
 */
public class FunctionCart{
    // Chức năng này nhằm tìm kiếm item có trong giỏ hay chưa nếu có thì tăng số lượng lên 1, nếu chưa thì thêm mới vào
    public ArrayList<Cart> search(ProductService productServiceImpl,ArrayList<Cart> cartList,String maSach){
        for(Cart cart:cartList)
        {
            if(cart.getSach().getMaSach().equals(maSach))
            {
                cart.setSoLuong(cart.getSoLuong()+1);
                return cartList;
            }
        }
        cartList.add(new Cart(productServiceImpl.getSach(maSach),1));
        return cartList;
    }
    public boolean updateItemCart(String maSach,int soLuong,List cartItems){
               
            Iterator itr=cartItems.iterator();
            while(itr.hasNext())
            {
                Cart cart=(Cart) itr.next();
                Sach sach=cart.getSach();
                if(sach.getMaSach().equals(maSach))
                {
                    cart.setSoLuong(soLuong);
                    return false;
                }
            }
        return true;
    }
    public boolean deleteItemCart(String maSach,List cartItems)
    {
            Iterator itr=cartItems.iterator();
            while(itr.hasNext())
            {
                Cart cart=(Cart) itr.next();
                Sach sach=cart.getSach();
                if(sach.getMaSach().equals(maSach))
                {
                  itr.remove();
                  return true;
                }
            }
            return false;
    }
}
