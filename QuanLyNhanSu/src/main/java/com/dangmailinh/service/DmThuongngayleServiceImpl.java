package com.dangmailinh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dangmailinh.entities.DmThuongngayle;
import com.dangmailinh.repository.DmThuongngayleRepository;

@Service
@Transactional
public class DmThuongngayleServiceImpl implements DmThuongngayleService{
	@Autowired(required = true)
	public DmThuongngayleRepository dmThuongngayleRepository;

	@Override
	public Iterable<DmThuongngayle> findAll() {
		// TODO Auto-generated method stub
		return dmThuongngayleRepository.findAll();
	}

	@Override
	public Optional<DmThuongngayle> findById(Integer id) {
		// TODO Auto-generated method stub
		return dmThuongngayleRepository.findById(id);
	}

	@Override
	public void save(DmThuongngayle thuongNgayLe) {
		// TODO Auto-generated method stub
		dmThuongngayleRepository.save(thuongNgayLe);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		dmThuongngayleRepository.deleteById(id);
	}

	@Override
	public List<DmThuongngayle> findAllActiveDmThuongNgayLeNative() {
		// TODO Auto-generated method stub
		return dmThuongngayleRepository.findAllActiveNgayLeNative();
	}

	@Override
	public int countQuanHe() {
		// TODO Auto-generated method stub
		return dmThuongngayleRepository.countNgayLe();
	}

	@Override
	public List<DmThuongngayle> findAllByTenThuongNgayLe(String tenNgayLe) {
		// TODO Auto-generated method stub
		return dmThuongngayleRepository.findAllByTenNgayLe(tenNgayLe);
	}

	@Override
	public Page<DmThuongngayle> findAllByThuongNgayLe(Pageable pageable) {
		// TODO Auto-generated method stub
		return dmThuongngayleRepository.findAllNgayLe(pageable);
	}

	@Override
	public DmThuongngayle findNgayLeById(Integer id) {
		// TODO Auto-generated method stub
		return dmThuongngayleRepository.findNgayLeById(id);
	}

}
