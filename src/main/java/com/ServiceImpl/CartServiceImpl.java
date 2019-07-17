/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ServiceImpl;

import com.Service.CartService;
import com.Service.ProductService;
import com.model.Sach;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

/**
 *
 * @author vital
 */
@Service
public class CartServiceImpl implements CartService{

    @Override
    public boolean addItem(HttpServletRequest request, ProductService productServiceImpl) {
        ArrayList<Sach> cart;
        String maSach = request.getParameter("maSach");
        try{
            Sach sach=productServiceImpl.getSach(maSach);
            if(request.getSession().getAttribute("cartItems")== null)
            {
               cart =new ArrayList();
               cart.add(sach);
               request.getSession().setAttribute("cartItems", cart);
              
            }else
            {
                cart=(ArrayList<Sach>) request.getSession().getAttribute("cartItems");
                cart.add(sach);
            }
            System.out.println("** Cart "+cart.size() + "  **\n**********\n*************"+sach.getMaSach()+"\n***\n"+sach.getTenSach());
        }catch(Exception e)
        {
            return false;
                }
        return true;
    }

    @Override
    public void deleteItem() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
