package com.model;
// Generated Jul 12, 2019 12:14:04 PM by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Phat generated by hbm2java
 */
@Entity
@Table(name="phat"
    ,schema="dbo"
    ,catalog="Libary"
)
public class Phat  implements java.io.Serializable {


     private int maPhat;
     private Loi loi;
     private String hinhThucPhat;

    public Phat() {
    }

	
    public Phat(int maPhat) {
        this.maPhat = maPhat;
    }
    public Phat(int maPhat, Loi loi, String hinhThucPhat) {
       this.maPhat = maPhat;
       this.loi = loi;
       this.hinhThucPhat = hinhThucPhat;
    }
   
     @Id 

    
    @Column(name="maPhat", unique=true, nullable=false)
    public int getMaPhat() {
        return this.maPhat;
    }
    
    public void setMaPhat(int maPhat) {
        this.maPhat = maPhat;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="maLoi")
    public Loi getLoi() {
        return this.loi;
    }
    
    public void setLoi(Loi loi) {
        this.loi = loi;
    }

    
    @Column(name="HinhThucPhat", length=50)
    public String getHinhThucPhat() {
        return this.hinhThucPhat;
    }
    
    public void setHinhThucPhat(String hinhThucPhat) {
        this.hinhThucPhat = hinhThucPhat;
    }




}


