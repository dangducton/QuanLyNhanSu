package com.dangmailinh.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dangmailinh.entities.NvQuyetdinhkyluat;
@Repository
public interface NvQuyetdinhkyluatRepository extends CrudRepository<NvQuyetdinhkyluat, Integer>{
	@Query(value = "SELECT * FROM nv_quyetdinhkyluat u WHERE u.status = 1", nativeQuery = true)
	List<NvQuyetdinhkyluat> findAllActiveQuyetDinhKyLuatNative();

	@Query(value = "SELECT count(*) FROM nv_quyetdinhkyluat", nativeQuery = true)
	int countQuyetDinhKyLuat();

	@Query(value = "SELECT * FROM nv_quyetdinhkyluat order by id desc", nativeQuery = true)
	Page<NvQuyetdinhkyluat> findAllByQuyetDinhKyLuat(Pageable pageable);

	@Query(value = "SELECT * FROM nv_quyetdinhkyluat WHERE id_nhanvien = ?1 and YEAR(ngayhieuluc) = ?2 and MONTH(ngayhieuluc) = ?3 and id_kyluat = ?4", nativeQuery = true)
	NvQuyetdinhkyluat findAllByQuyetDinhKyLuat(Integer id, Integer nam, Integer thang, Integer idKyLuat);

	@Query(value = "SELECT * FROM nv_quyetdinhkyluat WHERE id_nhanvien = ?1 and YEAR(ngayhieuluc) = ?2 and MONTH(ngayhieuluc) = ?3", nativeQuery = true)
	List<NvQuyetdinhkyluat> findAllKyLuat(Integer id, Integer nam, Integer thang);
	
	@Query(value = "SELECT * FROM nv_quyetdinhkyluat WHERE id = ?1", nativeQuery = true)
	NvQuyetdinhkyluat findAllById(Integer id);
	
	@Query(value ="SELECT count(id) from nv_quyetdinhkyluat where status = 1", nativeQuery = true)
	public long countquyetdinhkyluat();
	
	
	@Query(value = "SELECT * FROM nv_quyetdinhkyluat WHERE YEAR(ngayhieuluc) = ?1 group by id_nhanvien", nativeQuery = true)
	Page<NvQuyetdinhkyluat> findAllByKyLuatTheoNam(Integer nam,Pageable pageable);
	
	@Query(value = "SELECT * FROM nv_quyetdinhkyluat WHERE YEAR(ngayhieuluc) = ?1 and id_nhanvien = ?2", nativeQuery = true)
	List<NvQuyetdinhkyluat> findAllByKyLuatTheoNamNhanVien(Integer nam,Integer id);
}
