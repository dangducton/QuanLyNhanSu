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
@Table(name = "dm_chitietthuongngayle")
public class DmChitietthuongngayle implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	@Basic(optional = false)
	@Column(name = "mucthuong")
	private float mucthuong;
	@Column(name = "ghichu")
	private String ghichu;
	@Basic(optional = false)
	@Column(name = "status")
	private int status;
	@JoinColumn(name = "id_chucdanh", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private DmChucdanh idChucdanh;
	@JoinColumn(name = "id_thuongngayle", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private DmThuongngayle idThuongngayle;

	public DmChitietthuongngayle() {
	}

	public DmChitietthuongngayle(Integer id) {
		this.id = id;
	}

	public DmChitietthuongngayle(Integer id, float mucthuong, int status) {
		this.id = id;
		this.mucthuong = mucthuong;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public float getMucthuong() {
		return mucthuong;
	}

	public void setMucthuong(float mucthuong) {
		this.mucthuong = mucthuong;
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

	public DmChucdanh getIdChucdanh() {
		return idChucdanh;
	}

	public void setIdChucdanh(DmChucdanh idChucdanh) {
		this.idChucdanh = idChucdanh;
	}

	public DmThuongngayle getIdThuongngayle() {
		return idThuongngayle;
	}

	public void setIdThuongngayle(DmThuongngayle idThuongngayle) {
		this.idThuongngayle = idThuongngayle;
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
		if (!(object instanceof DmChitietthuongngayle)) {
			return false;
		}
		DmChitietthuongngayle other = (DmChitietthuongngayle) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.dangmailinh.entities.DmChitietthuongngayle[ id=" + id + " ]";
	}

}
