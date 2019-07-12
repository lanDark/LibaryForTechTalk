/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.Service.DanhMucService;
import com.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author vital
 */
@Controller
public class CategoryController {
//    @Autowired
//    DanhMucService danhMuc;
//    @RequestMapping(value="/category/{category}")
//    public String getCategoryByURL(@PathVariable String cate,Model model){
//        for(DanhMuc temp:danhMuc.getDanhMucAll()){
//            if(temp.getUrl().equals(cate)){
//                model.addAttribute("category", temp);
//                break;
//            }
//        }
//        return "";
//    }
}
