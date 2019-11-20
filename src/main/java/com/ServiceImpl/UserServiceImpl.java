/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ServiceImpl;

import com.DAO.UserDAO;
import com.Service.UserService;
import com.model.NguoiDung;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vital
 */
@Service
@Transactional
public class UserServiceImpl implements UserService{
    @Autowired
    UserDAO userDAOImpl;
    @Override
    public boolean login(HttpServletRequest rq,String email,String password) {
        NguoiDung nguoiDung= userDAOImpl.getUserByEmail(email);
        if(nguoiDung != null){
            if(nguoiDung.getEmail().equals(email) && nguoiDung.getMatKhau().equals(password))
            {
                rq.getSession().setAttribute("nguoiDung", nguoiDung);
                return true;
            }
        }
        else{
            return false;
        }
        return false;
    }

    @Override
    public boolean signUp(NguoiDung nguoiDung) {
        return  userDAOImpl.signUp(nguoiDung);
    }

    @Override
    public boolean logOut() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean datMuon(HttpServletRequest req) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public NguoiDung getUserByEmail(String email) {
       return userDAOImpl.getUserByEmail(email);
    }
}
