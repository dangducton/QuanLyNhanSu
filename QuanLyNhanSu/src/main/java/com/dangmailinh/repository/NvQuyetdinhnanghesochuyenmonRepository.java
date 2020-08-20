package com.dangmailinh.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dangmailinh.entities.NvQuyetdinhnanghesochuyenmon;
@Repository
public interface NvQuyetdinhnanghesochuyenmonRepository extends CrudRepository<NvQuyetdinhnanghesochuyenmon, Integer>{
	@Query(value = "SELECT * FROM nv_quyetdinhnanghesochuyenmon u WHERE u.status = 1", nativeQuery = true)
	List<NvQuyetdinhnanghesochuyenmon> findAllActiveQuyetDinhNangHeSoChuyenMonNative();

	@Query(value = "SELECT count(*) FROM nv_quyetdinhnanghesochuyenmon", nativeQuery = true)
	int countQuyetDinhKhenThuong();

	@Query(value = "SELECT * FROM nv_quyetdinhnanghesochuyenmon order by id desc", nativeQuery = true)
	Page<NvQuyetdinhnanghesochuyenmon> findAllByQuyetDinhNangHeSoChuyenMon(Pageable pageable);

	@Query(value = "SELECT * FROM nv_quyetdinhnanghesochuyenmon WHERE id_nhanvien = ?1 and YEAR(ngayhieuluc) = ?2 and MONTH(ngayhieuluc) = ?3", nativeQuery = true)
	NvQuyetdinhnanghesochuyenmon findAllByQuyetDinhNangHeSoChuyenMon(Integer id, Integer nam, Integer thang);

	@Query(value = "SELECT * FROM nv_quyetdinhnanghesochuyenmon WHERE id = ?1", nativeQuery = true)
	NvQuyetdinhnanghesochuyenmon findAllById(Integer id);
	
	@Query(value ="SELECT count(id) from nv_quyetdinhnanghesochuyenmon where status = 1", nativeQuery = true)
	public long countquyetnanghesochuyenmon();
}
