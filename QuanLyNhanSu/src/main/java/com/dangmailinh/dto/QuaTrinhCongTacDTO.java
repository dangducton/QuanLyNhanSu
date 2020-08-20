package com.dangmailinh.dto;

import java.util.Date;

public class QuaTrinhCongTacDTO {
	private Integer id;
	private String ghichu;
	private Date tungay;
	private Date denngay;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGhichu() {
		return ghichu;
	}

	public void setGhichu(String ghichu) {
		this.ghichu = ghichu;
	}

	public Date getTungay() {
		return tungay;
	}

	public void setTungay(Date tungay) {
		this.tungay = tungay;
	}

	public Date getDenngay() {
		return denngay;
	}

	public void setDenngay(Date denngay) {
		this.denngay = denngay;
	}

	public QuaTrinhCongTacDTO() {

	}

	public QuaTrinhCongTacDTO(Integer id,String ghichu, Date tungay, Date denngay) {
		super();
		this.id = id;
		this.ghichu = ghichu;
		this.tungay = tungay;
		this.denngay = denngay;
	}

}
