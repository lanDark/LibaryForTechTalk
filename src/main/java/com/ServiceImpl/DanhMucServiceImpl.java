/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ServiceImpl;

import com.DAOImpl.DanhMucDAOImpl;
import com.Service.DanhMucService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vital
 */
    @Service(value = "danhMucService")
    @Transactional
public class DanhMucServiceImpl implements DanhMucService{
    @Autowired
    public DanhMucDAOImpl danhMucDAOImpl;
    
    @Cacheable(value="mycache",key="#getDanhMucAll")
    public List getDanhMucAll(String getDanhMucAll){
        return danhMucDAOImpl.getDanhMucAll();
    }
}
