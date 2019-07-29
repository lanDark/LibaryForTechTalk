/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import com.Service.ProductService;
import com.model.Cart;
import java.util.ArrayList;

/**
 *
 * @author vital
 */
public class SearchItemInCart{
    public ArrayList<Cart> search( ProductService productServiceImpl,ArrayList<Cart> cartList,String maSach){
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
}
