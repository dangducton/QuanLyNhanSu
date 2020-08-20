package com.dangmailinh.service;

import java.util.List;
import java.util.Optional;

import com.dangmailinh.entities.DmChucdanh;

public interface DmChucdanhService {
	Iterable<DmChucdanh> findAll();

	Optional<DmChucdanh> findById(Integer id);

	void save(DmChucdanh chucDanh);

	void delete(Integer id);

	List<DmChucdanh> findAllActiveDmChucDanhNative();

	int countChucDanh();
	
	List<DmChucdanh> findAllByTenChucDanh(String tenChucDanh);
	
	DmChucdanh findChucDanhByID(Integer id);
	
	public long countchucdanh();
}
