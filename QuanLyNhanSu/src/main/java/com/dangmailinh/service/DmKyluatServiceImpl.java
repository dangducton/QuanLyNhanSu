package com.dangmailinh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dangmailinh.entities.DmKyluat;
import com.dangmailinh.repository.DmKyluatRepository;

@Service
@Transactional
public class DmKyluatServiceImpl implements DmKyluatService {
	@Autowired(required = true)
	public DmKyluatRepository dmKyluatRepository;

	@Override
	public Iterable<DmKyluat> findAll() {
		// TODO Auto-generated method stub
		return dmKyluatRepository.findAll();
	}

	@Override
	public Optional<DmKyluat> findById(Integer id) {
		// TODO Auto-generated method stub
		return dmKyluatRepository.findById(id);
	}

	@Override
	public void save(DmKyluat kyLuat) {
		// TODO Auto-generated method stub
		dmKyluatRepository.save(kyLuat);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		dmKyluatRepository.deleteById(id);
	}

	@Override
	public List<DmKyluat> findAllActiveDmKyLuatNative() {
		// TODO Auto-generated method stub
		return dmKyluatRepository.findAllActiveKyLuatNative();
	}

	@Override
	public int countKyLuat() {
		// TODO Auto-generated method stub
		return dmKyluatRepository.countKyLuat();
	}

	@Override
	public List<DmKyluat> findAllByTenKyLuat(String tenKyLuat) {
		// TODO Auto-generated method stub
		return dmKyluatRepository.findAllByTenKyLuat(tenKyLuat);
	}

	@Override
	public Page<DmKyluat> findAllByKyLuat(Pageable pageable) {
		// TODO Auto-generated method stub
		return dmKyluatRepository.findAllKyLuat(pageable);
	}
}
