package com.dangmailinh.service;

import java.util.List;
import java.util.Optional;

import com.dangmailinh.entities.DmChuyennganh;

public interface DmChuyennganhService {
	Iterable<DmChuyennganh> findAll();

	Optional<DmChuyennganh> findById(Integer id);

	void save(DmChuyennganh chuyenNganh);

	void delete(Integer id);

	List<DmChuyennganh> findAllActiveDmChuyenNganhNative();

	int countChucDanh();
	
	List<DmChuyennganh> findAllByTenChuyenNganh(String tenChuyenNganh);
	
	DmChuyennganh findChuyenNganhById(Integer id);
}
