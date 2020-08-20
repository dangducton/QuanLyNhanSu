package com.dangmailinh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dangmailinh.entities.DmQuanhe;
import com.dangmailinh.repository.DmQuanheRepository;

@Service
@Transactional
public class DmQuanheServiceImpl implements DmQuanheService{
	@Autowired(required = true)
	public DmQuanheRepository dmQuanheRepository;

	@Override
	public Iterable<DmQuanhe> findAll() {
		// TODO Auto-generated method stub
		return dmQuanheRepository.findAll();
	}

	@Override
	public Optional<DmQuanhe> findById(Integer id) {
		// TODO Auto-generated method stub
		return dmQuanheRepository.findById(id);
	}

	@Override
	public void save(DmQuanhe quanHe) {
		// TODO Auto-generated method stub
		dmQuanheRepository.save(quanHe);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		dmQuanheRepository.deleteById(id);
	}

	@Override
	public List<DmQuanhe> findAllActiveDmQuanHeNative() {
		// TODO Auto-generated method stub
		return dmQuanheRepository.findAllActiveQuanHeNative();
	}

	@Override
	public int countQuanHe() {
		// TODO Auto-generated method stub
		return dmQuanheRepository.countQuanHe();
	}

	@Override
	public List<DmQuanhe> findAllByTenQuanHe(String tenQuanHe) {
		// TODO Auto-generated method stub
		return dmQuanheRepository.findAllByTenQuanHe(tenQuanHe);
	}

	@Override
	public Page<DmQuanhe> findAllByQuanHe(Pageable pageable) {
		// TODO Auto-generated method stub
		return dmQuanheRepository.findAllQuanHe(pageable);
	}
}
