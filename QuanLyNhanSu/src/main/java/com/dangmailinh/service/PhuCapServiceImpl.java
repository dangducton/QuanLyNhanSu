
package com.dangmailinh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dangmailinh.entities.PhuCap;
import com.dangmailinh.repository.PhuCapRepository;

@Service
@Transactional
public class PhuCapServiceImpl implements PhuCapService {
	@Autowired(required = true)
	public PhuCapRepository phuCapRepository;

	@Override
	public Iterable<PhuCap> findAll() {
		// TODO Auto-generated method stub
		return phuCapRepository.findAll();
	}

	@Override
	public Optional<PhuCap> findById(Integer id) {
		// TODO Auto-generated method stub
		return phuCapRepository.findById(id);
	}

	@Override
	public void save(PhuCap phuCap) {
		// TODO Auto-generated method stub
		phuCapRepository.save(phuCap);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		phuCapRepository.deleteById(id);
	}

	@Override
	public int countPhuCap() {
		// TODO Auto-generated method stub
		return phuCapRepository.countPhuCap();
	}

	@Override
	public Page<PhuCap> findAllByPhuCap(Pageable pageable) {
		// TODO Auto-generated method stub
		return phuCapRepository.findAll(pageable);
	}

	@Override
	public PhuCap findAllById(Integer id) {
		// TODO Auto-generated method stub
		return phuCapRepository.findAllById(id);
	}

	@Override
	public List<PhuCap> findAllByTamUng(Integer id, Integer nam, Integer thang) {
		// TODO Auto-generated method stub
		return phuCapRepository.findAllByTamUng(id, nam, thang);
	}

	@Override
	public List<PhuCap> findAllActivePhuCapNative() {
		// TODO Auto-generated method stub
		return phuCapRepository.findAllActivePhuCapNative();
	}
}
