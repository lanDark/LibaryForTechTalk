/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ServiceImpl;

import com.DAO.SachDAO;
import com.Service.SachService;
import com.model.Sach;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vital
 */
@Service
@Transactional
public class SachServiceImpl implements SachService{
    @Autowired
    SachDAO sachDAOImpl;
    
    @Cacheable(value="mycache",key="#getNewProductView")//getNewProductView dữ liệu được lấy từ file CacheMap.class
    @Override
    public List getNewProductView(String getNewProductView) {
        return sachDAOImpl.getNewProductView();
    }
    

    @Override
    public boolean create(Sach object) {
        return sachDAOImpl.create(object);
    }

    @Override
    public List<Sach> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Sach find(String id) {
        return sachDAOImpl.find(id);
    }

    @Override
    public boolean update(Sach object) {
        return sachDAOImpl.update(object);
    }

    @Override
    public boolean delete(String id) {
        return sachDAOImpl.delete(id);
    }






    
}
