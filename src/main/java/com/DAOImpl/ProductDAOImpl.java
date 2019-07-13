/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DAOImpl;

import com.DAO.ProductDAO;
import com.model.*;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author vital
 */
@Repository
public class ProductDAOImpl implements ProductDAO {
    @Autowired
    SessionFactory sessionFactory;
    
    @Override
    public List<Sach> getNewProductView() {
        Session session=sessionFactory.getCurrentSession();

        Criteria cr=session.createCriteria(Sach.class);
<<<<<<< HEAD
        cr.addOrder(Order.desc("ngayTao"));
        cr.setMaxResults(10);
=======
        cr.setMaxResults(10);
        cr.addOrder(Order.desc("ngayTao"));
        cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        System.out.println(cr.list().size());
>>>>>>> feature-productNews
        return cr.list();
    }

    @Override
    public List getProductByCategoryView() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List getProductByKindView() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateProduct() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean insertProduct() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteProduct() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
