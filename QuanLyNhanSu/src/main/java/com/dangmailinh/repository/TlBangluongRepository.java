package com.dangmailinh.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dangmailinh.entities.TlBangluong;
@Repository
public interface TlBangluongRepository extends CrudRepository<TlBangluong, Integer>{
	@Query(value = "SELECT * FROM tl_bangluong u WHERE u.status = 1", nativeQuery = true)
	List<TlBangluong> findAllActiveBangLuongNative();

	@Query(value = "SELECT count(*) FROM tl_bangluong", nativeQuery = true)
	int countBangLuong();

	@Query(value = "SELECT * FROM tl_bangluong order by id desc", nativeQuery = true)
	Page<TlBangluong> findAllByBangLuong(Pageable pageable);

	@Query(value = "SELECT * FROM tl_bangluong WHERE id_nhanvien = ?1 and nam = ?2 and thang = ?3", nativeQuery = true)
	TlBangluong findAllByBangLuong(Integer id, Integer nam, Integer thang);

	@Query(value = "SELECT * FROM tl_bangluong WHERE id = ?1", nativeQuery = true)
	TlBangluong findAllById(Integer id);
	
	@Query(value = "SELECT * FROM tl_bangluong c JOIN nv_nhanvien n ON c.id_nhanvien = n.id where n.id_phongban = ?1 and thang = ?2 and nam = ?3", nativeQuery = true)
	List<TlBangluong> findAllBaoCaoLuongTheoPhongBan(Integer id,Integer thang,Integer nam);
	
	@Query(value = "SELECT * FROM tl_bangluong c JOIN nv_nhanvien n ON c.id_nhanvien = n.id where n.id_chucdanh = ?1 and thang = ?2 and nam = ?3", nativeQuery = true)
	List<TlBangluong> findAllBaoCaoLuongTheoChucDanh(Integer id,Integer thang,Integer nam);
	
	@Query(value = "SELECT * FROM tl_bangluong where thang = ?1 and nam = ?2", nativeQuery = true)
	List<TlBangluong> findAllBaoCaoLuongTheoThang(Integer thang,Integer nam);
}
