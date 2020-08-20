package com.dangmailinh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dangmailinh.entities.TlTamung;
import com.dangmailinh.repository.TlTamungRepository;

@Service
@Transactional
public class TITamungServiceImpl implements TITamungService {
	@Autowired(required = true)
	public TlTamungRepository TlTamungRepository;

	@Override
	public Iterable<TlTamung> findAll() {
		// TODO Auto-generated method stub
		return TlTamungRepository.findAll();
	}

	@Override
	public Optional<TlTamung> findById(Integer id) {
		// TODO Auto-generated method stub
		return TlTamungRepository.findById(id);
	}

	@Override
	public void save(TlTamung phuCap) {
		// TODO Auto-generated method stub
		TlTamungRepository.save(phuCap);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		TlTamungRepository.deleteById(id);
	}

	@Override
	public List<TlTamung> findAllActiveTamungNative() {
		// TODO Auto-generated method stub
		return TlTamungRepository.findAllActiveTamUngNative();
	}

	@Override
	public int countTamCung() {
		// TODO Auto-generated method stub
		return TlTamungRepository.countTamUng();
	}

	@Override
	public Page<TlTamung> findAllByTamung(Pageable pageable) {
		// TODO Auto-generated method stub
		return TlTamungRepository.findAllByTamUng(pageable);
	}

	@Override
	public TlTamung findAllById(Integer id) {
		// TODO Auto-generated method stub
		return TlTamungRepository.findAllById(id);
	}

	@Override
	public List<TlTamung> findAllByTamUng(Integer id, Integer nam, Integer thang) {
		// TODO Auto-generated method stub
		return TlTamungRepository.findAllByTamUng(id, nam, thang);
	}
}
