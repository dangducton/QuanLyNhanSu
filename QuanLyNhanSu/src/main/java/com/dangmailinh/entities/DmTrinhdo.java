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
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "dm_trinhdo")
public class DmTrinhdo implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	@Basic(optional = false)
	@Column(name = "tentrinhdo")
	private String tentrinhdo;
	@Basic(optional = false)
	@Column(name = "hesochuyenmon")
	private float hesochuyenmon;
	@Column(name = "ghichu")
	private String ghichu;
	@Basic(optional = false)
	@Column(name = "status")
	private int status;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idTrinhdo")
	private Collection<NvNhanvien> nvNhanvienCollection;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idTrinhdo")
	private Collection<NvQuyetdinhnanghesochuyenmon> nvQuyetdinhnanghesochuyenmonCollection;

	public DmTrinhdo() {
	}

	public DmTrinhdo(Integer id) {
		this.id = id;
	}

	public DmTrinhdo(Integer id, String tentrinhdo, float hesochuyenmon, int status) {
		this.id = id;
		this.tentrinhdo = tentrinhdo;
		this.hesochuyenmon = hesochuyenmon;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTentrinhdo() {
		return tentrinhdo;
	}

	public void setTentrinhdo(String tentrinhdo) {
		this.tentrinhdo = tentrinhdo;
	}

	public float getHesochuyenmon() {
		return hesochuyenmon;
	}

	public void setHesochuyenmon(float hesochuyenmon) {
		this.hesochuyenmon = hesochuyenmon;
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

	@XmlTransient
	public Collection<NvNhanvien> getNvNhanvienCollection() {
		return nvNhanvienCollection;
	}

	public void setNvNhanvienCollection(Collection<NvNhanvien> nvNhanvienCollection) {
		this.nvNhanvienCollection = nvNhanvienCollection;
	}

	@XmlTransient
	public Collection<NvQuyetdinhnanghesochuyenmon> getNvQuyetdinhnanghesochuyenmonCollection() {
		return nvQuyetdinhnanghesochuyenmonCollection;
	}

	public void setNvQuyetdinhnanghesochuyenmonCollection(
			Collection<NvQuyetdinhnanghesochuyenmon> nvQuyetdinhnanghesochuyenmonCollection) {
		this.nvQuyetdinhnanghesochuyenmonCollection = nvQuyetdinhnanghesochuyenmonCollection;
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
		if (!(object instanceof DmTrinhdo)) {
			return false;
		}
		DmTrinhdo other = (DmTrinhdo) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.dangmailinh.entities.DmTrinhdo[ id=" + id + " ]";
	}

}
