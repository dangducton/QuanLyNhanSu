package com.dangmailinh.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dangmailinh.entities.PhuCap;

@Repository
public interface PhuCapRepository extends JpaRepository<PhuCap, Integer> {
	@Query(value = "SELECT * FROM phucap u WHERE u.status = 1", nativeQuery = true)
	List<PhuCap> findAllActivePhuCapNative();

	@Query(value = "SELECT count(*) FROM phucap", nativeQuery = true)
	int countPhuCap();

	@Query(value = "SELECT * FROM phucap orderby id desc", nativeQuery = true)
	Page<PhuCap> findAllByPhuCap(Pageable pageable);

	@Query(value = "SELECT * FROM phucap WHERE id = ?1", nativeQuery = true)
	PhuCap findAllById(Integer id);
	
	@Query(value = "SELECT * FROM phucap WHERE id_nhanvien = ?1 and nam = ?2 and thang = ?3", nativeQuery = true)
	List<PhuCap> findAllByTamUng(Integer id, Integer nam, Integer thang);
}