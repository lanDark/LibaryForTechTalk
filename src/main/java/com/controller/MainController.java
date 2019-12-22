    package com.controller;
import com.Class.CacheMap;
import com.Service.DanhMucService;
import com.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;  
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
@Controller  
public class MainController {  
    
    @Autowired
    DanhMucService danhMucService;

    @Autowired
    ProductService productService;
    

    
    @RequestMapping("/")
    public String home(Model map,WebRequest swr) {

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
    
    
    
}  