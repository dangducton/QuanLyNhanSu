package com.dangmailinh.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dangmailinh.entities.NvQuyetdinhchamduthopdong;
@Repository
public interface NvQuyetdinhchamduthopdongRepository extends CrudRepository<NvQuyetdinhchamduthopdong, Integer>{
	@Query(value = "SELECT * FROM nv_quyetdinhchamduthopdong u WHERE u.status = 1", nativeQuery = true)
	List<NvQuyetdinhchamduthopdong> findAllActiveQuyetDinhChamDutHopDongNative();

	@Query(value = "SELECT count(*) FROM nv_quyetdinhchamduthopdong", nativeQuery = true)
	int countQuyetDinhChamDutHopDong();

	@Query(value = "SELECT * FROM nv_quyetdinhchamduthopdong order by id desc", nativeQuery = true)
	Page<NvQuyetdinhchamduthopdong> findAllByQuyetDinhChamDutHopDong(Pageable pageable);

	@Query(value = "SELECT * FROM nv_quyetdinhchamduthopdong WHERE id_nhanvien = ?1", nativeQuery = true)
	NvQuyetdinhchamduthopdong findAllByQuyetDinhChamDutHopDong(Integer id);

	@Query(value = "SELECT * FROM nv_quyetdinhchamduthopdong WHERE id = ?1", nativeQuery = true)
	NvQuyetdinhchamduthopdong findAllById(Integer id);
	
	@Query(value ="SELECT count(id) from nv_quyetdinhchamduthopdong where status = 1", nativeQuery = true)
	public long countquyetdinhchamduthopdong();
}
