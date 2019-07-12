package com.controller;
import com.Service.DanhMucService;
import com.Service.ProductService;
import com.model.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;  
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import org.springframework.web.bind.annotation.RequestParam;
@Controller  
public class MainController {  
    
  @Autowired
  DanhMucService danhMucService;
  
  @Autowired
  ProductService productService;
  @RequestMapping("/")
  public String home(Model map) {
    map.addAttribute("category", danhMucService.getDanhMucAll());
    map.addAttribute("productNews",productService.getNewProductView());
    return "index";
  }
  
}  