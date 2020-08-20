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
@Table(name = "nv_quatrinhcongtac")
public class NvQuatrinhcongtac implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "tungay")
    @Temporal(TemporalType.DATE)
    private Date tungay;
    @Basic(optional = false)
    @Column(name = "denngay")
    @Temporal(TemporalType.DATE)
    private Date denngay;
    @Column(name = "ghichu")
    private String ghichu;
    @Basic(optional = false)
    @Column(name = "status")
    private int status;
    @JoinColumn(name = "id_chucvu", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private DmChucdanh idChucvu;
    @JoinColumn(name = "id_phongban", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private DmPhongban idPhongban;
    @JoinColumn(name = "id_nhanvien", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private NvNhanvien idNhanvien;

    public NvQuatrinhcongtac() {
    }

    public NvQuatrinhcongtac(Integer id) {
        this.id = id;
    }

    public NvQuatrinhcongtac(Integer id, Date tungay, Date denngay, int status) {
        this.id = id;
        this.tungay = tungay;
        this.denngay = denngay;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTungay() {
        return tungay;
    }

    public void setTungay(Date tungay) {
        this.tungay = tungay;
    }

    public Date getDenngay() {
        return denngay;
    }

    public void setDenngay(Date denngay) {
        this.denngay = denngay;
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

    public DmChucdanh getIdChucvu() {
        return idChucvu;
    }

    public void setIdChucvu(DmChucdanh idChucvu) {
        this.idChucvu = idChucvu;
    }

    public DmPhongban getIdPhongban() {
        return idPhongban;
    }

    public void setIdPhongban(DmPhongban idPhongban) {
        this.idPhongban = idPhongban;
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
        if (!(object instanceof NvQuatrinhcongtac)) {
            return false;
        }
        NvQuatrinhcongtac other = (NvQuatrinhcongtac) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dangmailinh.NvQuatrinhcongtac[ id=" + id + " ]";
    }
    
}
