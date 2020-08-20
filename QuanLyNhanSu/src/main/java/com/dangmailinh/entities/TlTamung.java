package com.dangmailinh.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tl_tamung")
public class TlTamung implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "ngaytamung")
    @Temporal(TemporalType.DATE)
    private Date ngaytamung;
    @Basic(optional = false)
    @Column(name = "sotien")
    private double sotien;
    @Basic(optional = false)
    @Column(name = "lydo")
    private String lydo;
    @Column(name = "ghichu")
    private String ghichu;
    @Basic(optional = false)
    @Column(name = "status")
    private int status;
    @JoinColumn(name = "id_nhanvien", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private NvNhanvien idNhanvien;

    public TlTamung() {
    }

    public TlTamung(Integer id) {
        this.id = id;
    }

    public TlTamung(Integer id, Date ngaytamung, double sotien, String lydo, int status) {
        this.id = id;
        this.ngaytamung = ngaytamung;
        this.sotien = sotien;
        this.lydo = lydo;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getNgaytamung() {
        return ngaytamung;
    }

    public void setNgaytamung(Date ngaytamung) {
        this.ngaytamung = ngaytamung;
    }

    public double getSotien() {
        return sotien;
    }

    public void setSotien(double sotien) {
        this.sotien = sotien;
    }

    public String getLydo() {
        return lydo;
    }

    public void setLydo(String lydo) {
        this.lydo = lydo;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public NvNhanvien getIdNhanvien() {
        return idNhanvien;
    }

    public void setIdNhanvien(NvNhanvien idNhanvien) {
        this.idNhanvien = idNhanvien;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TlTamung)) {
            return false;
        }
        TlTamung other = (TlTamung) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dangmailinh.entities.TlTamung[ id=" + id + " ]";
    }
    
}
