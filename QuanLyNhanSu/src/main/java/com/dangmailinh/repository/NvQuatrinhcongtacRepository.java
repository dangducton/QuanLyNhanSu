package com.dangmailinh.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dangmailinh.entities.NvQuatrinhcongtac;

@Repository
public interface NvQuatrinhcongtacRepository extends CrudRepository<NvQuatrinhcongtac, Integer> {
	@Query(value = "SELECT * FROM nv_quanhegiadinh u WHERE u.status = 1", nativeQuery = true)
	List<NvQuatrinhcongtac> findAllActiveQuaTrinhCongTacNative();

	@Query(value = "SELECT count(*) FROM nv_quatrinhcongtac", nativeQuery = true)
	int countQuaTrinhCongTac();

	@Query(value = "SELECT * FROM nv_quatrinhcongtac order by id desc", nativeQuery = true)
	Page<NvQuatrinhcongtac> findAllByQuaTrinhCongTac(Pageable pageable);

	@Query(value = "SELECT * FROM nv_quatrinhcongtac WHERE id_nhanvien = ?1", nativeQuery = true)
	List<NvQuatrinhcongtac> findAllByQuaTrinhCongTac(Integer id);

	@Query(value = "SELECT * FROM nv_quatrinhcongtac WHERE id = ?1", nativeQuery = true)
	NvQuatrinhcongtac findAllById(Integer id);

}
