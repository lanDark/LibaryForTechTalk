/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DAOImpl;

import com.DAO.UserDAO;
import com.model.NguoiDung;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author vital
 */
@Repository
public class UserDAOImpl implements UserDAO {
    @Autowired
    SessionFactory sessionFactory;
    @Override
    public NguoiDung login(String email) {
        Session session=sessionFactory.openSession();
        String hql="From NguoiDung ND where ND.email= :ND_email";
        Query query=session.createQuery(hql);
        query.setParameter("ND_email", email);
        NguoiDung nguoiDung = (NguoiDung) query.list().get(0);
        return nguoiDung;
    }

    @Override
    public boolean signIn() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean logOut() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
