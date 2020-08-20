package com.dangmailinh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dangmailinh.entities.TlBangluong;

public interface TIBangluongService {
	Iterable<TlBangluong> findAll();

	Optional<TlBangluong> findById(Integer id);

	void save(TlBangluong phuCap);

	void delete(Integer id);

	List<TlBangluong> findAllActiveBangLuongNative();

	int countQuyetDinhThangChuc();

	Page<TlBangluong> findAllByBangLuong(Pageable pageable);

	TlBangluong findAllByBangLuong(Integer id, Integer nam, Integer thang);

	TlBangluong findAllById(Integer id);
	
	List<TlBangluong> findAllBaoCaoLuongTheoPhongBan(Integer id,Integer thang,Integer nam);
	
	List<TlBangluong> findAllBaoCaoLuongTheoChucDanh(Integer id,Integer thang,Integer nam);
	
	List<TlBangluong> findAllBaoCaoLuongTheoThang(Integer thang,Integer nam);
}
