package com.dangmailinh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dangmailinh.entities.DmChucdanh;
import com.dangmailinh.repository.DmChucdanhRepository;

@Service
@Transactional
public class DmChucdanhServiceImpl implements DmChucdanhService {
	@Autowired(required = true)
	public DmChucdanhRepository dmChucdanhRepository;

	@Override
	public Iterable<DmChucdanh> findAll() {
		// TODO Auto-generated method stub
		return dmChucdanhRepository.findAll();
	}

	@Override
	public Optional<DmChucdanh> findById(Integer id) {
		// TODO Auto-generated method stub
		return dmChucdanhRepository.findById(id);
	}

	@Override
	public void save(DmChucdanh chucDanh) {
		// TODO Auto-generated method stub
		dmChucdanhRepository.save(chucDanh);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		dmChucdanhRepository.deleteById(id);
	}

	@Override
	public List<DmChucdanh> findAllActiveDmChucDanhNative() {
		// TODO Auto-generated method stub
		return dmChucdanhRepository.findAllActiveChucDanhNative();
	}

	@Override
	public int countChucDanh() {
		// TODO Auto-generated method stub
		return dmChucdanhRepository.countChucDanh();
	}

	@Override
	public List<DmChucdanh> findAllByTenChucDanh(String tenChucDanh) {
		// TODO Auto-generated method stub
		return dmChucdanhRepository.findAllByChucDanh(tenChucDanh);
	}

	@Override
	public DmChucdanh findChucDanhByID(Integer id) {
		// TODO Auto-generated method stub
		return dmChucdanhRepository.findChucDanhByID(id);
	}

	@Override
	public long countchucdanh() {
		// TODO Auto-generated method stub
		return dmChucdanhRepository.countchucdanh();
	}

}
