package com.dangmailinh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dangmailinh.entities.NvQuyetdinhkhenthuong;

public interface NvQuyetdinhkhenthuongService {
	Iterable<NvQuyetdinhkhenthuong> findAll();

	Optional<NvQuyetdinhkhenthuong> findById(Integer id);

	void save(NvQuyetdinhkhenthuong phuCap);

	void delete(Integer id);

	List<NvQuyetdinhkhenthuong> findAllActiveQuyetDinhKhenThuongNative();

	int countKhenThuong();

	Page<NvQuyetdinhkhenthuong> findAllByQuyetDinhKhenThuong(Pageable pageable);

	NvQuyetdinhkhenthuong findAllByQuyetDinhKhenThuong(Integer id, Integer nam, Integer thang);

	NvQuyetdinhkhenthuong findAllById(Integer id);
	
	List<NvQuyetdinhkhenthuong> findAllByKhenThuong(Integer id, Integer nam, Integer thang);
	
	public long countquyetdinhkhenthuong();
	
	Page<NvQuyetdinhkhenthuong> findAllByKhenThuongTheoNam(Integer nam,Pageable pageable);
	
	List<NvQuyetdinhkhenthuong> findAllByKhenThuongTheoNamNhanVien(Integer nam,Integer id);
}
