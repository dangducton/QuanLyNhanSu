package com.dangmailinh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dangmailinh.entities.NvQuyetdinhkyluat;

public interface NvQuyetdinhkyluatService {
	Iterable<NvQuyetdinhkyluat> findAll();

	Optional<NvQuyetdinhkyluat> findById(Integer id);

	void save(NvQuyetdinhkyluat phuCap);

	void delete(Integer id);

	List<NvQuyetdinhkyluat> findAllActiveQuyetDinhKyLuatNative();

	int countKhenThuong();

	Page<NvQuyetdinhkyluat> findAllByQuyetDinhKyLuat(Pageable pageable);

	NvQuyetdinhkyluat findAllByQuyetDinhKyLuat(Integer id, Integer nam, Integer thang, Integer idKyLuat);

	NvQuyetdinhkyluat findAllById(Integer id);
	
	List<NvQuyetdinhkyluat> findAllKyLuat(Integer id, Integer nam, Integer thang);
	
	public long countquyetdinhkyluat();
	
	Page<NvQuyetdinhkyluat> findAllByKyLuatTheoNam(Integer nam,Pageable pageable);
	
	List<NvQuyetdinhkyluat> findAllByKyLuatTheoNamNhanVien(Integer nam,Integer id);
}
