package com.dangmailinh.dto;

public class DmChucDanhDTO {
	private Integer id;
	private String tenchucdanh;
	private float hesochucvu;
	private float hesotrachnhiem;
	private double luongcoban;
	private String ghichu;
	private int status;

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

	public DmChucDanhDTO() {
		super();
	}

	public DmChucDanhDTO(Integer id, String tenchucdanh, float hesochucvu, float hesotrachnhiem, double luongcoban,
			String ghichu, int status) {
		super();
		this.id = id;
		this.tenchucdanh = tenchucdanh;
		this.hesochucvu = hesochucvu;
		this.hesotrachnhiem = hesotrachnhiem;
		this.luongcoban = luongcoban;
		this.ghichu = ghichu;
		this.status = status;
	}
	
}
