package com.model;
// Generated Aug 24, 2019 11:38:04 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * NguoiDung generated by hbm2java
 */
@Entity
@Table(name="nguoiDung"
    ,schema="dbo"
    ,catalog="libary"
    , uniqueConstraints = @UniqueConstraint(columnNames="email") 
)
public class NguoiDung  implements java.io.Serializable {


     private String maNguoiDung;
     private String email;
     private String matKhau;
     private String hoTen;
     private String sdt;
     private String diaChi;
     private String cmnd;
     private Integer tinhTrang;
     private Set<Rules> rules  = new HashSet<Rules>(0);
     private Set<LuotThich> luotThiches = new HashSet<LuotThich>(0);
     private Set<PhieuMuon> phieuMuonsForMaThuThu = new HashSet<PhieuMuon>(0);
     private Set<PhieuMuon> phieuMuonsForMaNguoiDung = new HashSet<PhieuMuon>(0);
     private Set<PhieuMuon> phieuMuonsForNguoiNhanTra = new HashSet<PhieuMuon>(0);
     private Set<PhieuNhap> phieuNhaps = new HashSet<PhieuNhap>(0);

    public NguoiDung() {
    }

	
    public NguoiDung(String maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
    }
    public NguoiDung(String maNguoiDung, Set<Rules> rules, String email, String matKhau, String hoTen, String sdt, String diaChi, String cmnd, Integer tinhTrang, Set<LuotThich> luotThiches, Set<PhieuMuon> phieuMuonsForMaThuThu, Set<PhieuMuon> phieuMuonsForMaNguoiDung, Set<PhieuMuon> phieuMuonsForNguoiNhanTra, Set<PhieuNhap> phieuNhaps) {
       this.maNguoiDung = maNguoiDung;
       this.rules = rules;
       this.email = email;
       this.matKhau = matKhau;
       this.hoTen = hoTen;
       this.sdt = sdt;
       this.diaChi = diaChi;
       this.cmnd = cmnd;
       this.tinhTrang = tinhTrang;
       this.luotThiches = luotThiches;
       this.phieuMuonsForMaThuThu = phieuMuonsForMaThuThu;
       this.phieuMuonsForMaNguoiDung = phieuMuonsForMaNguoiDung;
       this.phieuMuonsForNguoiNhanTra = phieuMuonsForNguoiNhanTra;
       this.phieuNhaps = phieuNhaps;
    }
   
     @Id 

    
    @Column(name="maNguoiDung", unique=true, nullable=false, length=6)
    public String getMaNguoiDung() {
        return this.maNguoiDung;
    }
    
    public void setMaNguoiDung(String maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
    }

    @OneToMany(fetch=FetchType.LAZY,mappedBy = "nguoiDung")
    public Set<Rules> getRules() {
        return this.rules;
    }
    
    public void setRules(Set<Rules> rules) {
        this.rules = rules;
    }

    
    @Column(name="email", unique=true, length=30)
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    
    @Column(name="matKhau", length=60)
    public String getMatKhau() {
        return this.matKhau;
    }
    
    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    
    @Column(name="hoTen", length=50)
    public String getHoTen() {
        return this.hoTen;
    }
    
    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    
    @Column(name="sdt", length=10)
    public String getSdt() {
        return this.sdt;
    }
    
    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    
    @Column(name="diaChi", length=100)
    public String getDiaChi() {
        return this.diaChi;
    }
    
    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    
    @Column(name="CMND", length=9)
    public String getCmnd() {
        return this.cmnd;
    }
    
    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    
    @Column(name="tinhTrang")
    public Integer getTinhTrang() {
        return this.tinhTrang;
    }
    
    public void setTinhTrang(Integer tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="nguoiDung")
    public Set<LuotThich> getLuotThiches() {
        return this.luotThiches;
    }
    
    public void setLuotThiches(Set<LuotThich> luotThiches) {
        this.luotThiches = luotThiches;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="nguoiDungByMaThuThu")
    public Set<PhieuMuon> getPhieuMuonsForMaThuThu() {
        return this.phieuMuonsForMaThuThu;
    }
    
    public void setPhieuMuonsForMaThuThu(Set<PhieuMuon> phieuMuonsForMaThuThu) {
        this.phieuMuonsForMaThuThu = phieuMuonsForMaThuThu;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="nguoiDungByMaNguoiDung")
    public Set<PhieuMuon> getPhieuMuonsForMaNguoiDung() {
        return this.phieuMuonsForMaNguoiDung;
    }
    
    public void setPhieuMuonsForMaNguoiDung(Set<PhieuMuon> phieuMuonsForMaNguoiDung) {
        this.phieuMuonsForMaNguoiDung = phieuMuonsForMaNguoiDung;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="nguoiDungByNguoiNhanTra")
    public Set<PhieuMuon> getPhieuMuonsForNguoiNhanTra() {
        return this.phieuMuonsForNguoiNhanTra;
    }
    
    public void setPhieuMuonsForNguoiNhanTra(Set<PhieuMuon> phieuMuonsForNguoiNhanTra) {
        this.phieuMuonsForNguoiNhanTra = phieuMuonsForNguoiNhanTra;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="nguoiDung")
    public Set<PhieuNhap> getPhieuNhaps() {
        return this.phieuNhaps;
    }
    
    public void setPhieuNhaps(Set<PhieuNhap> phieuNhaps) {
        this.phieuNhaps = phieuNhaps;
    }



    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NguoiDung other = (NguoiDung) obj;
        if (!Objects.equals(this.maNguoiDung, other.maNguoiDung)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.matKhau, other.matKhau)) {
            return false;
        }
        if (!Objects.equals(this.hoTen, other.hoTen)) {
            return false;
        }
        if (!Objects.equals(this.sdt, other.sdt)) {
            return false;
        }
        if (!Objects.equals(this.diaChi, other.diaChi)) {
            return false;
        }
        if (!Objects.equals(this.cmnd, other.cmnd)) {
            return false;
        }
        if (!Objects.equals(this.tinhTrang, other.tinhTrang)) {
            return false;
        }
        if (!Objects.equals(this.rules, other.rules)) {
            return false;
        }
        if (!Objects.equals(this.luotThiches, other.luotThiches)) {
            return false;
        }
        if (!Objects.equals(this.phieuMuonsForMaThuThu, other.phieuMuonsForMaThuThu)) {
            return false;
        }
        if (!Objects.equals(this.phieuMuonsForMaNguoiDung, other.phieuMuonsForMaNguoiDung)) {
            return false;
        }
        if (!Objects.equals(this.phieuMuonsForNguoiNhanTra, other.phieuMuonsForNguoiNhanTra)) {
            return false;
        }
        if (!Objects.equals(this.phieuNhaps, other.phieuNhaps)) {
            return false;
        }
        return true;
    }

  




}


