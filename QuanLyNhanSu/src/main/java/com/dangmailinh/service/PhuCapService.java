package com.dangmailinh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dangmailinh.entities.PhuCap;

public interface PhuCapService {
	Iterable<PhuCap> findAll();

	Optional<PhuCap> findById(Integer id);

	void save(PhuCap phuCap);

	void delete(Integer id);

	int countPhuCap();

	Page<PhuCap> findAllByPhuCap(Pageable pageable);


	PhuCap findAllById(Integer id);
	
	List<PhuCap> findAllByTamUng(Integer id, Integer nam, Integer thang);
	
	List<PhuCap> findAllActivePhuCapNative();
}
