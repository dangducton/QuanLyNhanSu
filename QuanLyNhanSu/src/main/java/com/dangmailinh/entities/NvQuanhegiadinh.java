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
@Table(name = "nv_quanhegiadinh")
public class NvQuanhegiadinh implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	@Basic(optional = false)
	@Column(name = "hoten")
	private String hoten;
	@Basic(optional = false)
	@Column(name = "nghenghiep")
	private String nghenghiep;
	@Basic(optional = false)
	@Column(name = "diachi")
	private String diachi;
	@Column(name = "ghichu")
	private String ghichu;
	@Basic(optional = false)
	@Column(name = "status")
	private int status;
	@JoinColumn(name = "id_quanhe", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private DmQuanhe idQuanhe;
	@JoinColumn(name = "id_nhanvien", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private NvNhanvien idNhanvien;

	public NvQuanhegiadinh() {
	}

	public NvQuanhegiadinh(Integer id) {
		this.id = id;
	}

	public NvQuanhegiadinh(Integer id, String hoten, String nghenghiep, String diachi, int status) {
		this.id = id;
		this.hoten = hoten;
		this.nghenghiep = nghenghiep;
		this.diachi = diachi;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getHoten() {
		return hoten;
	}

	public void setHoten(String hoten) {
		this.hoten = hoten;
	}

	public String getNghenghiep() {
		return nghenghiep;
	}

	public void setNghenghiep(String nghenghiep) {
		this.nghenghiep = nghenghiep;
	}

	public String getDiachi() {
		return diachi;
	}

	public void setDiachi(String diachi) {
		this.diachi = diachi;
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

	public DmQuanhe getIdQuanhe() {
		return idQuanhe;
	}

	public void setIdQuanhe(DmQuanhe idQuanhe) {
		this.idQuanhe = idQuanhe;
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
		if (!(object instanceof NvQuanhegiadinh)) {
			return false;
		}
		NvQuanhegiadinh other = (NvQuanhegiadinh) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.dangmailinh.entities.NvQuanhegiadinh[ id=" + id + " ]";
	}

}
