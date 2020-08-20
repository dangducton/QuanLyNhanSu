package com.dangmailinh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dangmailinh.entities.NvQuyetdinhkyluat;
import com.dangmailinh.repository.NvQuyetdinhkyluatRepository;

@Service
@Transactional
public class NvQuyetdinhkyluatServiceImpl implements NvQuyetdinhkyluatService {
	@Autowired(required = true)
	public NvQuyetdinhkyluatRepository nvQuyetdinhkyluatRepository;

	@Override
	public Iterable<NvQuyetdinhkyluat> findAll() {
		// TODO Auto-generated method stub
		return nvQuyetdinhkyluatRepository.findAll();
	}

	@Override
	public Optional<NvQuyetdinhkyluat> findById(Integer id) {
		// TODO Auto-generated method stub
		return nvQuyetdinhkyluatRepository.findById(id);
	}

	@Override
	public void save(NvQuyetdinhkyluat phuCap) {
		// TODO Auto-generated method stub
		nvQuyetdinhkyluatRepository.save(phuCap);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		nvQuyetdinhkyluatRepository.deleteById(id);
	}

	@Override
	public List<NvQuyetdinhkyluat> findAllActiveQuyetDinhKyLuatNative() {
		// TODO Auto-generated method stub
		return nvQuyetdinhkyluatRepository.findAllActiveQuyetDinhKyLuatNative();
	}

	@Override
	public int countKhenThuong() {
		// TODO Auto-generated method stub
		return nvQuyetdinhkyluatRepository.countQuyetDinhKyLuat();
	}

	@Override
	public Page<NvQuyetdinhkyluat> findAllByQuyetDinhKyLuat(Pageable pageable) {
		// TODO Auto-generated method stub
		return nvQuyetdinhkyluatRepository.findAllByQuyetDinhKyLuat(pageable);
	}

	@Override
	public NvQuyetdinhkyluat findAllByQuyetDinhKyLuat(Integer id, Integer nam, Integer thang, Integer idKyLuat) {
		// TODO Auto-generated method stub
		return nvQuyetdinhkyluatRepository.findAllByQuyetDinhKyLuat(id, nam, thang, idKyLuat);
	}

	@Override
	public NvQuyetdinhkyluat findAllById(Integer id) {
		// TODO Auto-generated method stub
		return nvQuyetdinhkyluatRepository.findAllById(id);
	}

	@Override
	public List<NvQuyetdinhkyluat> findAllKyLuat(Integer id, Integer nam, Integer thang) {
		// TODO Auto-generated method stub
		return nvQuyetdinhkyluatRepository.findAllKyLuat(id, nam, thang);
	}

	@Override
	public long countquyetdinhkyluat() {
		// TODO Auto-generated method stub
		return nvQuyetdinhkyluatRepository.countquyetdinhkyluat();
	}

	@Override
	public Page<NvQuyetdinhkyluat> findAllByKyLuatTheoNam(Integer nam, Pageable pageable) {
		// TODO Auto-generated method stub
		return nvQuyetdinhkyluatRepository.findAllByKyLuatTheoNam(nam, pageable);
	}

	@Override
	public List<NvQuyetdinhkyluat> findAllByKyLuatTheoNamNhanVien(Integer nam, Integer id) {
		// TODO Auto-generated method stub
		return nvQuyetdinhkyluatRepository.findAllByKyLuatTheoNamNhanVien(nam, id);
	}
}
