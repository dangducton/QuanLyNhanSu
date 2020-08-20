package com.dangmailinh.dto;

public class FormQuanHeDTO {
	private Integer id;
	private int status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public FormQuanHeDTO() {
	}

	public FormQuanHeDTO(Integer id, int status) {
		super();
		this.id = id;
		this.status = status;
	}

}
