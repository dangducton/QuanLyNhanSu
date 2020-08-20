package com.dangmailinh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dangmailinh.entities.DmPhucap;
import com.dangmailinh.repository.DmPhucapRepository;

@Service
@Transactional
public class DmPhucapServiceImpl implements DmPhucapService{
	@Autowired(required = true)
	public DmPhucapRepository dmPhucapRepository;

	@Override
	public Iterable<DmPhucap> findAll() {
		// TODO Auto-generated method stub
		return dmPhucapRepository.findAll();
	}

	@Override
	public Optional<DmPhucap> findById(Integer id) {
		// TODO Auto-generated method stub
		return dmPhucapRepository.findById(id);
	}

	@Override
	public void save(DmPhucap phuCap) {
		// TODO Auto-generated method stub
		dmPhucapRepository.save(phuCap);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		dmPhucapRepository.deleteById(id);
	}

	@Override
	public List<DmPhucap> findAllActiveDmPhuCapNative() {
		// TODO Auto-generated method stub
		return dmPhucapRepository.findAllActivePhuCapNative();
	}

	@Override
	public int countPhuCap() {
		// TODO Auto-generated method stub
		return dmPhucapRepository.countPhuCap();
	}

	@Override
	public List<DmPhucap> findAllByTenPhuCap(String tenPhuCap) {
		// TODO Auto-generated method stub
		return dmPhucapRepository.findAllByTenPhuCap(tenPhuCap);
	}

	@Override
	public Page<DmPhucap> findAllByPhuCap(Pageable pageable) {
		// TODO Auto-generated method stub
		return dmPhucapRepository.findAllPhuCap(pageable);
	}
}
