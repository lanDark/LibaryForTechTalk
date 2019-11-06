/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Validator;

import com.model.NguoiDung;

/**
 *
 * @author vital
 */

public class RegisterForm implements  java.io.Serializable {
    private String email;
    private String matKhau;
    private String matKhau2;
    private String diaChi;
    private String sdt;
    private String hoTen;
    private String cmnd;

    public RegisterForm() {
    }

    public RegisterForm(String email, String matKhau, String matKhau2, String diaChi, String sdt, String hoTen, String cmnd) {
        this.email = email;
        this.matKhau = matKhau;
        this.matKhau2 = matKhau2;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.hoTen = hoTen;
        this.cmnd = cmnd;
    }

    
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getMatKhau2() {
        return matKhau2;
    }

    public void setMatKhau2(String matKhau2) {
        this.matKhau2 = matKhau2;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }
    
    public NguoiDung convertToNguoiDungClass(){
        NguoiDung nguoiDung = new NguoiDung();
        
        nguoiDung.setEmail(this.getEmail());
        nguoiDung.setMatKhau(this.getMatKhau()); 
        nguoiDung.setDiaChi(this.getDiaChi());        
        nguoiDung.setSdt(this.getSdt());                    
        nguoiDung.setHoTen(this.getHoTen());             
        nguoiDung.setCmnd(this.getCmnd());           
        
        return nguoiDung;
    }
}
