/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DAOImpl;

import com.DAO.*;
import com.configuration.*;
import com.model.*;
import com.Class.*;
import java.util.*;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vital
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {HelloWorldConfig.class,HibernateConfig.class,HelloWorldInitializer.class})
@Transactional
@WebAppConfiguration

public class UserDAOImplTest {
    @Autowired
    UserDAO userDAOImpl;
    
    @Autowired
    PhieuMuonDAO phieuMuonDAO;
      
    public UserDAOImplTest() {
    }

    /**
     * Test of find method, of class UserDAOImpl.
     */
    @Test
    @Ignore
    public void testFindByEmail() {
        System.out.println(userDAOImpl.getUserByEmail("vitaliti97@gmail.com").getHoTen());
    }
    
    @Test
    @Ignore
    public void testSearchPhieuMuon1(){
        System.out.println("------------- Test 1 --------");
        List<SearchCriteria> params = new ArrayList<SearchCriteria>();
        params.add(new SearchCriteria("trangThai", ":", 1));
        List<PhieuMuon> pmList = phieuMuonDAO.searchPhieuMuon(params);
        
        pmList.forEach(pm -> {
            System.out.println(pm.getMaPm());
        });
    }
    @Test
    
    public void testSearchPhieuMuon2(){
        System.out.println("------------- Test 2 --------");
        List<SearchCriteria> params = new ArrayList<SearchCriteria>();
        params.add(new SearchCriteria("nguoiDungByMaNguoiDung",":", "BD0001"));
        params.add(new SearchCriteria("trangThai",":", 2));
        List<PhieuMuon> pmList = phieuMuonDAO.searchPhieuMuon(params);
        
        pmList.forEach(pm -> {
            System.out.println(pm.getMaPm());
        });
    }
    @Test@Ignore
    public void getUser(){
        NguoiDung nguoiDung = userDAOImpl.find("BD0001");
        System.out.println(nguoiDung.getMaNguoiDung());
    }

    
    
}
