package com.dangmailinh.dto;

import java.util.Date;

public class QuyetDinhChamDutHopDongDTO {
	private Integer id;
	private String tenquyetdinh;
	private String noidung;
	private Date ngayhieuluc;
	private String ghichu;

	public String getGhichu() {
		return ghichu;
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

	public QuyetDinhChamDutHopDongDTO() {
	}

	public QuyetDinhChamDutHopDongDTO(Integer id, String tenquyetdinh, String ghichu, String noidung,
			Date ngayhieuluc) {
		super();
		this.id = id;
		this.ghichu = ghichu;
		this.tenquyetdinh = tenquyetdinh;
		this.noidung = noidung;
		this.ngayhieuluc = ngayhieuluc;
	}

}
