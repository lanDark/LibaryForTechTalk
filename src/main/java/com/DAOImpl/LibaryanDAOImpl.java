/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DAOImpl;

import com.DAO.LibaryanDAO;
import com.model.CtPhieumuon;
import com.model.PhieuMuon;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vital
 */
@Repository
public class LibaryanDAOImpl implements LibaryanDAO{
    @Autowired
    SessionFactory sessionFactory;
    
    @Transactional
    @Override
    public List<CtPhieumuon> getChiTietPhieuMuon(int idPhieuMuon) {
        Session session = sessionFactory.getCurrentSession();
        String hql = " FROM CtPhieumuon as Ctpm WHERE Ctpm.phieuMuon.maPm = :maPhieuMuon";
        
        Query query = session.createQuery(hql);
        query.setParameter("maPhieuMuon", idPhieuMuon);
        List ctPhieuMuonList = query.list();
        
        return ctPhieuMuonList;
    }

    @Override
    @Transactional
    public List<PhieuMuon> getYeuCauGiuMoiNhat() {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM PhieuMuon as PM WHERE PM.trangThai = 1 ORDER BY PM.ngayDat DESC";

        Query query = session.createQuery(hql);
        query.setFirstResult(0);
        query.setMaxResults(10);
        
        List<PhieuMuon> yeuCauDatGiuList = query.list();
        for(PhieuMuon phieuMuon : yeuCauDatGiuList)
        {
            phieuMuon.getNguoiDungByMaNguoiDung().setMatKhau("");
        }
       return yeuCauDatGiuList;
       
    }
    
    @Override
    @Transactional
    public List<PhieuMuon> getPaginationRequestHold(int page,int limit){
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM PhieuMuon as PM WHERE PM.trangThai = 1 ORDER BY PM.ngayDat DESC";
        
        Query query = session.createQuery(hql);
        if(page == 1 && limit ==1){
            query.setFirstResult(0);
            query.setMaxResults(limit);
        }
        else if(page == 1 && limit > 1){
            query.setFirstResult(0);
            query.setMaxResults(limit);
        }
        else{
            query.setFirstResult(limit*(page-1)); 
            query.setMaxResults((limit*page)-1);    
        }
        List<PhieuMuon> yeuCauDatGiuList = query.list();
        for(PhieuMuon phieuMuon : yeuCauDatGiuList)
        {
            phieuMuon.getNguoiDungByMaNguoiDung().setMatKhau("");
        }
        return yeuCauDatGiuList;
    }

    @Override
    @Transactional
    public Object getNumberRequestHold() {
        String hql = "SELECT count(*) FROM PhieuMuon as PM WHERE PM.trangThai = 1";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hql);
        
        Iterator itr = query.iterate();
        
        return (Long) itr.next();
    }
}
