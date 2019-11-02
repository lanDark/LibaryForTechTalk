/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import com.model.Sach;

/**
 *
 * @author vital
 */
public class Cart implements java.io.Serializable{
    Sach sach;
    int soLuong = 0;
    
    public Cart(){};

    public Cart(Sach sach, int soLuong) {
        this.sach = sach;
        this.soLuong = soLuong;
    }
    
    public Sach getSach() {
        return sach;
    }

    public void setSach(Sach sach) {
        this.sach = sach;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
    
}
