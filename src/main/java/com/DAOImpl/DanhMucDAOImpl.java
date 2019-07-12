/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DAOImpl;

import com.DAO.DanhMucDAO;
import com.model.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vital
 */
@Repository(value = "danhMucDAO")
public class DanhMucDAOImpl implements Serializable,DanhMucDAO {
    
    @Autowired
    SessionFactory sessionFactory;
    Transaction transaction;
    @Override
    public List getDanhMucAll() {
        Map<String,Long> soLuongFromDanhMuc=new HashMap<String,Long> (); //String: id Danh mục; integer: số lượng sách
        List returns=new ArrayList();  //Trả về kết quả
        Session session=sessionFactory.getCurrentSession();
        List<DanhMuc> result=session.createQuery("FROM DanhMuc").list();
        for(DanhMuc temp:result)
        {
            Long soLuong=countAllBookInCategory(temp.getIdDanhMuc());
            soLuongFromDanhMuc.put(temp.getUrl(), soLuong);
        }
        returns.add(result);
        returns.add(soLuongFromDanhMuc);
        return returns;
    }

    @Override
    public DanhMuc getDanhMucByURL(String url) {
        List<DanhMuc> list=this.getDanhMucAll();
        for(DanhMuc temp:list){
            if(temp.getUrl().equals(url)){
                return temp;
            }
        }
        return null;
    }

    @Override
    public boolean updateDanhMuc(DanhMuc obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteDanhMuc(DanhMuc obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean insertDanhMuc(DanhMuc obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean getDanhMucObj(DanhMuc obj) {
 throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public  Long countAllBookInCategory(int idDanhMuc){
       String hql="select count(s)"
                + " FROM Sach as s"
                + " WHERE s.theLoai.danhMuc.id_DanhMuc= :id_Param";
        Session session=sessionFactory.getCurrentSession();
        Query query= session.createQuery(hql);
        query.setParameter("id_Param",idDanhMuc);
        List l=query.list();
        Object obj[]=l.toArray();
       
        return (Long) obj[0];
    }
}
