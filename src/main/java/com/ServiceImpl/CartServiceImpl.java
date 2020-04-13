/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ServiceImpl;

import com.DAO.CartDAO;
import com.Service.CartService;
import com.model.Sach;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vital
 */
@Service
@Transactional(rollbackFor = SQLException.class)
public class CartServiceImpl implements CartService{
    @Autowired 
    CartDAO cartDAOImpl;
    
    @Override
    public boolean datMuon(List<Sach> saches, String maNguoiDung) throws Exception {
        try{
            if(cartDAOImpl.datMuon(saches, maNguoiDung)){
                return true;
            } 
        }catch(SQLException sql){
            Logger.getLogger(CartServiceImpl.class.getName()).log(Level.SEVERE, null, sql);
            throw sql;
        }catch(Exception e){
            Logger.getLogger(CartServiceImpl.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
        return true;
    }

}
