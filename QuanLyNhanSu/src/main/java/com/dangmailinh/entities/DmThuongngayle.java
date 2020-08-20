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
@Table(name = "dm_thuongngayle")
public class DmThuongngayle implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	@Basic(optional = false)
	@Column(name = "tenngayle")
	private String tenngayle;
	@Column(name = "ghichu")
	private String ghichu;
	@Basic(optional = false)
	@Column(name = "status")
	private int status;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idThuongngayle")
	private Collection<DmChitietthuongngayle> dmChitietthuongngayleCollection;

	public DmThuongngayle() {
	}

	public DmThuongngayle(Integer id) {
		this.id = id;
	}

	public DmThuongngayle(Integer id, String tenngayle, int status) {
		this.id = id;
		this.tenngayle = tenngayle;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTenngayle() {
		return tenngayle;
	}

	public void setTenngayle(String tenngayle) {
		this.tenngayle = tenngayle;
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
	public Collection<DmChitietthuongngayle> getDmChitietthuongngayleCollection() {
		return dmChitietthuongngayleCollection;
	}

	public void setDmChitietthuongngayleCollection(Collection<DmChitietthuongngayle> dmChitietthuongngayleCollection) {
		this.dmChitietthuongngayleCollection = dmChitietthuongngayleCollection;
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
		if (!(object instanceof DmThuongngayle)) {
			return false;
		}
		DmThuongngayle other = (DmThuongngayle) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.dangmailinh.entities.DmThuongngayle[ id=" + id + " ]";
	}

}
