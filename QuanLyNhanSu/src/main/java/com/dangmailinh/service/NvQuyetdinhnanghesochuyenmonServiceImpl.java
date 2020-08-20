package com.dangmailinh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dangmailinh.entities.NvQuyetdinhnanghesochuyenmon;
import com.dangmailinh.repository.NvQuyetdinhnanghesochuyenmonRepository;

@Service
@Transactional
public class NvQuyetdinhnanghesochuyenmonServiceImpl implements NvQuyetdinhnanghesochuyenmonService {
	@Autowired(required = true)
	public NvQuyetdinhnanghesochuyenmonRepository nvQuyetdinhnanghesochuyenmonRepository;

	@Override
	public Iterable<NvQuyetdinhnanghesochuyenmon> findAll() {
		// TODO Auto-generated method stub
		return nvQuyetdinhnanghesochuyenmonRepository.findAll();
	}

	@Override
	public Optional<NvQuyetdinhnanghesochuyenmon> findById(Integer id) {
		// TODO Auto-generated method stub
		return nvQuyetdinhnanghesochuyenmonRepository.findById(id);
	}

	@Override
	public void save(NvQuyetdinhnanghesochuyenmon phuCap) {
		// TODO Auto-generated method stub
		nvQuyetdinhnanghesochuyenmonRepository.save(phuCap);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		nvQuyetdinhnanghesochuyenmonRepository.deleteById(id);
	}

	@Override
	public List<NvQuyetdinhnanghesochuyenmon> findAllActiveQuyetDinhNangHeSoChuyenMonNative() {
		// TODO Auto-generated method stub
		return nvQuyetdinhnanghesochuyenmonRepository.findAllActiveQuyetDinhNangHeSoChuyenMonNative();
	}

	@Override
	public int countQuyetDinhNangHeSoChuyenMon() {
		// TODO Auto-generated method stub
		return nvQuyetdinhnanghesochuyenmonRepository.countQuyetDinhKhenThuong();
	}

	@Override
	public Page<NvQuyetdinhnanghesochuyenmon> findAllByQuyetDinhNangHeSoChuyenMon(Pageable pageable) {
		// TODO Auto-generated method stub
		return nvQuyetdinhnanghesochuyenmonRepository.findAllByQuyetDinhNangHeSoChuyenMon(pageable);
	}

	@Override
	public NvQuyetdinhnanghesochuyenmon findAllByQuyetDinhNangHeSoChuyenMon(Integer id, Integer nam, Integer thang) {
		// TODO Auto-generated method stub
		return nvQuyetdinhnanghesochuyenmonRepository.findAllByQuyetDinhNangHeSoChuyenMon(id, nam, thang);
	}

	@Override
	public NvQuyetdinhnanghesochuyenmon findAllById(Integer id) {
		// TODO Auto-generated method stub
		return nvQuyetdinhnanghesochuyenmonRepository.findAllById(id);
	}

	@Override
	public long countquyetnanghesochuyenmon() {
		// TODO Auto-generated method stub
		return nvQuyetdinhnanghesochuyenmonRepository.countquyetnanghesochuyenmon();
	}

}
