package com.dangmailinh.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dangmailinh.entities.NvQuyetdinhthangchuc;
@Repository
public interface NvQuyetdinhthangchucRepository extends CrudRepository<NvQuyetdinhthangchuc, Integer>{
	@Query(value = "SELECT * FROM nv_quyetdinhthangchuc u WHERE u.status = 1", nativeQuery = true)
	List<NvQuyetdinhthangchuc> findAllActiveQuyetDinhThangChucNative();

	@Query(value = "SELECT count(*) FROM nv_quyetdinhthangchuc", nativeQuery = true)
	int countQuyetDinhKhenThuong();

	@Query(value = "SELECT * FROM nv_quyetdinhthangchuc order by id desc", nativeQuery = true)
	Page<NvQuyetdinhthangchuc> findAllByQuyetDinhNangThangChuc(Pageable pageable);

	@Query(value = "SELECT * FROM nv_quyetdinhthangchuc WHERE id_nhanvien = ?1 and YEAR(ngayhieuluc) = ?2 and MONTH(ngayhieuluc) = ?3", nativeQuery = true)
	NvQuyetdinhthangchuc findAllByQuyetDinhThangChuc(Integer id, Integer nam, Integer thang);

	@Query(value = "SELECT * FROM nv_quyetdinhthangchuc WHERE id = ?1", nativeQuery = true)
	NvQuyetdinhthangchuc findAllById(Integer id);
	
	@Query(value ="SELECT count(id) from nv_quyetdinhthangchuc where status = 1", nativeQuery = true)
	public long countquyetdinhthangchuc();
}
