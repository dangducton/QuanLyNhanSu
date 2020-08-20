package com.dangmailinh.service;

import java.util.List;
import java.util.Optional;

import com.dangmailinh.entities.DmPhongban;

public interface DmPhongbanService {
	Iterable<DmPhongban> findAll();

	Optional<DmPhongban> findById(Integer id);

	void save(DmPhongban phongBan);

	void delete(Integer id);

	List<DmPhongban> findAllActiveDmPhongBanNative();

	int countPhongBan();
	
	List<DmPhongban> findAllByTenPhongBan(String tenphongban);
	
	DmPhongban findPhongBanByID(Integer id);
	
	public long countphongban();
}
