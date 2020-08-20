package com.dangmailinh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dangmailinh.entities.NvQuyetdinhkhenthuong;
import com.dangmailinh.repository.NvQuyetdinhkhenthuongRepository;

@Service
@Transactional
public class NvQuyetdinhkhenthuongServiceImpl implements NvQuyetdinhkhenthuongService {
	@Autowired(required = true)
	public NvQuyetdinhkhenthuongRepository nvQuyetdinhkhenthuongRepository;

	@Override
	public Iterable<NvQuyetdinhkhenthuong> findAll() {
		// TODO Auto-generated method stub
		return nvQuyetdinhkhenthuongRepository.findAll();
	}

	@Override
	public Optional<NvQuyetdinhkhenthuong> findById(Integer id) {
		// TODO Auto-generated method stub
		return nvQuyetdinhkhenthuongRepository.findById(id);
	}

	@Override
	public void save(NvQuyetdinhkhenthuong phuCap) {
		// TODO Auto-generated method stub
		nvQuyetdinhkhenthuongRepository.save(phuCap);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		nvQuyetdinhkhenthuongRepository.deleteById(id);
	}

	@Override
	public List<NvQuyetdinhkhenthuong> findAllActiveQuyetDinhKhenThuongNative() {
		// TODO Auto-generated method stub
		return nvQuyetdinhkhenthuongRepository.findAllActiveQuyetDinhKhenThuongNative();
	}

	@Override
	public int countKhenThuong() {
		// TODO Auto-generated method stub
		return nvQuyetdinhkhenthuongRepository.countQuyetDinhKhenThuong();
	}

	@Override
	public Page<NvQuyetdinhkhenthuong> findAllByQuyetDinhKhenThuong(Pageable pageable) {
		// TODO Auto-generated method stub
		return nvQuyetdinhkhenthuongRepository.findAllByQuyetDinhKhenThuong(pageable);
	}

	@Override
	public NvQuyetdinhkhenthuong findAllById(Integer id) {
		// TODO Auto-generated method stub
		return nvQuyetdinhkhenthuongRepository.findAllById(id);
	}

	@Override
	public NvQuyetdinhkhenthuong findAllByQuyetDinhKhenThuong(Integer id, Integer nam, Integer thang) {
		// TODO Auto-generated method stub
		return nvQuyetdinhkhenthuongRepository.findAllByQuyetDinhKhenThuong(id, nam, thang);
	}

	@Override
	public List<NvQuyetdinhkhenthuong> findAllByKhenThuong(Integer id, Integer nam, Integer thang) {
		// TODO Auto-generated method stub
		return nvQuyetdinhkhenthuongRepository.findAllByKhenThuong(id, nam, thang);
	}

	@Override
	public long countquyetdinhkhenthuong() {
		// TODO Auto-generated method stub
		return nvQuyetdinhkhenthuongRepository.countquyetdinhkhenthuong();
	}

	@Override
	public Page<NvQuyetdinhkhenthuong> findAllByKhenThuongTheoNam(Integer nam, Pageable pageable) {
		// TODO Auto-generated method stub
		return nvQuyetdinhkhenthuongRepository.findAllByKhenThuongTheoNam(nam,pageable);
	}

	@Override
	public List<NvQuyetdinhkhenthuong> findAllByKhenThuongTheoNamNhanVien(Integer nam, Integer id) {
		// TODO Auto-generated method stub
		return nvQuyetdinhkhenthuongRepository.findAllByKhenThuongTheoNamNhanVien(nam, id);
	}
}
