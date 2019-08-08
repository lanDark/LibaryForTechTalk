package com.controller;
import Class.CacheMap;
import com.Service.DanhMucService;
import com.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;  
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;  
@Controller  
public class MainController {  
    
  @Autowired
  DanhMucService danhMucService;
  
  @Autowired
  ProductService productService;
  @RequestMapping("/")
  public String home(Model map) {
    map.addAttribute("category", danhMucService.getDanhMucAll(CacheMap.getDanhMucAll));
    map.addAttribute("productNews",productService.getNewProductView(CacheMap.getNewProductView));
    return "index";
  }
  
}  