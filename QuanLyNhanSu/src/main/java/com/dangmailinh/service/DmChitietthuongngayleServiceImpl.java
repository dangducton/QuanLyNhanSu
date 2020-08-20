package com.dangmailinh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dangmailinh.entities.DmChitietthuongngayle;
import com.dangmailinh.repository.DmChitietthuongngayleRepository;

@Service
@Transactional
public class DmChitietthuongngayleServiceImpl implements DmChitietthuongngayleService{
	@Autowired(required = true)
	public DmChitietthuongngayleRepository dmChitietthuongngayleRepository;

	@Override
	public Iterable<DmChitietthuongngayle> findAll() {
		// TODO Auto-generated method stub
		return dmChitietthuongngayleRepository.findAll();
	}

	@Override
	public Optional<DmChitietthuongngayle> findById(Integer id) {
		// TODO Auto-generated method stub
		return dmChitietthuongngayleRepository.findById(id);
	}

	@Override
	public void save(DmChitietthuongngayle chiTietThuongLe) {
		// TODO Auto-generated method stub
		dmChitietthuongngayleRepository.save(chiTietThuongLe);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		dmChitietthuongngayleRepository.deleteById(id);
	}

	@Override
	public Page<DmChitietthuongngayle> findAllByChiTietThuongLe(Pageable pageable) {
		// TODO Auto-generated method stub
		return dmChitietthuongngayleRepository.findAllChiTietThuongLe(pageable);
	}

	@Override
	public DmChitietthuongngayle findByIdChiTietThuongLe(Integer id) {
		// TODO Auto-generated method stub
		return dmChitietthuongngayleRepository.findByIdChiTietThuongLe(id);
	}

	@Override
	public List<DmChitietthuongngayle> findByIdChiTietThuongLeByChucDanh(Integer id) {
		// TODO Auto-generated method stub
		return dmChitietthuongngayleRepository.findByIdChiTietThuongLeByChucDanh(id);
	}
}
