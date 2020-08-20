package com.dangmailinh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dangmailinh.entities.DmTongiao;
import com.dangmailinh.repository.DmTongiaoRepository;

@Service
@Transactional
public class DmTongiaoServiceImpl implements DmTongiaoService {
	@Autowired(required = true)
	public DmTongiaoRepository dmTongiaoRepository;

	@Override
	public Iterable<DmTongiao> findAll() {
		// TODO Auto-generated method stub
		return dmTongiaoRepository.findAll();
	}

	@Override
	public Optional<DmTongiao> findById(Integer id) {
		// TODO Auto-generated method stub
		return dmTongiaoRepository.findById(id);
	}

	@Override
	public void save(DmTongiao tonGiao) {
		// TODO Auto-generated method stub
		dmTongiaoRepository.save(tonGiao);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		dmTongiaoRepository.deleteById(id);
	}

	@Override
	public List<DmTongiao> findAllActiveDmTonGiaoNative() {
		// TODO Auto-generated method stub
		return dmTongiaoRepository.findAllActiveTonGiaoNative();
	}

	@Override
	public int countTonGiao() {
		// TODO Auto-generated method stub
		return dmTongiaoRepository.countTonGiao();
	}

	@Override
	public List<DmTongiao> findAllByTenTonGiao(String tenTonGiao) {
		// TODO Auto-generated method stub
		return dmTongiaoRepository.findAllByTenTonGiao(tenTonGiao);
	}

	@Override
	public Page<DmTongiao> findAllByTonGiao(Pageable pageable) {
		// TODO Auto-generated method stub
		return dmTongiaoRepository.findAllTonGiao(pageable);
	}
}
