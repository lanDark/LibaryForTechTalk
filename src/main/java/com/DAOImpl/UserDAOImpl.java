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
    public NguoiDung login(String email) {
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
            nguoiDung = (NguoiDung) query.list().get(0);
            
            return nguoiDung;
        }catch(Exception e){
              Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, e);
              return null;
        }
    }

    
    private StringBuilder getMaNguoiDungBiggest(Session session){
        String sql = 
                "Select max(n.maNguoiDung) "
                + "FROM NguoiDung as n "
                + "WHERE n.rules.id=2";
        

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
    
    @Override
    public boolean signUp(NguoiDung nguoiDung) {
        try{
            Session session = sessionFactory.getCurrentSession();
            StringBuilder maNguoiDung = new StringBuilder();
            int so; 
            
            maNguoiDung = this.getMaNguoiDungBiggest(session);
            so = this.tachChuoiLaySo(maNguoiDung.toString());
            maNguoiDung = this.taoMaMoi(so);
            nguoiDung.setMatKhau(passwordEncoder.encode(nguoiDung.getMatKhau()));
            nguoiDung.setMaNguoiDung(maNguoiDung.toString());
            nguoiDung.setRules(new Rules(2));
            nguoiDung.setTinhTrang(1);
            session.save(nguoiDung);
            
            return true;
        }catch(Exception e){
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, e);
            
            return false;
            
        }
    }



    @Override
    public boolean datMuon(ArrayList<Cart> listCart,CustomUser user) throws Exception {
        Session session=sessionFactory.getCurrentSession();
        PhieuMuon phieuMuon =this.createPhieuMuon( session.get(NguoiDung.class, user.getMaNguoiDung()));
        session.save(phieuMuon);
        LOG.info("Connect to method UserDAOIMPL.datMuon :  "+ user.getUsername());
            if(phieuMuon != null)
            {
                for(Cart item:listCart){ 
                    for(int  i = 1;i<=item.getSoLuong();i++){
                          try {
                            CtPhieumuon ctpm=new CtPhieumuon();
                            ctpm.setSach(item.getSach());
                            ctpm.setPhieuMuon(phieuMuon);
                            ctpm.setSoLuong(item.getSoLuong());
                            Query giamSoLuongSach=session.createQuery("update Sach set soLuong=soLuong-:soLuong where maSach=:maSach");
                            giamSoLuongSach.setParameter("maSach", item.getSach().getMaSach());
                            giamSoLuongSach.setParameter("soLuong", item.getSoLuong());
                            giamSoLuongSach.executeUpdate();
                            session.save(ctpm);
                        } catch (Exception ex) {
                            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
                            throw new Exception("Xin lỗi sách: \""+ item.getSach().getTenSach()+"\" tạm thời đã hết!");
                        }
                    }
                }
                return true;
            }
        return false;
       
    }
    
    public PhieuMuon createPhieuMuon(NguoiDung nguoiDung) {
        PhieuMuon phieuMuon = new PhieuMuon();
          LOG.info("Connect to method UserDAOIMPL.createPhieuMuon :  "+ nguoiDung.getMaNguoiDung());
        Calendar c = Calendar.getInstance();
        try {
            phieuMuon.setTrangThai(1);
            phieuMuon.setNgayDat(c.getTime());
            phieuMuon.setNguoiDungByMaNguoiDung(nguoiDung);
            return phieuMuon;
        } catch (Exception ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
             return null;
        }
    }

}
