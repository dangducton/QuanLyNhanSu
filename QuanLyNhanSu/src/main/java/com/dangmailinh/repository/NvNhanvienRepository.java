package com.dangmailinh.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dangmailinh.entities.NvNhanvien;

@Repository
public interface NvNhanvienRepository extends CrudRepository<NvNhanvien, Integer> {
	@Query(value = "SELECT MAX(id) FROM nv_nhanvien", nativeQuery = true)
	int findMaxID();

	@Query(value = "SELECT * FROM nv_nhanvien WHERE nv_nhanvien.id = ?1", nativeQuery = true)
	NvNhanvien findNhanVienByID(Integer id);

	@Query(value = "SELECT * FROM nv_nhanvien n JOIN dm_phongban c ON n.id_phongban = c.id and c.tenphongban like %?1% or n.ten like %?1% or n.hodem like %?1% or n.ngaysinh like %?1% or n.diachihientai like %?1% or n.id_chitiet like %?1%  GROUP BY n.id" , nativeQuery = true)
	Page<NvNhanvien> findAllByTenNhanVien(String tennhanvien, Pageable pageable);

	@Query(value = "SELECT * FROM nv_nhanvien WHERE nv_nhanvien.status = 1", nativeQuery = true)
	List<NvNhanvien> findAllNhanVienAcctive();

	@Query(value = "SELECT * FROM nv_nhanvien WHERE nv_nhanvien.id_chitiet = ?1 and nv_nhanvien.status = 1", nativeQuery = true)
	NvNhanvien findNhanVienByIDNhanVien(String nhanvienId);

	@Query(value = "SELECT * FROM nv_nhanvien ORDER BY id desc", nativeQuery = true)
	Page<NvNhanvien> findAll(Pageable pageable);

	@Query(value = "SELECT count(*) FROM nv_nhanvien", nativeQuery = true)
	int countNhanVien();

	@Query(value = "SELECT * FROM nv_nhanvien c JOIN dm_chucdanh n ON c.id_chucdanh = n.id where n.id = ?1", nativeQuery = true)
	Page<NvNhanvien> findAllNhanVienTheoChucDanh(Integer id, Pageable pageable);

	@Query(value = "SELECT * FROM nv_nhanvien c JOIN dm_trinhdo n ON c.id_trinhdo = n.id where n.id = ?1", nativeQuery = true)
	Page<NvNhanvien> findAllNhanVienTheoTrinhDo(Integer id, Pageable pageable);

	@Query(value = "SELECT * FROM nv_nhanvien c JOIN dm_phongban n ON c.id_phongban = n.id where n.id = ?1", nativeQuery = true)
	Page<NvNhanvien> findAllNhanVienTheoPhongBan(Integer id, Pageable pageable);

	@Query(value = "SELECT * FROM nv_nhanvien c JOIN dm_chuyennganh n ON c.id_chuyennganh = n.id where n.id = ?1", nativeQuery = true)
	Page<NvNhanvien> findAllNhanVienTheoChuyenNganh(Integer id, Pageable pageable);
	
	@Query(value = "SELECT * FROM nv_nhanvien c JOIN dm_chucdanh n ON c.id_chucdanh = n.id where n.id = ?1", nativeQuery = true)
	List<NvNhanvien> findAllNhanVienBaoCaoTheoChucDanh(Integer id);

	@Query(value = "SELECT * FROM nv_nhanvien c JOIN dm_trinhdo n ON c.id_trinhdo = n.id where n.id = ?1", nativeQuery = true)
	List<NvNhanvien> findAllNhanVienBaoCaoTheoTrinhDo(Integer id);

	@Query(value = "SELECT * FROM nv_nhanvien c JOIN dm_phongban n ON c.id_phongban = n.id where n.id = ?1", nativeQuery = true)
	List<NvNhanvien> findAllNhanVienBaoCaoTheoPhongBan(Integer id);

	@Query(value = "SELECT * FROM nv_nhanvien c JOIN dm_chuyennganh n ON c.id_chuyennganh = n.id where n.id = ?1", nativeQuery = true)
	List<NvNhanvien> findAllNhanVienBaoCaoTheoChuyenNganh(Integer id);
	
	@Query(value ="SELECT count(id) from nv_nhanvien where trangthaihopdong = 1", nativeQuery = true)
	public long countnhanvien();
}



