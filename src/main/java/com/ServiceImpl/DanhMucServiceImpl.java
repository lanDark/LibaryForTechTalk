/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ServiceImpl;

import com.DAOImpl.DanhMucDAOImpl;
import com.Service.DanhMucService;
import com.model.DanhMuc;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    public List getDanhMucAll(){
        return danhMucDAOImpl.getDanhMucAll();
    }
}
