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
@Table(name = "nv_quyetdinhkyluat")
public class NvQuyetdinhkyluat implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "tenquyetdinh")
    private String tenquyetdinh;
    @Basic(optional = false)
    @Column(name = "ngayquyetdinh")
    @Temporal(TemporalType.DATE)
    private Date ngayquyetdinh;
    @Basic(optional = false)
    @Column(name = "ngayhieuluc")
    @Temporal(TemporalType.DATE)
    private Date ngayhieuluc;
    @Basic(optional = false)
    @Column(name = "noidung")
    private String noidung;
    @Basic(optional = false)
    @Column(name = "tienphat")
    private double tienphat;
    @Column(name = "ghichu")
    private String ghichu;
    @Basic(optional = false)
    @Column(name = "status")
    private int status;
    @JoinColumn(name = "id_kyluat", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private DmKyluat idKyluat;
    @JoinColumn(name = "id_nhanvien", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private NvNhanvien idNhanvien;

    public NvQuyetdinhkyluat() {
    }

    public NvQuyetdinhkyluat(Integer id) {
        this.id = id;
    }

    public NvQuyetdinhkyluat(Integer id, String tenquyetdinh, Date ngayquyetdinh, Date ngayhieuluc, String noidung, double tienphat, int status) {
        this.id = id;
        this.tenquyetdinh = tenquyetdinh;
        this.ngayquyetdinh = ngayquyetdinh;
        this.ngayhieuluc = ngayhieuluc;
        this.noidung = noidung;
        this.tienphat = tienphat;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTenquyetdinh() {
        return tenquyetdinh;
    }

    public void setTenquyetdinh(String tenquyetdinh) {
        this.tenquyetdinh = tenquyetdinh;
    }

    public Date getNgayquyetdinh() {
        return ngayquyetdinh;
    }

    public void setNgayquyetdinh(Date ngayquyetdinh) {
        this.ngayquyetdinh = ngayquyetdinh;
    }

    public Date getNgayhieuluc() {
        return ngayhieuluc;
    }

    public void setNgayhieuluc(Date ngayhieuluc) {
        this.ngayhieuluc = ngayhieuluc;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public double getTienphat() {
        return tienphat;
    }

    public void setTienphat(double tienphat) {
        this.tienphat = tienphat;
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

    public DmKyluat getIdKyluat() {
        return idKyluat;
    }

    public void setIdKyluat(DmKyluat idKyluat) {
        this.idKyluat = idKyluat;
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
        if (!(object instanceof NvQuyetdinhkyluat)) {
            return false;
        }
        NvQuyetdinhkyluat other = (NvQuyetdinhkyluat) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dangmailinh.NvQuyetdinhkyluat[ id=" + id + " ]";
    }
    
}
