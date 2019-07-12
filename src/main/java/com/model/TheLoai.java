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
 * TheLoai generated by hbm2java
 */
@Entity
@Table(name="theLoai"
    ,schema="dbo"
    ,catalog="Libary"
)
public class TheLoai  implements java.io.Serializable {


     private int maTheLoai;
     private DanhMuc danhMuc;
     private String tenTheLoai;
     private String url;
     private Set<Sach> saches = new HashSet<Sach>(0);

    public TheLoai() {
    }

	
    public TheLoai(int maTheLoai) {
        this.maTheLoai = maTheLoai;
    }
    public TheLoai(int maTheLoai, DanhMuc danhMuc, String tenTheLoai, String url, Set<Sach> saches) {
       this.maTheLoai = maTheLoai;
       this.danhMuc = danhMuc;
       this.tenTheLoai = tenTheLoai;
       this.url = url;
       this.saches = saches;
    }
   
     @Id 

    
    @Column(name="maTheLoai", unique=true, nullable=false)
    public int getMaTheLoai() {
        return this.maTheLoai;
    }
    
    public void setMaTheLoai(int maTheLoai) {
        this.maTheLoai = maTheLoai;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_DanhMuc")
    public DanhMuc getDanhMuc() {
        return this.danhMuc;
    }
    
    public void setDanhMuc(DanhMuc danhMuc) {
        this.danhMuc = danhMuc;
    }

    
    @Column(name="tenTheLoai", length=30)
    public String getTenTheLoai() {
        return this.tenTheLoai;
    }
    
    public void setTenTheLoai(String tenTheLoai) {
        this.tenTheLoai = tenTheLoai;
    }

    
    @Column(name="url", length=50)
    public String getUrl() {
        return this.url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="theLoai")
    public Set<Sach> getSaches() {
        return this.saches;
    }
    
    public void setSaches(Set<Sach> saches) {
        this.saches = saches;
    }




}


