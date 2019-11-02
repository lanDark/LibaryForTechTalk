/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ServiceImpl;

import com.DAO.UserDAO;
import com.Service.UserService;
import com.model.Cart;
import com.model.NguoiDung;
import com.securityImpl.CustomUser;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
        NguoiDung nguoiDung= userDAOImpl.login(email);
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
    public boolean signIn() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean logOut() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean datMuon(HttpServletRequest req) throws Exception {
        ArrayList<Cart> cartItems = (ArrayList<Cart>) req.getSession().getAttribute("cartItems");
        Object principal= SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(cartItems.size()>0)
        {
            if( userDAOImpl.datMuon(cartItems,(CustomUser)principal) ){
                req.getSession().removeAttribute("cartItems");
                return true;
            }
        }
        return false;
    }
}
