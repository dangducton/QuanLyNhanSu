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
@Table(name = "dm_dantoc")
public class DmDantoc implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	@Basic(optional = false)
	@Column(name = "tendantoc")
	private String tendantoc;
	@Column(name = "ghichu")
	private String ghichu;
	@Basic(optional = false)
	@Column(name = "status")
	private int status;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idDantoc")
	private Collection<NvNhanvien> nvNhanvienCollection;

	public DmDantoc() {
	}

	public DmDantoc(Integer id) {
		this.id = id;
	}

	public DmDantoc(Integer id, String tendantoc, int status) {
		this.id = id;
		this.tendantoc = tendantoc;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTendantoc() {
		return tendantoc;
	}

	public void setTendantoc(String tendantoc) {
		this.tendantoc = tendantoc;
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
		if (!(object instanceof DmDantoc)) {
			return false;
		}
		DmDantoc other = (DmDantoc) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.dangmailinh.entities.DmDantoc[ id=" + id + " ]";
	}

}
