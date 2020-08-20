package com.dangmailinh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dangmailinh.entities.NvQuatrinhcongtac;
import com.dangmailinh.repository.NvQuatrinhcongtacRepository;

@Service
@Transactional
public class NvQuatrinhcongtacServiceImpl implements NvQuatrinhcongtacService{
	@Autowired(required = true)
	public NvQuatrinhcongtacRepository nvQuatrinhcongtacRepository;

	@Override
	public Iterable<NvQuatrinhcongtac> findAll() {
		// TODO Auto-generated method stub
		return nvQuatrinhcongtacRepository.findAll();
	}

	@Override
	public Optional<NvQuatrinhcongtac> findById(Integer id) {
		// TODO Auto-generated method stub
		return nvQuatrinhcongtacRepository.findById(id);
	}

	@Override
	public void save(NvQuatrinhcongtac phuCap) {
		// TODO Auto-generated method stub
		nvQuatrinhcongtacRepository.save(phuCap);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		nvQuatrinhcongtacRepository.deleteById(id);
	}

	@Override
	public List<NvQuatrinhcongtac> findAllActiveQuaTrinhCongTacNative() {
		// TODO Auto-generated method stub
		return nvQuatrinhcongtacRepository.findAllActiveQuaTrinhCongTacNative();
	}

	@Override
	public int countQuaTrinhCongTac() {
		// TODO Auto-generated method stub
		return nvQuatrinhcongtacRepository.countQuaTrinhCongTac();
	}

	@Override
	public Page<NvQuatrinhcongtac> findAllByQuaTrinhCongTac(Pageable pageable) {
		// TODO Auto-generated method stub
		return nvQuatrinhcongtacRepository.findAllByQuaTrinhCongTac(pageable);
	}

	@Override
	public List<NvQuatrinhcongtac> findAllByQuaTrinhCongTac(Integer id) {
		// TODO Auto-generated method stub
		return nvQuatrinhcongtacRepository.findAllByQuaTrinhCongTac(id);
	}

	@Override
	public NvQuatrinhcongtac findAllById(Integer id) {
		// TODO Auto-generated method stub
		return nvQuatrinhcongtacRepository.findAllById(id);
	}

	

}
