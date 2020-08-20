package com.dangmailinh.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "dm_phucap")
public class DmPhucap implements Serializable {

	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "ghichu")
    private String ghichu;
    @Basic(optional = false)
    @Column(name = "mucphucap")
    private float mucphucap;
    @Basic(optional = false)
    @Column(name = "status")
    private int status;
    @Basic(optional = false)

    @Column(name = "tenphucap")
    private String tenphucap;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPhucap")
    private Collection<PhuCap> phucapCollection;

    public DmPhucap() {
    }

    public DmPhucap(Integer id) {
        this.id = id;
    }

    public DmPhucap(Integer id, float mucphucap, int status, String tenphucap) {
        this.id = id;
        this.mucphucap = mucphucap;
        this.status = status;
        this.tenphucap = tenphucap;
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

    public float getMucphucap() {
        return mucphucap;
    }

    public void setMucphucap(float mucphucap) {
        this.mucphucap = mucphucap;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTenphucap() {
        return tenphucap;
    }

    public void setTenphucap(String tenphucap) {
        this.tenphucap = tenphucap;
    }

    public Collection<PhuCap> getPhuCapCollection() {
        return phucapCollection;
    }

    public void setPhuCapCollection(Collection<PhuCap> phucapCollection) {
        this.phucapCollection = phucapCollection;
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
        if (!(object instanceof DmPhucap)) {
            return false;
        }
        DmPhucap other = (DmPhucap) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }


    @Override
    public String toString() {
        return "com.dangmailinh.DmPhucap[ id=" + id + " ]";
    }
    
}
