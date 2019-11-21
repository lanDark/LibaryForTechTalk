/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DAOImpl;

import com.DAO.UserDAO;
import com.model.Cart;
import com.model.CtPhieumuon;
import com.model.NguoiDung;
import com.model.PhieuMuon;
import com.model.Rules;
import com.securityImpl.CustomUser;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

/**
 *
 * @author vital
 */
@Repository
public class UserDAOImpl implements UserDAO {
    @Autowired
    SessionFactory sessionFactory;
    
    @Autowired
    PasswordEncoder passwordEncoder;
    
    private final Logger LOG=Logger.getLogger(UserDAOImpl.class.getName());
    
    @Override
    public NguoiDung getUserByEmail(String email) {
        LOG.info("Connect to method UserDAOIMPL.login :  "+email);
        try
        {
            String hql="From NguoiDung ND where ND.email= :ND_email";
            Session session;
            Query query;
            NguoiDung nguoiDung;
            
            session=sessionFactory.getCurrentSession();
            query = session.createQuery(hql);
            query.setParameter("ND_email", email);
            
            return (query.list().size() > 0) ? (NguoiDung) query.list().get(0) : null;
        }catch(Exception e){
              Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, e);
              return null;
        }
    }

    

    
    @Override
    public boolean signUp(NguoiDung nguoiDung) {
        int so;
        Rules rule;
        try{
            Session session = sessionFactory.getCurrentSession();
            StringBuilder maNguoiDung = new StringBuilder();
            rule = new Rules();
            
            maNguoiDung = this.getMaNguoiDungBiggest(session);
            so = this.tachChuoiLaySo(maNguoiDung.toString());
            maNguoiDung = this.taoMaMoi(so);
            
            rule.setNguoiDung(nguoiDung);
            rule.setRole("ROLE_USER");
            
            nguoiDung.setMatKhau(passwordEncoder.encode(nguoiDung.getMatKhau()));
            nguoiDung.setMaNguoiDung(maNguoiDung.toString());
            nguoiDung.setTinhTrang(1);
            
            session.save(nguoiDung);
            session.save(rule);
            
            return true;
        }catch(Exception e){
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, e);
            
            return false;
            
        }
    }
    
    private StringBuilder getMaNguoiDungBiggest(Session session){
        String sql = 
                "Select max(N.maNguoiDung) "
                + "FROM NguoiDung as N "
                + "inner join Rules as R ON N.maNguoiDung = R.nguoiDung.maNguoiDung "
                + "WHERE R.role = 'ROLE_USER'";
        

        Query query = session.createQuery(sql);
        query.setMaxResults(1);
        List result = query.list();
        
        return  (result.size()>0)? new StringBuilder(result.get(0).toString()): new StringBuilder("BD0001"); 
       
    }   
    
    private int tachChuoiLaySo(String ma){
        String temp = ma.substring(2);
        int so = Integer.parseInt(temp);
        
        return so+1;
    }
    
    private StringBuilder taoMaMoi(int so){
        StringBuilder temp = new StringBuilder("BD"+so);
        
        while(temp.length()<6){
            temp.insert(2, "0");
        }
        
        return temp;
    }


}
