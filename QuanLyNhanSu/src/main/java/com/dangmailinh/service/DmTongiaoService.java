package com.dangmailinh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dangmailinh.entities.DmTongiao;

public interface DmTongiaoService {
	Iterable<DmTongiao> findAll();

	Optional<DmTongiao> findById(Integer id);

	void save(DmTongiao tonGiao);

	void delete(Integer id);

	List<DmTongiao> findAllActiveDmTonGiaoNative();

	int countTonGiao();

	List<DmTongiao> findAllByTenTonGiao(String tenTonGiao);

	Page<DmTongiao> findAllByTonGiao(Pageable pageable);
}
