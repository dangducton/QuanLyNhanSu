package com.dangmailinh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dangmailinh.entities.NvNhanvien;

public interface NvNhanvienService {
	Optional<NvNhanvien> findById(Integer id);

	void save(NvNhanvien nv);

	void delete(Integer id);

	int findMaxID();

	Page<NvNhanvien> findAll(Pageable pageable);

	NvNhanvien findNhanVienByID(Integer id);

	NvNhanvien findNhanVienByIDNhanVien(String id);

	List<NvNhanvien> findAllNhanVienAcctive();

	Page<NvNhanvien> findAllByTenNhanVien(String tennhanvien, Pageable pageable);

	int countNhanVien();

	Page<NvNhanvien> findAllNhanVienTheoChucDanh(Integer id, Pageable pageable);

	Page<NvNhanvien> findAllNhanVienTheoTrinhDo(Integer id, Pageable pageable);

	Page<NvNhanvien> findAllNhanVienTheoPhongBan(Integer id, Pageable pageable);

	Page<NvNhanvien> findAllNhanVienTheoChuyenNganh(Integer id, Pageable pageable);
	
	List<NvNhanvien> findAllNhanVienBaoCaoTheoChucDanh(Integer id);

	List<NvNhanvien> findAllNhanVienBaoCaoTheoTrinhDo(Integer id);

	List<NvNhanvien> findAllNhanVienBaoCaoTheoPhongBan(Integer id);

	List<NvNhanvien> findAllNhanVienBaoCaoTheoChuyenNganh(Integer id);
	
	public long countnhanvien();
}
