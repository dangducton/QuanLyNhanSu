package com.dangmailinh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dangmailinh.entities.NvQuanhegiadinh;

public interface NvQuanhegiadinhService {
	Iterable<NvQuanhegiadinh> findAll();

	Optional<NvQuanhegiadinh> findById(Integer id);

	void save(NvQuanhegiadinh phuCap);

	void delete(Integer id);

	List<NvQuanhegiadinh> findAllActiveDmPhuCapNative();

	int countPhuCap();

	Page<NvQuanhegiadinh> findAllByPhuCap(Pageable pageable);
	
	List<NvQuanhegiadinh> findAllByQuanHeGiaDinh(Integer id);
	
	NvQuanhegiadinh findAllById(Integer id);
}
