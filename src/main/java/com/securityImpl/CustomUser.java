/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.securityImpl;

import java.util.Collection;
import org.apache.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author vital
 */
public class CustomUser extends User{
    private final String maNguoiDung;
    private final String soDienThoai;
    private final String hoTen; 
    public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities,String hoTen,String maNguoiDung,String soDienThoai) {
        super(username, password, authorities);
        this.maNguoiDung=maNguoiDung;
        this.hoTen=hoTen;
        this.soDienThoai=soDienThoai;
    }

    public String getMaNguoiDung() {
        return maNguoiDung;
    }
    
    public String getSoDienThoai() {
        return soDienThoai;
    }

    public String getHoTen() {
        return hoTen;
    }


    
}
