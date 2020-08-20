package com.dangmailinh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dangmailinh.entities.DmQuanhe;

public interface DmQuanheService {
	Iterable<DmQuanhe> findAll();

	Optional<DmQuanhe> findById(Integer id);

	void save(DmQuanhe quanHe);

	void delete(Integer id);

	List<DmQuanhe> findAllActiveDmQuanHeNative();

	int countQuanHe();

	List<DmQuanhe> findAllByTenQuanHe(String tenQuanHe);

	Page<DmQuanhe> findAllByQuanHe(Pageable pageable);
}
