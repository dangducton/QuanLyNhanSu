package com.dangmailinh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dangmailinh.entities.DmThuongngayle;

public interface DmThuongngayleService {
	Iterable<DmThuongngayle> findAll();

	Optional<DmThuongngayle> findById(Integer id);

	void save(DmThuongngayle thuongNgayLe);

	void delete(Integer id);

	List<DmThuongngayle> findAllActiveDmThuongNgayLeNative();

	int countQuanHe();

	List<DmThuongngayle> findAllByTenThuongNgayLe(String tenNgayLe);

	Page<DmThuongngayle> findAllByThuongNgayLe(Pageable pageable);
	
	DmThuongngayle findNgayLeById(Integer id);
}
