
package com.controller;

import com.Service.SachService;
import com.model.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    SachService sachServiceImpl;
    
    @RequestMapping(value="/quickview",method=POST)
    public String quickViewModal(HttpServletRequest request,HttpServletResponse res,Model model){
        String maSach=request.getParameter("maSach");
        Sach sach=sachServiceImpl.find(maSach);
        model.addAttribute("sach",sach);
        return "ajaxQuickView";
    }
    

}
