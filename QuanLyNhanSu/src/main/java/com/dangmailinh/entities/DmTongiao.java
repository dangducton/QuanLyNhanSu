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
@Table(name = "dm_tongiao")
public class DmTongiao implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	@Basic(optional = false)
	@Column(name = "tentongiao")
	private String tentongiao;
	@Column(name = "ghichu")
	private String ghichu;
	@Basic(optional = false)
	@Column(name = "status")
	private int status;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idTongiao")
	private Collection<NvNhanvien> nvNhanvienCollection;

	public DmTongiao() {
	}

	public DmTongiao(Integer id) {
		this.id = id;
	}

	public DmTongiao(Integer id, String tentongiao, int status) {
		this.id = id;
		this.tentongiao = tentongiao;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTentongiao() {
		return tentongiao;
	}

	public void setTentongiao(String tentongiao) {
		this.tentongiao = tentongiao;
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
		if (!(object instanceof DmTongiao)) {
			return false;
		}
		DmTongiao other = (DmTongiao) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.dangmailinh.entities.DmTongiao[ id=" + id + " ]";
	}

}
