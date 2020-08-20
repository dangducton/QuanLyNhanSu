package com.dangmailinh.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dm_kyluat")
public class DmKyluat implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	@Basic(optional = false)
	@Column(name = "tenkyluat")
	private String tenkyluat;
	@Basic(optional = false)
	@Column(name = "mucphat")
	private float mucphat;
	@Column(name = "ghichu")
	private String ghichu;
	@Basic(optional = false)
	@Column(name = "status")
	private int status;

	public DmKyluat() {
	}

	public DmKyluat(Integer id) {
		this.id = id;
	}

	public DmKyluat(Integer id, String tenkyluat, float mucphat, int status) {
		this.id = id;
		this.tenkyluat = tenkyluat;
		this.mucphat = mucphat;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTenkyluat() {
		return tenkyluat;
	}

	public void setTenkyluat(String tenkyluat) {
		this.tenkyluat = tenkyluat;
	}

	public float getMucphat() {
		return mucphat;
	}

	public void setMucphat(float mucphat) {
		this.mucphat = mucphat;
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

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof DmKyluat)) {
			return false;
		}
		DmKyluat other = (DmKyluat) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.dangmailinh.entities.DmKyluat[ id=" + id + " ]";
	}

}
