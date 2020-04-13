/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DAOImpl;

import com.Class.SearchCriteria;
import com.DAO.PhieuMuonDAO;
import com.model.PhieuMuon;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import javax.validation.ConstraintViolationException;
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
@Transactional
public class PhieuMuonDAOImpl implements PhieuMuonDAO{
    @Autowired
    SessionFactory sessionFactory;
    
    @Override
    public List<PhieuMuon> findByEmailUsingLike(String email) {
        try{
            Session ses = sessionFactory.getCurrentSession();
            String hql = "FROM PhieuMuon as pm WHERE pm.nguoiDungByMaNguoiDung.email like :email ";
            Query query = ses.createQuery(hql);
            
            query.setParameter("email", email+"%");
            return query.list();
        }catch(ConstraintViolationException e){
            e.getMessage();
            return null;
        }
        catch(Exception ex){
            ex.getMessage();
            return null;
        }
        
        
    }

    @Override
    public List<PhieuMuon> findIdUsingLike(int maND) {
        try{
            Session ses = sessionFactory.getCurrentSession();
            String hql = "FROM PhieuMuon as pm WHERE pm.nguoiDungByMaNguoiDung.email like ':maND%' ";
            Query query = ses.createQuery(hql);
            query.setParameter("maND", maND);
            
            return query.list();
        }catch(ConstraintViolationException e){
            e.getMessage();
            return null;
        }
        catch(Exception ex){
            ex.getMessage();
            return null;
        }
    }
    
    @Override
    public List phieuMuonSuggestion(String hql) throws Exception{
        try {
            Session session = sessionFactory.getCurrentSession();
            List<PhieuMuon> result = session.createQuery(hql).list();
            
            return result;    
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        
    }
    public List<PhieuMuon> searchPhieuMuon(List<SearchCriteria> params){
        EntityManager entityManager =  sessionFactory.createEntityManager();
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<PhieuMuon> criteriaQuery = builder.createQuery(PhieuMuon.class);
        Root<PhieuMuon> r = criteriaQuery.from(PhieuMuon.class);
        Predicate predicate = builder.conjunction();
        PhieuMuonSearchQueryCriteriaConsumer searchPhieuMuon 
                    = new PhieuMuonSearchQueryCriteriaConsumer(predicate,builder,r);
       
        params.stream().forEach(searchPhieuMuon );
        predicate = searchPhieuMuon.getPredicate();
       
        criteriaQuery.where(predicate);
        List<PhieuMuon> result = entityManager.createQuery(criteriaQuery).getResultList();
        
        return result;
        
    }
    
    @Override
    public boolean create(PhieuMuon o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PhieuMuon> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PhieuMuon find(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(PhieuMuon o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
