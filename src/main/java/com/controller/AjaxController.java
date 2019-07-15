
package com.controller;

import com.Service.ProductService;
import com.model.*;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 *
 * @author vital
 */
@Controller
public class AjaxController  {
    @Autowired
    ProductService productServiceImpl;
    
    @RequestMapping(value="/quickview",method=POST)
    public String quickViewModal(HttpServletRequest request,Model model){
        String maSach=request.getParameter("maSach");
        Sach sach=productServiceImpl.getSach(maSach);
        System.out.println("********\n**********\n*************"+sach.getMaSach()+"\n***\n"+sach.getTenSach());
        model.addAttribute("sach",sach);
        return "ajaxQuickView";
    }
    
    @RequestMapping(value="/addToCart",method=POST)
    public void addToCartItem(HttpServletRequest request,Model model){
        ArrayList<Sach> cart;
        String maSach = request.getParameter("maSach");
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
    }
}
