package com.dangmailinh.service;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import com.dangmailinh.entities.Role;


public interface SercurityService {
	void autoLogin(String tendangnhap, String password, Collection<Role> collection, HttpServletRequest request);
}
