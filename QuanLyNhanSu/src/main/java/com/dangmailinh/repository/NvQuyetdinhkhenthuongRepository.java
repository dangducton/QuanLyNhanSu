package com.dangmailinh.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dangmailinh.entities.NvQuyetdinhkhenthuong;
@Repository
public interface NvQuyetdinhkhenthuongRepository extends CrudRepository<NvQuyetdinhkhenthuong, Integer>{
	@Query(value = "SELECT * FROM nv_quyetdinhkhenthuong u WHERE u.status = 1", nativeQuery = true)
	List<NvQuyetdinhkhenthuong> findAllActiveQuyetDinhKhenThuongNative();

	@Query(value = "SELECT count(*) FROM nv_quyetdinhkhenthuong", nativeQuery = true)
	int countQuyetDinhKhenThuong();

	@Query(value = "SELECT * FROM nv_quyetdinhkhenthuong order by id desc", nativeQuery = true)
	Page<NvQuyetdinhkhenthuong> findAllByQuyetDinhKhenThuong(Pageable pageable);

	@Query(value = "SELECT * FROM nv_quyetdinhkhenthuong WHERE id_nhanvien = ?1 and YEAR(ngayhieuluc) = ?2 and MONTH(ngayhieuluc) = ?3", nativeQuery = true)
	NvQuyetdinhkhenthuong findAllByQuyetDinhKhenThuong(Integer id, Integer nam, Integer thang);
	
	@Query(value = "SELECT * FROM nv_quyetdinhkhenthuong WHERE id_nhanvien = ?1 and YEAR(ngayhieuluc) = ?2 and MONTH(ngayhieuluc) = ?3", nativeQuery = true)
	List<NvQuyetdinhkhenthuong> findAllByKhenThuong(Integer id, Integer nam, Integer thang);

	@Query(value = "SELECT * FROM nv_quyetdinhkhenthuong WHERE id = ?1", nativeQuery = true)
	NvQuyetdinhkhenthuong findAllById(Integer id);
	
	@Query(value ="SELECT count(id) from nv_quyetdinhkhenthuong where status = 1", nativeQuery = true)
	public long countquyetdinhkhenthuong();
	
	@Query(value = "SELECT * FROM nv_quyetdinhkhenthuong WHERE YEAR(ngayhieuluc) = ?1 group by id_nhanvien", nativeQuery = true)
	Page<NvQuyetdinhkhenthuong> findAllByKhenThuongTheoNam(Integer nam,Pageable pageable);
	
	@Query(value = "SELECT * FROM nv_quyetdinhkhenthuong WHERE YEAR(ngayhieuluc) = ?1 and id_nhanvien = ?2", nativeQuery = true)
	List<NvQuyetdinhkhenthuong> findAllByKhenThuongTheoNamNhanVien(Integer nam,Integer id);
}
