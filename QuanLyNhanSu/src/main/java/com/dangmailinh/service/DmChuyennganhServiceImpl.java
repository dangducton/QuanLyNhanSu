package com.dangmailinh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dangmailinh.entities.DmChuyennganh;
import com.dangmailinh.repository.DmChuyennganhRepository;
@Service
@Transactional
public class DmChuyennganhServiceImpl implements DmChuyennganhService{
	@Autowired(required = true)
	public DmChuyennganhRepository dmChuyennganhRepository;
	
	@Override
	public Iterable<DmChuyennganh> findAll() {
		// TODO Auto-generated method stub
		return dmChuyennganhRepository.findAll();
	}

	@Override
	public Optional<DmChuyennganh> findById(Integer id) {
		// TODO Auto-generated method stub
		return dmChuyennganhRepository.findById(id);
	}

	@Override
	public void save(DmChuyennganh chuyenNganh) {
		// TODO Auto-generated method stub
		dmChuyennganhRepository.save(chuyenNganh);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		dmChuyennganhRepository.deleteById(id);
	}

	@Override
	public List<DmChuyennganh> findAllActiveDmChuyenNganhNative() {
		// TODO Auto-generated method stub
		return dmChuyennganhRepository.findAllActiveChuyenNganhNative();
	}

	@Override
	public int countChucDanh() {
		// TODO Auto-generated method stub
		return dmChuyennganhRepository.countChucDanh();
	}

	@Override
	public List<DmChuyennganh> findAllByTenChuyenNganh(String tenChuyenNganh) {
		// TODO Auto-generated method stub
		return dmChuyennganhRepository.findAllByTenChuyenNganh(tenChuyenNganh);
	}

	@Override
	public DmChuyennganh findChuyenNganhById(Integer id) {
		// TODO Auto-generated method stub
		return dmChuyennganhRepository.findChuyenNganhById(id);
	}

}
