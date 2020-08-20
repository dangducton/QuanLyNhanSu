package com.dangmailinh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dangmailinh.entities.DmPhucap;

public interface DmPhucapService {
	Iterable<DmPhucap> findAll();

	Optional<DmPhucap> findById(Integer id);

	void save(DmPhucap phuCap);

	void delete(Integer id);

	List<DmPhucap> findAllActiveDmPhuCapNative();

	int countPhuCap();

	List<DmPhucap> findAllByTenPhuCap(String tenPhuCap);

	Page<DmPhucap> findAllByPhuCap(Pageable pageable);
}
