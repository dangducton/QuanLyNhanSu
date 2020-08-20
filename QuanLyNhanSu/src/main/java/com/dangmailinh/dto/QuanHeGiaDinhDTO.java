package com.dangmailinh.dto;

public class QuanHeGiaDinhDTO {
	private int id;
	private String nghenghiep;
	private String hoten;
	private String ghichu;
	private String diachi;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNghenghiep() {
		return nghenghiep;
	}

	public void setNghenghiep(String nghenghiep) {
		this.nghenghiep = nghenghiep;
	}

	public String getHoten() {
		return hoten;
	}

	public void setHoten(String hoten) {
		this.hoten = hoten;
	}

	public String getGhichu() {
		return ghichu;
	}

	public void setGhichu(String ghichu) {
		this.ghichu = ghichu;
	}

	public String getDiachi() {
		return diachi;
	}

	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}

	public QuanHeGiaDinhDTO() {

	}

	public QuanHeGiaDinhDTO(int id, String nghenghiep, String hoten, String ghichu, String diachi) {
		super();
		this.id = id;
		this.nghenghiep = nghenghiep;
		this.hoten = hoten;
		this.ghichu = ghichu;
		this.diachi = diachi;
	}

}
