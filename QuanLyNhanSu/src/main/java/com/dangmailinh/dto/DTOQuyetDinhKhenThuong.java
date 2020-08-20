package com.dangmailinh.dto;

import java.util.Date;

public class DTOQuyetDinhKhenThuong {
	Integer idNhanVien;
	String manhanvien;
	String hoten;
	String diachi;
	Date ngaysinh;
	int gioitinh;
	int solankhenthuong;
	int namkhenthuong;
	String chucvu;
	String phongban;
	double luongcoban;
	String trinhdohocvan;

	
	public Integer getIdNhanVien() {
		return idNhanVien;
	}

	public void setIdNhanVien(Integer idNhanVien) {
		this.idNhanVien = idNhanVien;
	}

	public String getManhanvien() {
		return manhanvien;
	}

	public void setManhanvien(String manhanvien) {
		this.manhanvien = manhanvien;
	}

	public int getSolankhenthuong() {
		return solankhenthuong;
	}

	public void setSolankhenthuong(int solankhenthuong) {
		this.solankhenthuong = solankhenthuong;
	}

	public int getNamkhenthuong() {
		return namkhenthuong;
	}

	public void setNamkhenthuong(int namkhenthuong) {
		this.namkhenthuong = namkhenthuong;
	}

	public String getHoten() {
		return hoten;
	}

	public void setHoten(String hoten) {
		this.hoten = hoten;
	}

	public String getDiachi() {
		return diachi;
	}

	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}

	public Date getNgaysinh() {
		return ngaysinh;
	}

	public void setNgaysinh(Date ngaysinh) {
		this.ngaysinh = ngaysinh;
	}

	public int getGioitinh() {
		return gioitinh;
	}

	public void setGioitinh(int gioitinh) {
		this.gioitinh = gioitinh;
	}

	public String getChucvu() {
		return chucvu;
	}

	public void setChucvu(String chucvu) {
		this.chucvu = chucvu;
	}

	public String getPhongban() {
		return phongban;
	}

	public void setPhongban(String phongban) {
		this.phongban = phongban;
	}

	public double getLuongcoban() {
		return luongcoban;
	}

	public void setLuongcoban(double luongcoban) {
		this.luongcoban = luongcoban;
	}

	public String getTrinhdohocvan() {
		return trinhdohocvan;
	}

	public void setTrinhdohocvan(String trinhdohocvan) {
		this.trinhdohocvan = trinhdohocvan;
	}

	public DTOQuyetDinhKhenThuong() {
	}

	public DTOQuyetDinhKhenThuong(Integer idNhanVien,String manhanvien, String hoten, String diachi, Date ngaysinh, int gioitinh,
			int solankhenthuong, int namkhenthuong, String chucvu, String phongban, double luongcoban,
			String trinhdohocvan) {
		super();
		this.idNhanVien = idNhanVien;
		this.manhanvien = manhanvien;
		this.hoten = hoten;
		this.diachi = diachi;
		this.ngaysinh = ngaysinh;
		this.gioitinh = gioitinh;
		this.solankhenthuong = solankhenthuong;
		this.namkhenthuong = namkhenthuong;
		this.chucvu = chucvu;
		this.phongban = phongban;
		this.luongcoban = luongcoban;
		this.trinhdohocvan = trinhdohocvan;
	}



}
