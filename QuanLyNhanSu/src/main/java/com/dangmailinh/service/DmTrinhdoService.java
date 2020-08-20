package com.dangmailinh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dangmailinh.entities.DmTrinhdo;

public interface DmTrinhdoService {
	Iterable<DmTrinhdo> findAll();

	Optional<DmTrinhdo> findById(Integer id);

	void save(DmTrinhdo trinhDo);

	void delete(Integer id);

	List<DmTrinhdo> findAllActiveDmTrinhDoNative();

	int countTrinhDo();

	List<DmTrinhdo> findAllByTenTrinhDo(String tenTrinhDo);

	Page<DmTrinhdo> findAllByTrinhDo(Pageable pageable);
	
	DmTrinhdo findAllTrinhDo(Integer id);
}
