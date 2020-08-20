package com.dangmailinh.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "dm_phongban")
public class DmPhongban implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	@Basic(optional = false)
	@Column(name = "tenphongban")
	private String tenphongban;
	@Basic(optional = false)
	@Column(name = "ngaythanhlap")
	@Temporal(TemporalType.DATE)
	private Date ngaythanhlap;
	@Column(name = "ghichu")
	private String ghichu;
	@Basic(optional = false)
	@Column(name = "status")
	private int status;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idPhongban")
	private Collection<NvNhanvien> nvNhanvienCollection;
	public DmPhongban() {
	}

	public DmPhongban(Integer id) {
		this.id = id;
	}

	public DmPhongban(Integer id, String tenphongban, Date ngaythanhlap, int status) {
		this.id = id;
		this.tenphongban = tenphongban;
		this.ngaythanhlap = ngaythanhlap;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTenphongban() {
		return tenphongban;
	}

	public void setTenphongban(String tenphongban) {
		this.tenphongban = tenphongban;
	}

	public Date getNgaythanhlap() {
		return ngaythanhlap;
	}

	public void setNgaythanhlap(Date ngaythanhlap) {
		this.ngaythanhlap = ngaythanhlap;
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

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof DmPhongban)) {
			return false;
		}
		DmPhongban other = (DmPhongban) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.dangmailinh.entities.DmPhongban[ id=" + id + " ]";
	}

}
