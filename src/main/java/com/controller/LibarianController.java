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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
        return "Libarian/index";
    }
    
    @RequestMapping(value = "api/v1/PhieuMuons/{id}/CtphieuMuons/",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
   
    public  @ResponseBody ResponseEntity getChiTietPhieuMuon(@PathVariable int id) {
        List<CtPhieumuon> result = libaryanDAOImpl.getChiTietPhieuMuon(id);
        
        return new ResponseEntity(result,HttpStatus.OK);
    }
    
    @RequestMapping(value = "api/v1/PhieuMuons/request-holds/count",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity getRequestHolds(){
        Map<String,Object> result = new HashMap<String,Object>();

        Object soLuongRequestHold = libaryanDAOImpl.getNumberRequestHold();
        result.put("number", soLuongRequestHold);
       
        
        return new ResponseEntity(result,HttpStatus.OK);
    }
    
    @RequestMapping(value = "api/v1/PhieuMuons/request-holds",method = RequestMethod.GET)
    public @ResponseBody ResponseEntity getRequestHoldPagination(@RequestParam int page,@RequestParam int limit){
        Map<String,Object> result = new HashMap<String,Object>();
        StringBuilder mess = new StringBuilder("");
        
        if(page <= 0 || limit <= 0){
            
            mess.append("Page phải lớn hơn 0");
            
            if(limit <= 0 )
                mess.append(", Limit phải lớn hơn 0"); 
            result.put("error", mess);
            return new ResponseEntity(result,HttpStatus.BAD_REQUEST);
        }
        
        List<PhieuMuon> yeuCauDatGiuList = libaryanDAOImpl.getPaginationRequestHold(page,limit);
        for(PhieuMuon phieuMuon : yeuCauDatGiuList)
        {
            phieuMuon.getNguoiDungByMaNguoiDung().setMatKhau(null);
        }
       return new ResponseEntity(yeuCauDatGiuList,HttpStatus.OK);
    }

}
