package com.dangmailinh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dangmailinh.entities.DmTinhtranghonnhan;
import com.dangmailinh.repository.DmTinhtranghonnhanRepository;

@Service
@Transactional
public class DmTinhtranghonnhanServiceImpl implements DmTinhtranghonnhanService{
	@Autowired(required = true)
	public DmTinhtranghonnhanRepository dmTinhtranghonnhanRepository;

	@Override
	public Iterable<DmTinhtranghonnhan> findAll() {
		// TODO Auto-generated method stub
		return dmTinhtranghonnhanRepository.findAll();
	}

	@Override
	public Optional<DmTinhtranghonnhan> findById(Integer id) {
		// TODO Auto-generated method stub
		return dmTinhtranghonnhanRepository.findById(id);
	}

	@Override
	public void save(DmTinhtranghonnhan tinhTrangHonNhan) {
		// TODO Auto-generated method stub
		dmTinhtranghonnhanRepository.save(tinhTrangHonNhan);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		dmTinhtranghonnhanRepository.deleteById(id);
	}

	@Override
	public List<DmTinhtranghonnhan> findAllActiveDmTinhTrangHonNhanNative() {
		// TODO Auto-generated method stub
		return dmTinhtranghonnhanRepository.findAllActiveTinhTrangHonNhanNative();
	}

	@Override
	public int countQuanHe() {
		// TODO Auto-generated method stub
		return dmTinhtranghonnhanRepository.countTinhtranghonnhan();
	}

	@Override
	public List<DmTinhtranghonnhan> findAllByTenTinhTrangHonNhan(String tenTinhTrangHonNhan) {
		// TODO Auto-generated method stub
		return dmTinhtranghonnhanRepository.findAllByTinhTrangHonNhan(tenTinhTrangHonNhan);
	}

	@Override
	public Page<DmTinhtranghonnhan> findAllByTinhTrangHonNhan(Pageable pageable) {
		// TODO Auto-generated method stub
		return dmTinhtranghonnhanRepository.findAllTinhTrangHonNhan(pageable);
	}

	@Override
	public DmTinhtranghonnhan findTinhTrangHonNhanById(Integer id) {
		// TODO Auto-generated method stub
		return dmTinhtranghonnhanRepository.findTinhTrangHonNhanById(id);
	}
}
