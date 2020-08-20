package com.dangmailinh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dangmailinh.entities.NvQuyetdinhchamduthopdong;

public interface NvQuyetdinhchamduthopdongService {
	Iterable<NvQuyetdinhchamduthopdong> findAll();

	Optional<NvQuyetdinhchamduthopdong> findById(Integer id);

	void save(NvQuyetdinhchamduthopdong phuCap);

	void delete(Integer id);

	List<NvQuyetdinhchamduthopdong> findAllActiveQuyetDinhChamDutHopDongNative();

	int countQuaTrinhCongTac();

	Page<NvQuyetdinhchamduthopdong> findAllByQuyetDinhChamDutHopDong(Pageable pageable);

	NvQuyetdinhchamduthopdong findAllByQuyetDinhChamDutHopDong(Integer id);

	NvQuyetdinhchamduthopdong findAllById(Integer id);
	
	public long countquyetdinhchamduthopdong();
}
