package com.dangmailinh.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	@Basic(optional = false)
	@Column(name = "tendangnhap")
	private String tendangnhap;
	@Basic(optional = false)
	@Column(name = "matkhau")
	private String matkhau;
	@Basic(optional = false)
	@Column(name = "status")
	private int status;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "user_roles", joinColumns = {
			@JoinColumn(name = "id_user", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "id_role", referencedColumnName = "id") })
	private Collection<Role> roleCollection;
	
	@JoinColumn(name = "id_nhanvien", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private NvNhanvien idNhanvien;

	public User() {
	}

	public User(Integer id) {
		this.id = id;
	}

	public User(Integer id, String tendangnhap, String matkhau, int status) {
		this.id = id;
		this.tendangnhap = tendangnhap;
		this.matkhau = matkhau;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTendangnhap() {
		return tendangnhap;
	}

	public void setTendangnhap(String tendangnhap) {
		this.tendangnhap = tendangnhap;
	}

	public String getMatkhau() {
		return matkhau;
	}

	public void setMatkhau(String matkhau) {
		this.matkhau = matkhau;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@XmlTransient
	public Collection<Role> getRoleCollection() {
		return roleCollection;
	}

	public void setRoleCollection(Collection<Role> roleCollection) {
		this.roleCollection = roleCollection;
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
		if (!(object instanceof User)) {
			return false;
		}
		User other = (User) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.dangmailinh.entities.User[ id=" + id + " ]";
	}

}
