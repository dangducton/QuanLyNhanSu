package com.dangmailinh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dangmailinh.entities.DmPhongban;
import com.dangmailinh.repository.DmPhongbanRepository;

@Service
@Transactional
public class DmPhongbanServiceImpl implements DmPhongbanService {
	@Autowired(required = true)
	public DmPhongbanRepository dmPhongbanRepository;

	@Override
	public Iterable<DmPhongban> findAll() {
		// TODO Auto-generated method stub
		return dmPhongbanRepository.findAll();
	}

	@Override
	public Optional<DmPhongban> findById(Integer id) {
		// TODO Auto-generated method stub
		return dmPhongbanRepository.findById(id);
	}

	@Override
	public void save(DmPhongban phongBan) {
		// TODO Auto-generated method stub
		dmPhongbanRepository.save(phongBan);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		dmPhongbanRepository.deleteById(id);
	}

	@Override
	public List<DmPhongban> findAllActiveDmPhongBanNative() {
		// TODO Auto-generated method stub
		return dmPhongbanRepository.findAllActivePhongBanNative();
	}

	@Override
	public int countPhongBan() {
		// TODO Auto-generated method stub
		return dmPhongbanRepository.countPhongBan();
	}

	@Override
	public List<DmPhongban> findAllByTenPhongBan(String tenphongban) {
		// TODO Auto-generated method stub
		return dmPhongbanRepository.findAllByTenPhongBan(tenphongban);
	}

	@Override
	public DmPhongban findPhongBanByID(Integer id) {
		// TODO Auto-generated method stub
		return dmPhongbanRepository.findPhongBanByID(id);
	}

	@Override
	public long countphongban() {
		// TODO Auto-generated method stub
		return dmPhongbanRepository.countphongban();
	}

}
