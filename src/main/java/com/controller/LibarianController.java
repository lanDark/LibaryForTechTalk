/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.DAO.LibaryanDAO;
import com.model.CtPhieumuon;
import com.model.PhieuMuon;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author vital
 */
@Controller
@RequestMapping(value = "/Libarian")

public class LibarianController {
    @Autowired
    LibaryanDAO libaryanDAOImpl;
    @RequestMapping(value = "/home")
    public String home(){
        return "Admin/index";
    }
    
    @RequestMapping(value = "api/v1/PhieuMuons/{id}/CtphieuMuons/",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
   
    public  @ResponseBody ResponseEntity getChiTietPhieuMuon(@PathVariable int id) {
        List<CtPhieumuon> result = libaryanDAOImpl.getChiTietPhieuMuon(id);
        
        return new ResponseEntity(result,HttpStatus.OK);
    }
    
    @RequestMapping(value = "api/v1/PhieuMuons/request-holds/",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity getRequestHolds(){
        Map<String,Object> result = new HashMap<String,Object>();
        List<PhieuMuon> yeuCauDatGiuList = libaryanDAOImpl.getYeuCauGiuMoiNhat();
        Object soLuongRequestHold = libaryanDAOImpl.getNumberRequestHold();
        
        result.put("soLuong", soLuongRequestHold);
        result.put("list",yeuCauDatGiuList);
        
        return new ResponseEntity(result,HttpStatus.OK);
    }
    
    @RequestMapping(value = "api/v1/PhieuMuons/request-holds/page/{id}",method = RequestMethod.GET)
    public @ResponseBody ResponseEntity getRequestHoldPagination(@PathVariable int id){
        Map<String,Object> result = new HashMap<String,Object>();
        
        if(id <= 0){
            result.put("error", "Lỗi Input/Output");
            return new ResponseEntity(result,HttpStatus.BAD_REQUEST);
        }
           
        List<PhieuMuon> yeuCauDatGiuList = libaryanDAOImpl.getPaginationRequestHold(id);
        
      
       return new ResponseEntity(yeuCauDatGiuList,HttpStatus.OK);
    }

}
