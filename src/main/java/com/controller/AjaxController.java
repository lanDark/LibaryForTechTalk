
package com.controller;

import com.Service.ProductService;
import com.model.*;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.http.HttpMethod.GET;
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
    

}
