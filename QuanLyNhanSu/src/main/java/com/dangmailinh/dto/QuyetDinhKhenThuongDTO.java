package com.dangmailinh.dto;

import java.util.Date;

public class QuyetDinhKhenThuongDTO {
	private Integer id;
	private String tenquyetdinh;
	private double tienthuong;
	private String noidung;
	private Date ngayhieuluc;
	private String ghichu;

	public String getGhichu() {
		return ghichu;
	}

	public double getTienthuong() {
		return tienthuong;
	}

	public void setTienthuong(double tienthuong) {
		this.tienthuong = tienthuong;
	}

	public void setGhichu(String ghichu) {
		this.ghichu = ghichu;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTenquyetdinh() {
		return tenquyetdinh;
	}

	public void setTenquyetdinh(String tenquyetdinh) {
		this.tenquyetdinh = tenquyetdinh;
	}

	public String getNoidung() {
		return noidung;
	}

	public void setNoidung(String noidung) {
		this.noidung = noidung;
	}

	public Date getNgayhieuluc() {
		return ngayhieuluc;
	}

	public void setNgayhieuluc(Date ngayhieuluc) {
		this.ngayhieuluc = ngayhieuluc;
	}

	public QuyetDinhKhenThuongDTO() {
	}

	public QuyetDinhKhenThuongDTO(Integer id, String tenquyetdinh, String ghichu, String noidung, double tienthuong,
			Date ngayhieuluc) {
		super();
		this.id = id;
		this.tienthuong = tienthuong;
		this.ghichu = ghichu;
		this.tenquyetdinh = tenquyetdinh;
		this.noidung = noidung;
		this.ngayhieuluc = ngayhieuluc;
	}
}
