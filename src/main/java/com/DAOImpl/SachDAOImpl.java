/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DAOImpl;

import com.DAO.SachDAO;
import com.model.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author vital
 */
@Repository
public class SachDAOImpl implements SachDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Sach> getNewProductView() {
        System.out.println("Getting new Product");
        Session session = sessionFactory.getCurrentSession();
        Criteria cr = session.createCriteria(Sach.class);
        cr.setMaxResults(10);
        cr.addOrder(Order.desc("ngayTao"));
        cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        System.out.println(cr.list().size());
        return cr.list();
    }


    @Override
    public boolean create(Sach object) {
        Session session = sessionFactory.getCurrentSession();
        try {
            session.saveOrUpdate(object);

            return true;
        } catch (Exception e) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    @Override
    public List<Sach> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Sach find(String id) {
        try {
            Session session = sessionFactory.getCurrentSession();
            Sach sach = session.get(Sach.class, id);

            return sach;
        } catch (Exception e) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

    @Override
    public boolean update(Sach object) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.saveOrUpdate(object);

            return true;
        } catch (Exception e) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        try {
            Session session = sessionFactory.getCurrentSession();
            Sach sach = session.get(Sach.class, id);

            session.delete(sach);

            return true;
        } catch (Exception e) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

}
