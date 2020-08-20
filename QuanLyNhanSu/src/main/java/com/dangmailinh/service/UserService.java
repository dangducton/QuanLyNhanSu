package com.dangmailinh.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dangmailinh.entities.User;

public interface UserService {
	User save(User nguoiDung);
	
	Optional<User> findById(Integer id);

	User findBytendangnhap(String tendangnhap);

	void update(User dbNguoiDung);
	
	User findBytendangnhapAndEnabled(String tendangnhap);
	
	User findByIDNhanVien(Integer id, String tendangnhap);
	
	Page<User> findAllQuanTri(Pageable pageable);
	
	public long countquantri();
}
