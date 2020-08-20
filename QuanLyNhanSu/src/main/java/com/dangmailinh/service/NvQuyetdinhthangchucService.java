package com.dangmailinh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dangmailinh.entities.NvQuyetdinhthangchuc;

public interface NvQuyetdinhthangchucService {
	Iterable<NvQuyetdinhthangchuc> findAll();

	Optional<NvQuyetdinhthangchuc> findById(Integer id);

	void save(NvQuyetdinhthangchuc phuCap);

	void delete(Integer id);

	List<NvQuyetdinhthangchuc> findAllActiveQuyetDinhThangChucnNative();

	int countQuyetDinhThangChuc();

	Page<NvQuyetdinhthangchuc> findAllByQuyetDinhThangChuc(Pageable pageable);

	NvQuyetdinhthangchuc findAllByQuyetDinhThangChuc(Integer id, Integer nam, Integer thang);

	NvQuyetdinhthangchuc findAllById(Integer id);
	
	public long countquyetdinhthangchuc();
}
