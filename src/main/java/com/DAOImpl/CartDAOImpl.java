/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DAOImpl;

import com.DAO.CartDAO;
import com.model.CtPhieumuon;
import com.model.NguoiDung;
import com.model.PhieuMuon;
import com.model.Sach;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class CartDAOImpl implements CartDAO{
    @Autowired
    SessionFactory sessionFactory;
    
    final Logger LOG = Logger.getLogger(CartDAOImpl.class.getName());
    
    @Override
    public boolean datMuon(List<Sach> saches,String maNguoiDung)throws SQLException {
        Session session=sessionFactory.getCurrentSession();
        PhieuMuon phieuMuon =this.createPhieuMuon( session.get(NguoiDung.class, maNguoiDung));
        session.save(phieuMuon);
        LOG.info("Connect to method UserDAOIMPL.datMuon :  "+ maNguoiDung+"|| "+phieuMuon.getMaPm());
            if(phieuMuon != null)
            {
                for(Sach sach:saches){ 
                    for(int  i = 0;i<sach.getSoLuong();i++){
                        CtPhieumuon ctpm=new CtPhieumuon();
                        ctpm.setSach(sach);
                        ctpm.setPhieuMuon(phieuMuon);
                        ctpm.setSoLuong(sach.getSoLuong());
//                        Query giamSoLuongSach=session.createQuery("update Sach set soLuong=soLuong-:soLuong where maSach=:maSach");
//                        giamSoLuongSach.setParameter("maSach", sach.getMaSach());
//                        giamSoLuongSach.setParameter("soLuong", sach.getSoLuong());
//                        giamSoLuongSach.executeUpdate();
                        Sach sachPersistent=session.get(Sach.class, sach.getMaSach());
                        if(sachPersistent.getSoLuong    ()-sach.getSoLuong()<0)
                            throw new SQLException("Sách "+sach.getTenSach()+" không đủ số lượng");
                        else{
                            sachPersistent.setSoLuong(sachPersistent.getSoLuong()-sach.getSoLuong());
                        }
                        session.save(ctpm);
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
