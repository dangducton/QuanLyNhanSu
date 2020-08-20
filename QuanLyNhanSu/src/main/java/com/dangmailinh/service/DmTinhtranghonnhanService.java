package com.dangmailinh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dangmailinh.entities.DmTinhtranghonnhan;

public interface DmTinhtranghonnhanService {
	Iterable<DmTinhtranghonnhan> findAll();

	Optional<DmTinhtranghonnhan> findById(Integer id);

	void save(DmTinhtranghonnhan tinhTrangHonNhan);

	void delete(Integer id);

	List<DmTinhtranghonnhan> findAllActiveDmTinhTrangHonNhanNative();

	int countQuanHe();

	List<DmTinhtranghonnhan> findAllByTenTinhTrangHonNhan(String tenTinhTrangHonNhan);

	Page<DmTinhtranghonnhan> findAllByTinhTrangHonNhan(Pageable pageable);
	
	DmTinhtranghonnhan findTinhTrangHonNhanById(Integer id);
}
