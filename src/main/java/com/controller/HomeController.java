package com.controller;
import com.Class.CacheMap;
import com.Service.DanhMucService;
import com.Service.SachService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;  
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
    
@Controller  
public class HomeController {  
    
    @Autowired
    DanhMucService danhMucService;

    @Autowired
    SachService productService;
    
    
    @RequestMapping("/")
    public String home(Model map) {

        this.getCache(map);
        return "index";
    }
    
    @RequestMapping(value="/",method=RequestMethod.POST)
    public String homePostMethod(Model map){
        this.getCache(map);
        return "index";
    }
    
    public void getCache(Model map){
        map.addAttribute("category", danhMucService.getDanhMucAll(CacheMap.getDanhMucAll));
        map.addAttribute("productNews",productService.getNewProductView(CacheMap.getNewProductView));
    }   
    
  @RequestMapping(value = "/parseExceptionToJson_29051982")
    public @ResponseBody ResponseEntity processExceptionToJson(HttpServletRequest req,HttpServletResponse res,Model model) {
        model.addAttribute("status", req.getAttribute("status"));
        model.addAttribute("messeenger", req.getAttribute("messeenger"));
        
        return new ResponseEntity(model, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}  