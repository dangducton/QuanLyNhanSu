package com.dangmailinh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dangmailinh.entities.DmChitietthuongngayle;

public interface DmChitietthuongngayleService {
	Iterable<DmChitietthuongngayle> findAll();

	Optional<DmChitietthuongngayle> findById(Integer id);

	void save(DmChitietthuongngayle chiTietThuongLe);

	void delete(Integer id);
	
	Page<DmChitietthuongngayle> findAllByChiTietThuongLe(Pageable pageable);
	
	DmChitietthuongngayle findByIdChiTietThuongLe(Integer id);
	
	List<DmChitietthuongngayle> findByIdChiTietThuongLeByChucDanh(Integer id);
}
