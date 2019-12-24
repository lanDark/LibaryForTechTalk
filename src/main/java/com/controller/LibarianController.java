/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.Class.SearchCriteria;
import com.DAO.LibaryanDAO;
import com.DAO.PhieuMuonDAO;
import com.model.CtPhieumuon;
import com.model.PhieuMuon;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
@RequestMapping(value = {"/Libarian", "/libarian"})

public class LibarianController {

    @Autowired
    LibaryanDAO libaryanDAOImpl;

    @Autowired
    PhieuMuonDAO phieuMuonImpl;
    
    @RequestMapping(value = {"/home","/","Home","/index"})
    public String home() {
        return "Libarian/index";
    }

    @RequestMapping(value = "api/v1/PhieuMuons/{id}/CtphieuMuons/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity getChiTietPhieuMuon(@PathVariable int id) {
        List<CtPhieumuon> result = libaryanDAOImpl.getChiTietPhieuMuon(id);

        return new ResponseEntity(result, HttpStatus.OK);
    }

    @RequestMapping(value = "api/v1/PhieuMuons/{id}/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity getPhieuMuon(@PathVariable int id) {
        List<CtPhieumuon> result = libaryanDAOImpl.getChiTietPhieuMuon(id);
        return new ResponseEntity(result, HttpStatus.OK);
    }
    /**
     * @author vital
     * Tạo chức năng suggest PhieuMuons
     * Tìm kiếm dựa vào paramerte của method GET
     * các trường của phiếu mượn bao gồm NguoiDung.email,maPM,tinhTrang
     */
    @RequestMapping(value = "api/v1/PhieuMuons/suggest", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity searchPhieuMuonSuggest(HttpServletRequest req,HttpServletResponse res
    ) throws Exception{
        try{
            StringBuilder buildHQL;
            buildHQL= new StringBuilder("FROM PhieuMuon AS pm where ");

            Map<String,String[]> paramList = req.getParameterMap();
            List<String> mainList = new ArrayList<String>();
            mainList.addAll(paramList.keySet());
            Object arr[] = paramList.entrySet().toArray();
            for(Object obj : arr){
                System.out.println(obj.toString());
            }
             System.out.println("----------------------------------");
            for(String param : paramList.keySet()){
                System.out.println(param);
                System.out.println(paramList.get(param)[0]);
                if(param.equals("email")){
                    buildHQL.append("pm.nguoiDungByMaNguoiDung.email like '"+paramList.get(param)[0]+"%'");
                }else if(param.equals("maPm")){
                    buildHQL.append("pm.maPm like '"+paramList.get(param)[0]+"%'");
                }else
                    buildHQL.append("pm."+param+paramList.get(param)[0]);
                if(!param.equals(mainList.get(mainList.size()-1))){
                    buildHQL.append(" and ");
                }
                
            }
            System.out.println(buildHQL.toString());
            List result = phieuMuonImpl.phieuMuonSuggestion(buildHQL.toString());
            return new ResponseEntity(result, HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }
    }
    
    
    @RequestMapping(value = "api/v1/PhieuMuons/request-holds/count", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity getRequestHolds() {
        Map<String, Object> result = new HashMap<String, Object>();

        Object soLuongRequestHold = libaryanDAOImpl.getNumberRequestHold();
        result.put("number", soLuongRequestHold);

        return new ResponseEntity(result, HttpStatus.OK);
    }

    @RequestMapping(value = "api/v1/PhieuMuons/request-holds", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity getRequestHoldPagination(@RequestParam int page, @RequestParam int limit) {
        Map<String, Object> result = new HashMap<String, Object>();
        StringBuilder mess = new StringBuilder("");

        if (page <= 0 || limit <= 0) {

            mess.append("Page phải lớn hơn 0");

            if (limit <= 0) {
                mess.append(", Limit phải lớn hơn 0");
            }
            result.put("error", mess);
            return new ResponseEntity(result, HttpStatus.BAD_REQUEST);
        }

        List<PhieuMuon> yeuCauDatGiuList = libaryanDAOImpl.getPaginationRequestHold(page, limit);

        return new ResponseEntity(yeuCauDatGiuList, HttpStatus.OK);
    }
    
    @RequestMapping(value="api/v1/PhieuMuons/",method=RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<PhieuMuon> searchPhieuMuon(
            @RequestParam(required = false) String search
            ,HttpServletRequest req
            ,HttpServletResponse res){
        List<SearchCriteria> params = new ArrayList<SearchCriteria>();
        
        if(search != null){
            Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
            Matcher  matcher = pattern.matcher(search+",");
            while(matcher.find()){
                params.add(new SearchCriteria(matcher.group(1)
                                                            ,matcher.group(2)
                                                                ,matcher.group(3)));
            }
            List<PhieuMuon> results = phieuMuonImpl.searchPhieuMuon(params);
            
            return new ResponseEntity(results,HttpStatus.CREATED);
        }
        return null;
    }
    @RequestMapping(value = "/test404",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity test404(){
        return new ResponseEntity("test404",HttpStatus.OK);
    }
}