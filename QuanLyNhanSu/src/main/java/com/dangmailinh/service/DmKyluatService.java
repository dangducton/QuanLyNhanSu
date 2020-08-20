package com.dangmailinh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dangmailinh.entities.DmKyluat;

public interface DmKyluatService {
	Iterable<DmKyluat> findAll();

	Optional<DmKyluat> findById(Integer id);

	void save(DmKyluat kyLuat);

	void delete(Integer id);

	List<DmKyluat> findAllActiveDmKyLuatNative();

	int countKyLuat();
	
	List<DmKyluat> findAllByTenKyLuat(String tenKyLuat);
	
	Page<DmKyluat> findAllByKyLuat(Pageable pageable);
}
