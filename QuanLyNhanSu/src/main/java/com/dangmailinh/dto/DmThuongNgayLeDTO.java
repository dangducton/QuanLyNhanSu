package com.dangmailinh.dto;

public class DmThuongNgayLeDTO {
	private Integer id;
	private String tenngayle;
	private String ghichu;
	private int status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTenngayle() {
		return tenngayle;
	}

	public void setTenngayle(String tenngayle) {
		this.tenngayle = tenngayle;
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

	public DmThuongNgayLeDTO() {
	}

	public DmThuongNgayLeDTO(Integer id, String tenngayle, String ghichu, int status) {
		super();
		this.id = id;
		this.tenngayle = tenngayle;
		this.ghichu = ghichu;
		this.status = status;
	}

}
