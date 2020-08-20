package com.dangmailinh.dto;

import java.util.Date;

public class TamUngDTO {
	private Integer id;
	private String ghichu;
	private Date ngaytamung;
	private String lydo;
	private double sotien;

	public String getGhichu() {
		return ghichu;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setGhichu(String ghichu) {
		this.ghichu = ghichu;
	}

	public Date getNgaytamung() {
		return ngaytamung;
	}

	public void setNgaytamung(Date ngaytamung) {
		this.ngaytamung = ngaytamung;
	}

	public String getLydo() {
		return lydo;
	}

	public void setLydo(String lydo) {
		this.lydo = lydo;
	}

	public double getSotien() {
		return sotien;
	}

	public void setSotien(double sotien) {
		this.sotien = sotien;
	}

	public TamUngDTO() {

	}

	public TamUngDTO(String ghichu,Integer id, Date ngaytamung, String lydo, double sotien) {
		super();
		this.id = id;
		this.ghichu = ghichu;
		this.ngaytamung = ngaytamung;
		this.lydo = lydo;
		this.sotien = sotien;
	}

}