/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DAOImpl;

import com.DAO.UserDAO;
import com.model.NguoiDung;
import com.model.Rules;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
    /**
     *
     * Hàm xử lý riêng của lớp
     */
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
    private StringBuilder getMaNguoiDungLonNhat(Session session){
        String sql = 
                "Select max(N.maNguoiDung) "
                + "FROM NguoiDung as N "
                + "inner join Rules as R ON N.maNguoiDung = R.nguoiDung.maNguoiDung "
                + "WHERE R.role = 'ROLE_USER'";
        

        Query query = session.createQuery(sql);
        query.setMaxResults(1);
        List result = query.list();
        
        return  !(result.isEmpty()) ? new StringBuilder(result.get(0).toString()): new StringBuilder("BD0001"); 
       
    }  
    /**
     *
     * Các overide của interface
     */
    @Override
    public NguoiDung getUserByEmail(String email) {
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
    public boolean signUp(NguoiDung nguoiDung){
        int soTrongChuoiDuocLay;
        Rules rule;
        try{
            Session session = sessionFactory.getCurrentSession();
            StringBuilder maNguoiDung = new StringBuilder();
            rule = new Rules();
            
            maNguoiDung = this.getMaNguoiDungLonNhat(session);
            soTrongChuoiDuocLay = this.tachChuoiLaySo(maNguoiDung.toString());
            maNguoiDung = this.taoMaMoi(soTrongChuoiDuocLay);
            
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
   
    @Override
    public boolean create(NguoiDung nguoiDung) {
        Session session = sessionFactory.getCurrentSession();
        try{
        
            session.saveOrUpdate(nguoiDung);
            
            return true;
        }catch(Exception e){
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    @Override
    public List<NguoiDung> findAll() {
        Session session = sessionFactory.getCurrentSession();
        try{
            String hql =  "SELECT nd FROM NguoiDung nd "
                    + "INNER JOIN nd.rules rule"
                    + "on rule.role.nguoiDung.maNguoiDung = nd.maNguoiDung "
                    + "and role != :stringRole";
            Query query = session.createQuery(hql);
            List<NguoiDung> nguoiDungList;
            query.setParameter("stringRole", "ROLE_ADMIN");

            nguoiDungList = query.list();
            return nguoiDungList;   
        }catch(Exception e){
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }

    }

    @Override
    public NguoiDung find(String id) {
        try{
            Session session = sessionFactory.getCurrentSession();
            NguoiDung nguoiDung = session.get(NguoiDung.class, id);

            return nguoiDung;
        }catch(Exception e){
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

    @Override
    public boolean update(NguoiDung object) {
        try{
            Session session = sessionFactory.getCurrentSession();
            session.saveOrUpdate(object);
            
            return true;
        }catch(Exception e){
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        try{
            Session session = sessionFactory.getCurrentSession();
            NguoiDung nguoiDung = session.get(NguoiDung.class, id);
            session.delete(nguoiDung);
            
            return true;
        }catch(Exception e){
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    
}
