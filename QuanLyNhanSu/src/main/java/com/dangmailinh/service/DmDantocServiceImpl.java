package com.dangmailinh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dangmailinh.entities.DmDantoc;
import com.dangmailinh.repository.DmDantocRepository;

@Service
@Transactional
public class DmDantocServiceImpl implements DmDantocService {
	@Autowired(required = true)
	public DmDantocRepository dmDantocRepository;

	@Override
	public Iterable<DmDantoc> findAll() {
		// TODO Auto-generated method stub
		return dmDantocRepository.findAll();
	}

	@Override
	public Optional<DmDantoc> findById(Integer id) {
		// TODO Auto-generated method stub
		return dmDantocRepository.findById(id);
	}

	@Override
	public void save(DmDantoc danToc) {
		// TODO Auto-generated method stub
		dmDantocRepository.save(danToc);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		dmDantocRepository.deleteById(id);
	}

	@Override
	public List<DmDantoc> findAllActiveDmDanTocNative() {
		// TODO Auto-generated method stub
		return dmDantocRepository.findAllActiveDanTocNative();
	}

	@Override
	public int countDanToc() {
		// TODO Auto-generated method stub
		return dmDantocRepository.countDanToc();
	}

	@Override
	public List<DmDantoc> findAllByTenDanToc(String tenDanToc) {
		// TODO Auto-generated method stub
		return dmDantocRepository.findAllByTenDanToc(tenDanToc);
	}

	@Override
	public Page<DmDantoc> findAllByDanToc(Pageable pageable) {
		// TODO Auto-generated method stub
		return dmDantocRepository.findAllByDanToc(pageable);
	}
}
