package com.dangmailinh.dto;

import java.io.Serializable;

public class UpdateChiTietThuongLeDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private float mucthuong;
	private String ghichu;
	private int status;
	private Integer idChucdanh;
	private Integer idThuongngayle;

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

	public Integer getIdChucdanh() {
		return idChucdanh;
	}

	public void setIdChucdanh(Integer idChucdanh) {
		this.idChucdanh = idChucdanh;
	}

	public Integer getIdThuongngayle() {
		return idThuongngayle;
	}

	public void setIdThuongngayle(Integer idThuongngayle) {
		this.idThuongngayle = idThuongngayle;
	}

	public UpdateChiTietThuongLeDTO() {
		super();
	}

	public UpdateChiTietThuongLeDTO(Integer id, float mucthuong, String ghichu, int status, Integer idChucdanh,
			Integer idThuongngayle) {
		super();
		this.id = id;
		this.mucthuong = mucthuong;
		this.ghichu = ghichu;
		this.status = status;
		this.idChucdanh = idChucdanh;
		this.idThuongngayle = idThuongngayle;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ghichu == null) ? 0 : ghichu.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((idChucdanh == null) ? 0 : idChucdanh.hashCode());
		result = prime * result + ((idThuongngayle == null) ? 0 : idThuongngayle.hashCode());
		result = prime * result + Float.floatToIntBits(mucthuong);
		result = prime * result + status;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UpdateChiTietThuongLeDTO other = (UpdateChiTietThuongLeDTO) obj;
		if (ghichu == null) {
			if (other.ghichu != null)
				return false;
		} else if (!ghichu.equals(other.ghichu))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idChucdanh == null) {
			if (other.idChucdanh != null)
				return false;
		} else if (!idChucdanh.equals(other.idChucdanh))
			return false;
		if (idThuongngayle == null) {
			if (other.idThuongngayle != null)
				return false;
		} else if (!idThuongngayle.equals(other.idThuongngayle))
			return false;
		if (Float.floatToIntBits(mucthuong) != Float.floatToIntBits(other.mucthuong))
			return false;
		if (status != other.status)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UpdateChiTietThuongLeDTO [id=" + id + ", mucthuong=" + mucthuong + ", ghichu=" + ghichu + ", status="
				+ status + ", idChucdanh=" + idChucdanh + ", idThuongngayle=" + idThuongngayle + "]";
	}

}
