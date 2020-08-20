package com.dangmailinh.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dangmailinh.entities.TlTamung;
@Repository
public interface TlTamungRepository extends CrudRepository<TlTamung, Integer>{
	@Query(value = "SELECT * FROM tl_tamung u WHERE u.status = 1", nativeQuery = true)
	List<TlTamung> findAllActiveTamUngNative();

	@Query(value = "SELECT count(*) FROM tl_tamung", nativeQuery = true)
	int countTamUng();

	@Query(value = "SELECT * FROM tl_tamung order by id desc", nativeQuery = true)
	Page<TlTamung> findAllByTamUng(Pageable pageable);

	@Query(value = "SELECT * FROM tl_tamung WHERE id = ?1", nativeQuery = true)
	TlTamung findAllById(Integer id);
	
	@Query(value = "SELECT * FROM tl_tamung WHERE id_nhanvien = ?1 and YEAR(ngaytamung) = ?2 and MONTH(ngaytamung) = ?3", nativeQuery = true)
	List<TlTamung> findAllByTamUng(Integer id, Integer nam, Integer thang);
}
