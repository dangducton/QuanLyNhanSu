package com.dangmailinh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dangmailinh.entities.DmTrinhdo;
import com.dangmailinh.repository.DmTrinhdoRepository;

@Service
@Transactional
public class DmTrinhdoServiceImpl implements DmTrinhdoService{
	@Autowired(required = true)
	public DmTrinhdoRepository dmTrinhdoRepository;

	@Override
	public Iterable<DmTrinhdo> findAll() {
		// TODO Auto-generated method stub
		return dmTrinhdoRepository.findAll();
	}

	@Override
	public Optional<DmTrinhdo> findById(Integer id) {
		// TODO Auto-generated method stub
		return dmTrinhdoRepository.findById(id);
	}

	@Override
	public void save(DmTrinhdo trinhDo) {
		// TODO Auto-generated method stub
		dmTrinhdoRepository.save(trinhDo);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		dmTrinhdoRepository.deleteById(id);
	}

	@Override
	public List<DmTrinhdo> findAllActiveDmTrinhDoNative() {
		// TODO Auto-generated method stub
		return dmTrinhdoRepository.findAllActiveDmTrinhDoNative();
	}

	@Override
	public int countTrinhDo() {
		// TODO Auto-generated method stub
		return dmTrinhdoRepository.countTrinhDo();
	}

	@Override
	public List<DmTrinhdo> findAllByTenTrinhDo(String tenTrinhDo) {
		// TODO Auto-generated method stub
		return dmTrinhdoRepository.findAllByTenTrinhDo(tenTrinhDo);
	}

	@Override
	public Page<DmTrinhdo> findAllByTrinhDo(Pageable pageable) {
		// TODO Auto-generated method stub
		return dmTrinhdoRepository.findAllTrinhDo(pageable);
	}

	@Override
	public DmTrinhdo findAllTrinhDo(Integer id) {
		// TODO Auto-generated method stub
		return dmTrinhdoRepository.findTrinhDoById(id);
	}
}
