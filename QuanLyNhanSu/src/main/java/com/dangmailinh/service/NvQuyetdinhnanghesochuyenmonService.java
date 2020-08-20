package com.dangmailinh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dangmailinh.entities.NvQuyetdinhnanghesochuyenmon;

public interface NvQuyetdinhnanghesochuyenmonService {
	Iterable<NvQuyetdinhnanghesochuyenmon> findAll();

	Optional<NvQuyetdinhnanghesochuyenmon> findById(Integer id);

	void save(NvQuyetdinhnanghesochuyenmon phuCap);

	void delete(Integer id);

	List<NvQuyetdinhnanghesochuyenmon> findAllActiveQuyetDinhNangHeSoChuyenMonNative();

	int countQuyetDinhNangHeSoChuyenMon();

	Page<NvQuyetdinhnanghesochuyenmon> findAllByQuyetDinhNangHeSoChuyenMon(Pageable pageable);

	NvQuyetdinhnanghesochuyenmon findAllByQuyetDinhNangHeSoChuyenMon(Integer id, Integer nam, Integer thang);

	NvQuyetdinhnanghesochuyenmon findAllById(Integer id);
	
	public long countquyetnanghesochuyenmon();
}
