/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DAO;

import com.model.Cart;
import com.model.NguoiDung;
import com.securityImpl.CustomUser;
import java.util.ArrayList;

/**
 *
 * @author vital
 */
public interface UserDAO {
    public NguoiDung login(String email);
    public boolean signIn(NguoiDung nguoiDung);
    public boolean datMuon(ArrayList<Cart> listSach,CustomUser maNguoiDung) throws Exception ;
}
