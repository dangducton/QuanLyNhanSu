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
@Table(name = "dm_chucdanh")
public class DmChucdanh implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	@Basic(optional = false)
	@Column(name = "tenchucdanh")
	private String tenchucdanh;
	@Basic(optional = false)
	@Column(name = "hesochucvu")
	private float hesochucvu;
	@Basic(optional = false)
	@Column(name = "hesotrachnhiem")
	private float hesotrachnhiem;
	@Basic(optional = false)
	@Column(name = "luongcoban")
	private double luongcoban;
	@Column(name = "ghichu")
	private String ghichu;
	@Basic(optional = false)
	@Column(name = "status")
	private int status;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idChucdanh")
	private Collection<NvNhanvien> nvNhanvienCollection;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idChucdanh")
	private Collection<NvQuyetdinhthangchuc> nvQuyetdinhthangchucCollection;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idChucdanh")
	private Collection<DmChitietthuongngayle> dmChitietthuongngayleCollection;

	public DmChucdanh() {
	}

	public DmChucdanh(Integer id) {
		this.id = id;
	}

	public DmChucdanh(Integer id, String tenchucdanh, float hesochucvu, float hesotrachnhiem, double luongcoban,
			int status) {
		this.id = id;
		this.tenchucdanh = tenchucdanh;
		this.hesochucvu = hesochucvu;
		this.hesotrachnhiem = hesotrachnhiem;
		this.luongcoban = luongcoban;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTenchucdanh() {
		return tenchucdanh;
	}

	public void setTenchucdanh(String tenchucdanh) {
		this.tenchucdanh = tenchucdanh;
	}

	public float getHesochucvu() {
		return hesochucvu;
	}

	public void setHesochucvu(float hesochucvu) {
		this.hesochucvu = hesochucvu;
	}

	public float getHesotrachnhiem() {
		return hesotrachnhiem;
	}

	public void setHesotrachnhiem(float hesotrachnhiem) {
		this.hesotrachnhiem = hesotrachnhiem;
	}

	public double getLuongcoban() {
		return luongcoban;
	}

	public void setLuongcoban(double luongcoban) {
		this.luongcoban = luongcoban;
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
	public Collection<NvQuyetdinhthangchuc> getNvQuyetdinhthangchucCollection() {
		return nvQuyetdinhthangchucCollection;
	}

	public void setNvQuyetdinhthangchucCollection(Collection<NvQuyetdinhthangchuc> nvQuyetdinhthangchucCollection) {
		this.nvQuyetdinhthangchucCollection = nvQuyetdinhthangchucCollection;
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
		if (!(object instanceof DmChucdanh)) {
			return false;
		}
		DmChucdanh other = (DmChucdanh) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.dangmailinh.entities.DmChucdanh[ id=" + id + " ]";
	}

}
