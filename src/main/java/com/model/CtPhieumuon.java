package com.model;
// Generated Jul 12, 2019 12:14:04 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * CtPhieumuon generated by hbm2java
 */
@Entity
@Table(name="CT_PHIEUMUON"
    ,schema="dbo"
    ,catalog="Libary"
)
public class CtPhieumuon  implements java.io.Serializable {


     private int maCtpm;
     private PhieuMuon phieuMuon;
     private Sach sach;
     private String ghiChu;
     private String ngayTra;
     private Integer tienMuon;
     private Integer tiencoc;
     private Integer tienDu;
     private Set<CtLoi> ctLois = new HashSet<CtLoi>(0);

    public CtPhieumuon() {
    }

	
    public CtPhieumuon(int maCtpm) {
        this.maCtpm = maCtpm;
    }
    public CtPhieumuon(int maCtpm, PhieuMuon phieuMuon, Sach sach, String ghiChu, String ngayTra, Integer tienMuon, Integer tiencoc, Integer tienDu, Set<CtLoi> ctLois) {
       this.maCtpm = maCtpm;
       this.phieuMuon = phieuMuon;
       this.sach = sach;
       this.ghiChu = ghiChu;
       this.ngayTra = ngayTra;
       this.tienMuon = tienMuon;
       this.tiencoc = tiencoc;
       this.tienDu = tienDu;
       this.ctLois = ctLois;
    }
   
     @Id 

    
    @Column(name="maCTPM", unique=true, nullable=false)
    public int getMaCtpm() {
        return this.maCtpm;
    }
    
    public void setMaCtpm(int maCtpm) {
        this.maCtpm = maCtpm;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="maPM")
    public PhieuMuon getPhieuMuon() {
        return this.phieuMuon;
    }
    
    public void setPhieuMuon(PhieuMuon phieuMuon) {
        this.phieuMuon = phieuMuon;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="maSach")
    public Sach getSach() {
        return this.sach;
    }
    
    public void setSach(Sach sach) {
        this.sach = sach;
    }

    
    @Column(name="ghiChu", length=100)
    public String getGhiChu() {
        return this.ghiChu;
    }
    
    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    
    @Column(name="ngayTra", length=10)
    public String getNgayTra() {
        return this.ngayTra;
    }
    
    public void setNgayTra(String ngayTra) {
        this.ngayTra = ngayTra;
    }

    
    @Column(name="tienMuon")
    public Integer getTienMuon() {
        return this.tienMuon;
    }
    
    public void setTienMuon(Integer tienMuon) {
        this.tienMuon = tienMuon;
    }

    
    @Column(name="tiencoc")
    public Integer getTiencoc() {
        return this.tiencoc;
    }
    
    public void setTiencoc(Integer tiencoc) {
        this.tiencoc = tiencoc;
    }

    
    @Column(name="tienDu")
    public Integer getTienDu() {
        return this.tienDu;
    }
    
    public void setTienDu(Integer tienDu) {
        this.tienDu = tienDu;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="ctPhieumuon")
    public Set<CtLoi> getCtLois() {
        return this.ctLois;
    }
    
    public void setCtLois(Set<CtLoi> ctLois) {
        this.ctLois = ctLois;
    }




}


