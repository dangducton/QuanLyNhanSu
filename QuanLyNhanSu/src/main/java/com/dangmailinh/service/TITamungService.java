package com.dangmailinh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dangmailinh.entities.TlTamung;

public interface TITamungService {
	Iterable<TlTamung> findAll();

	Optional<TlTamung> findById(Integer id);

	void save(TlTamung phuCap);

	void delete(Integer id);

	List<TlTamung> findAllActiveTamungNative();

	int countTamCung();

	Page<TlTamung> findAllByTamung(Pageable pageable);

	TlTamung findAllById(Integer id);
	
	List<TlTamung> findAllByTamUng(Integer id, Integer nam, Integer thang);
}
