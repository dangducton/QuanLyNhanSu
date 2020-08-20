package com.dangmailinh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dangmailinh.entities.NvQuatrinhcongtac;

public interface NvQuatrinhcongtacService {
	Iterable<NvQuatrinhcongtac> findAll();

	Optional<NvQuatrinhcongtac> findById(Integer id);

	void save(NvQuatrinhcongtac phuCap);

	void delete(Integer id);

	List<NvQuatrinhcongtac> findAllActiveQuaTrinhCongTacNative();

	int countQuaTrinhCongTac();

	Page<NvQuatrinhcongtac> findAllByQuaTrinhCongTac(Pageable pageable);

	List<NvQuatrinhcongtac> findAllByQuaTrinhCongTac(Integer id);

	NvQuatrinhcongtac findAllById(Integer id);
	
}
