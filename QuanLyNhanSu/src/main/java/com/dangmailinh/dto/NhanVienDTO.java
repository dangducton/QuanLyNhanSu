package com.dangmailinh.dto;

import java.util.Date;

public class NhanVienDTO {
	private String hinhanh;

	public String getHinhanh() {
		return hinhanh;
	}

	public void setHinhanh(String hinhanh) {
		this.hinhanh = hinhanh;
	}

	private int ngay;
	private int thang;
	private int nam;
	private String manhanvien;
	private String hovaten;
	private Date ngaysinh;
	private String chucdanh;
	private String noisinh;
	private String quoctich;
	private String choohiennay;
	private String sochungminhnhandan;
	private String noicap;
	private String email;
	private String sodienthoai;
	private String trinhdochuyenmon;
	private String chuyennganh;
	private Double luongcoban;
	private String tinhtranghonnhan;
	private String ghichu;

	public String getManhanvien() {
		return manhanvien;
	}
	
	public int getNgay() {
		return ngay;
	}

	public void setNgay(int ngay) {
		this.ngay = ngay;
	}

	public int getThang() {
		return thang;
	}

	public void setThang(int thang) {
		this.thang = thang;
	}

	public int getNam() {
		return nam;
	}

	public void setNam(int nam) {
		this.nam = nam;
	}

	public void setManhanvien(String manhanvien) {
		this.manhanvien = manhanvien;
	}

	public String getHovaten() {
		return hovaten;
	}

	public void setHovaten(String hovaten) {
		this.hovaten = hovaten;
	}

	public Date getNgaysinh() {
		return ngaysinh;
	}

	public void setNgaysinh(Date ngaysinh) {
		this.ngaysinh = ngaysinh;
	}

	public String getChucdanh() {
		return chucdanh;
	}

	public void setChucdanh(String chucdanh) {
		this.chucdanh = chucdanh;
	}

	public String getNoisinh() {
		return noisinh;
	}

	public void setNoisinh(String noisinh) {
		this.noisinh = noisinh;
	}

	public String getQuoctich() {
		return quoctich;
	}

	public void setQuoctich(String quoctich) {
		this.quoctich = quoctich;
	}

	public String getChoohiennay() {
		return choohiennay;
	}

	public void setChoohiennay(String choohiennay) {
		this.choohiennay = choohiennay;
	}

	public String getSochungminhnhandan() {
		return sochungminhnhandan;
	}

	public void setSochungminhnhandan(String sochungminhnhandan) {
		this.sochungminhnhandan = sochungminhnhandan;
	}

	public String getNoicap() {
		return noicap;
	}

	public void setNoicap(String noicap) {
		this.noicap = noicap;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSodienthoai() {
		return sodienthoai;
	}

	public void setSodienthoai(String sodienthoai) {
		this.sodienthoai = sodienthoai;
	}

	public String getTrinhdochuyenmon() {
		return trinhdochuyenmon;
	}

	public void setTrinhdochuyenmon(String trinhdochuyenmon) {
		this.trinhdochuyenmon = trinhdochuyenmon;
	}

	public String getChuyennganh() {
		return chuyennganh;
	}

	public void setChuyennganh(String chuyennganh) {
		this.chuyennganh = chuyennganh;
	}

	public Double getLuongcoban() {
		return luongcoban;
	}

	public void setLuongcoban(Double luongcoban) {
		this.luongcoban = luongcoban;
	}

	public String getTinhtranghonnhan() {
		return tinhtranghonnhan;
	}

	public void setTinhtranghonnhan(String tinhtranghonnhan) {
		this.tinhtranghonnhan = tinhtranghonnhan;
	}

	public String getGhichu() {
		return ghichu;
	}

	public void setGhichu(String ghichu) {
		this.ghichu = ghichu;
	}

	public NhanVienDTO() {

	}

	public NhanVienDTO(String manhanvien, String hovaten, Date ngaysinh, String chucdanh, String noisinh,
			String quoctich, String choohiennay, String sochungminhnhandan, String noicap, String email,
			String sodienthoai, String trinhdochuyenmon, String chuyennganh, Double luongcoban, String tinhtranghonnhan,
			String ghichu, String hinhanh, int ngay, int thang, int nam) {
		super();
		this.hinhanh = hinhanh;
		this.ngay = ngay;
		this.thang = thang;
		this.nam = nam;
		this.manhanvien = manhanvien;
		this.hovaten = hovaten;
		this.ngaysinh = ngaysinh;
		this.chucdanh = chucdanh;
		this.noisinh = noisinh;
		this.quoctich = quoctich;
		this.choohiennay = choohiennay;
		this.sochungminhnhandan = sochungminhnhandan;
		this.noicap = noicap;
		this.email = email;
		this.sodienthoai = sodienthoai;
		this.trinhdochuyenmon = trinhdochuyenmon;
		this.chuyennganh = chuyennganh;
		this.luongcoban = luongcoban;
		this.tinhtranghonnhan = tinhtranghonnhan;
		this.ghichu = ghichu;
	}

}
