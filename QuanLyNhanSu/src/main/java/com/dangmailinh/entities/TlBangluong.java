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
@Table(name = "tl_bangluong")
public class TlBangluong implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	@Column(name = "ghichu")
	private String ghichu;
	@Basic(optional = false)
	@Column(name = "hesochucvu")
	private float hesochucvu;
	@Basic(optional = false)
	@Column(name = "hesochuyenmon")
	private float hesochuyenmon;
	@Basic(optional = false)
	@Column(name = "hesotrachnhiem")
	private float hesotrachnhiem;
	@Basic(optional = false)
	@Column(name = "mucluongcoban")
	private double mucluongcoban;
	@Basic(optional = false)
	@Column(name = "nam")
	private int nam;
	@Basic(optional = false)
	@Column(name = "status")
	private int status;
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	@Column(name = "tamung")
	private Double tamung;
	@Basic(optional = false)
	@Column(name = "thang")
	private int thang;
	@Basic(optional = false)
	@Column(name = "thuclinh")
	private double thuclinh;
	@Basic(optional = false)
	@Column(name = "tienbaohiem")
	private double tienbaohiem;
	@Column(name = "tienphat")
	private Double tienphat;
	@Column(name = "tienthuong")
	private Double tienthuong;
	@Column(name = "tienthuongle")
	private Double tienthuongle;
	@Basic(optional = false)
	@Column(name = "tongheso")
	private float tongheso;
	@Column(name = "tienphucap")
	private Double tienphucap;
	@JoinColumn(name = "id_nhanvien", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private NvNhanvien idNhanvien;

	public TlBangluong() {
	}

	public TlBangluong(Integer id) {
		this.id = id;
	}

	public TlBangluong(Integer id, float hesochucvu, float hesochuyenmon, float hesotrachnhiem, double mucluongcoban,
			int nam, int status, int thang, double thuclinh, double tienbaohiem, float tongheso) {
		this.id = id;
		this.hesochucvu = hesochucvu;
		this.hesochuyenmon = hesochuyenmon;
		this.hesotrachnhiem = hesotrachnhiem;
		this.mucluongcoban = mucluongcoban;
		this.nam = nam;
		this.status = status;
		this.thang = thang;
		this.thuclinh = thuclinh;
		this.tienbaohiem = tienbaohiem;
		this.tongheso = tongheso;
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

	public float getHesochucvu() {
		return hesochucvu;
	}

	public void setHesochucvu(float hesochucvu) {
		this.hesochucvu = hesochucvu;
	}

	public float getHesochuyenmon() {
		return hesochuyenmon;
	}

	public void setHesochuyenmon(float hesochuyenmon) {
		this.hesochuyenmon = hesochuyenmon;
	}

	public float getHesotrachnhiem() {
		return hesotrachnhiem;
	}

	public void setHesotrachnhiem(float hesotrachnhiem) {
		this.hesotrachnhiem = hesotrachnhiem;
	}

	public double getMucluongcoban() {
		return mucluongcoban;
	}

	public void setMucluongcoban(double mucluongcoban) {
		this.mucluongcoban = mucluongcoban;
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

	public Double getTamung() {
		return tamung;
	}

	public void setTamung(Double tamung) {
		this.tamung = tamung;
	}

	public int getThang() {
		return thang;
	}

	public void setThang(int thang) {
		this.thang = thang;
	}

	public double getThuclinh() {
		return thuclinh;
	}

	public void setThuclinh(double thuclinh) {
		this.thuclinh = thuclinh;
	}

	public double getTienbaohiem() {
		return tienbaohiem;
	}

	public void setTienbaohiem(double tienbaohiem) {
		this.tienbaohiem = tienbaohiem;
	}

	public Double getTienphat() {
		return tienphat;
	}

	public void setTienphat(Double tienphat) {
		this.tienphat = tienphat;
	}

	public Double getTienthuong() {
		return tienthuong;
	}

	public void setTienthuong(Double tienthuong) {
		this.tienthuong = tienthuong;
	}

	public Double getTienthuongle() {
		return tienthuongle;
	}

	public void setTienthuongle(Double tienthuongle) {
		this.tienthuongle = tienthuongle;
	}

	public float getTongheso() {
		return tongheso;
	}

	public void setTongheso(float tongheso) {
		this.tongheso = tongheso;
	}

	public Double getTienphucap() {
		return tienphucap;
	}

	public void setTienphucap(Double tienphucap) {
		this.tienphucap = tienphucap;
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
		if (!(object instanceof TlBangluong)) {
			return false;
		}
		TlBangluong other = (TlBangluong) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.dangmailinh.entities.TlBangluong[ id=" + id + " ]";
	}

}
