package com.dangmailinh.entities;


import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "phucap")
public class PhuCap implements Serializable{
	  private static final long serialVersionUID = 1L;
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Basic(optional = false)
	    @Column(name = "id")
	    private Integer id;
	    @Basic(optional = false)
	    @Column(name = "ghichu")
	    private String ghichu;
	    @Basic(optional = false)
	    @Column(name = "thang")
	    private int thang;
	    @Basic(optional = false)
	    @Column(name = "nam")
	    private int nam;
	    @Basic(optional = false)
	    @Column(name = "status")
	    private int status;
	    @JoinColumn(name = "id_phucap", referencedColumnName = "id")
	    @ManyToOne(optional = false)
	    private DmPhucap idPhucap;
	    @JoinColumn(name = "id_nhanvien", referencedColumnName = "id")
	    @ManyToOne(optional = false)
	    private NvNhanvien idNhanvien;

	    public PhuCap() {
	    }

	    public PhuCap(Integer id) {
	        this.id = id;
	    }

	    public PhuCap(Integer id, String ghichu, int thang, int nam, int status) {
	        this.id = id;
	        this.ghichu = ghichu;
	        this.thang = thang;
	        this.nam = nam;
	        this.status = status;
	    }

	    public Integer getId() {
	        return id;
	    }

	    public void setId(Integer id) {
	        this.id = id;
	    }

	    public String getGhichu() {
	        return ghichu;
	    }

	    public void setGhichu(String ghichu) {
	        this.ghichu = ghichu;
	    }

	    public int getThang() {
	        return thang;
	    }

	    public void setThang(int thang) {
	        this.thang = thang;
	    }

	    public int getNam() {
	        return nam;
	    }

	    public void setNam(int nam) {
	        this.nam = nam;
	    }

	    public int getStatus() {
	        return status;
	    }

	    public void setStatus(int status) {
	        this.status = status;
	    }

	    public DmPhucap getIdPhucap() {
	        return idPhucap;
	    }

	    public void setIdPhucap(DmPhucap idPhucap) {
	        this.idPhucap = idPhucap;
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
	        if (!(object instanceof PhuCap)) {
	            return false;
	        }
	        PhuCap other = (PhuCap) object;
	        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
	            return false;
	        }
	        return true;
	    }

	    @Override
	    public String toString() {
	        return "com.dangmailinh.Phucap[ id=" + id + " ]";
	    }
	    
	}
