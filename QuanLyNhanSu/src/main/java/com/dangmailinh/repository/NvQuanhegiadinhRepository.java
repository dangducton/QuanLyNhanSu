package com.dangmailinh.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dangmailinh.entities.NvQuanhegiadinh;

@Repository
public interface NvQuanhegiadinhRepository extends CrudRepository<NvQuanhegiadinh, Integer> {
	@Query(value = "SELECT * FROM nv_quanhegiadinh u WHERE u.status = 1", nativeQuery = true)
	List<NvQuanhegiadinh> findAllActiveQuanHeGiaDinhNative();

	@Query(value = "SELECT count(*) FROM nv_quanhegiadinh", nativeQuery = true)
	int countQuanHeGiaDinh();

	@Query(
			  value = "SELECT * FROM nv_quanhegiadinh order by id desc", 
			  nativeQuery = true)
	Page<NvQuanhegiadinh> findAllByQuanHeGiaDinh(Pageable pageable);
	
	@Query(
			  value = "SELECT * FROM nv_quanhegiadinh WHERE id_nhanvien = ?1", 
			  nativeQuery = true)
	List<NvQuanhegiadinh> findAllByQuanHeGiaDinh(Integer id);
	
	@Query(
			  value = "SELECT * FROM nv_quanhegiadinh WHERE id = ?1", 
			  nativeQuery = true)
	NvQuanhegiadinh findAllById(Integer id);
}
