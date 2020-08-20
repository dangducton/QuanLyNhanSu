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
@Table(name = "dm_quanhe")
public class DmQuanhe implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	@Basic(optional = false)
	@Column(name = "tenquanhe")
	private String tenquanhe;
	@Column(name = "ghichu")
	private String ghichu;
	@Basic(optional = false)
	@Column(name = "status")
	private int status;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idQuanhe")
	private Collection<NvQuanhegiadinh> nvQuanhegiadinhCollection;

	public DmQuanhe() {
	}

	public DmQuanhe(Integer id) {
		this.id = id;
	}

	public DmQuanhe(Integer id, String tenquanhe, int status) {
		this.id = id;
		this.tenquanhe = tenquanhe;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTenquanhe() {
		return tenquanhe;
	}

	public void setTenquanhe(String tenquanhe) {
		this.tenquanhe = tenquanhe;
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
	public Collection<NvQuanhegiadinh> getNvQuanhegiadinhCollection() {
		return nvQuanhegiadinhCollection;
	}

	public void setNvQuanhegiadinhCollection(Collection<NvQuanhegiadinh> nvQuanhegiadinhCollection) {
		this.nvQuanhegiadinhCollection = nvQuanhegiadinhCollection;
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
		if (!(object instanceof DmQuanhe)) {
			return false;
		}
		DmQuanhe other = (DmQuanhe) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.dangmailinh.entities.DmQuanhe[ id=" + id + " ]";
	}

}
