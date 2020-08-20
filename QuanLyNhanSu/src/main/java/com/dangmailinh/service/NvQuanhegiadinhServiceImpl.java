package com.dangmailinh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dangmailinh.entities.NvQuanhegiadinh;
import com.dangmailinh.repository.NvQuanhegiadinhRepository;

@Service
@Transactional
public class NvQuanhegiadinhServiceImpl implements NvQuanhegiadinhService {
	@Autowired(required = true)
	public NvQuanhegiadinhRepository nvQuanhegiadinhRepository;

	@Override
	public Iterable<NvQuanhegiadinh> findAll() {
		// TODO Auto-generated method stub
		return nvQuanhegiadinhRepository.findAll();
	}

	@Override
	public Optional<NvQuanhegiadinh> findById(Integer id) {
		// TODO Auto-generated method stub
		return nvQuanhegiadinhRepository.findById(id);
	}

	@Override
	public void save(NvQuanhegiadinh phuCap) {
		// TODO Auto-generated method stub
		nvQuanhegiadinhRepository.save(phuCap);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		nvQuanhegiadinhRepository.deleteById(id);
	}

	@Override
	public List<NvQuanhegiadinh> findAllActiveDmPhuCapNative() {
		// TODO Auto-generated method stub
		return nvQuanhegiadinhRepository.findAllActiveQuanHeGiaDinhNative();
	}

	@Override
	public int countPhuCap() {
		// TODO Auto-generated method stub
		return nvQuanhegiadinhRepository.countQuanHeGiaDinh();
	}

	@Override
	public Page<NvQuanhegiadinh> findAllByPhuCap(Pageable pageable) {
		// TODO Auto-generated method stub
		return nvQuanhegiadinhRepository.findAllByQuanHeGiaDinh(pageable);
	}

	@Override
	public List<NvQuanhegiadinh> findAllByQuanHeGiaDinh(Integer id) {
		// TODO Auto-generated method stub
		return nvQuanhegiadinhRepository.findAllByQuanHeGiaDinh(id);
	}

	@Override
	public NvQuanhegiadinh findAllById(Integer id) {
		// TODO Auto-generated method stub
		return nvQuanhegiadinhRepository.findAllById(id);
	}
}
