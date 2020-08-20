package com.dangmailinh.dto;

public class NguoiDungSignUpDTO {
	private String tendangnhap;
	private String password;
	private String confirmpassword;

	
	public String getTendangnhap() {
		return tendangnhap;
	}

	public void setTendangnhap(String tendangnhap) {
		this.tendangnhap = tendangnhap;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmpassword() {
		return confirmpassword;
	}

	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}

	public NguoiDungSignUpDTO() {
	}

	public NguoiDungSignUpDTO(String tendangnhap, String password, String confirmpassword) {
		super();
		this.tendangnhap = tendangnhap;
		this.password = password;
		this.confirmpassword = confirmpassword;
	}

}
