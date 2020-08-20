package com.dangmailinh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dangmailinh.entities.DmDantoc;

public interface DmDantocService {
	Iterable<DmDantoc> findAll();

	Optional<DmDantoc> findById(Integer id);

	void save(DmDantoc danToc);

	void delete(Integer id);

	List<DmDantoc> findAllActiveDmDanTocNative();

	int countDanToc();
	
	List<DmDantoc> findAllByTenDanToc(String tenDanToc);
	
	Page<DmDantoc> findAllByDanToc(Pageable pageable);
}
