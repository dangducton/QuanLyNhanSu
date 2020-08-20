package com.dangmailinh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dangmailinh.entities.NvQuyetdinhchamduthopdong;
import com.dangmailinh.repository.NvQuyetdinhchamduthopdongRepository;

@Service
@Transactional
public class NvQuyetdinhchamduthopdongServiceImpl implements NvQuyetdinhchamduthopdongService{
	@Autowired(required = true)
	public NvQuyetdinhchamduthopdongRepository nvQuyetdinhchamduthopdongRepository;

	@Override
	public Iterable<NvQuyetdinhchamduthopdong> findAll() {
		// TODO Auto-generated method stub
		return nvQuyetdinhchamduthopdongRepository.findAll();
	}

	@Override
	public Optional<NvQuyetdinhchamduthopdong> findById(Integer id) {
		// TODO Auto-generated method stub
		return nvQuyetdinhchamduthopdongRepository.findById(id);
	}

	@Override
	public void save(NvQuyetdinhchamduthopdong phuCap) {
		// TODO Auto-generated method stub
		nvQuyetdinhchamduthopdongRepository.save(phuCap);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		nvQuyetdinhchamduthopdongRepository.deleteById(id);
	}

	@Override
	public List<NvQuyetdinhchamduthopdong> findAllActiveQuyetDinhChamDutHopDongNative() {
		// TODO Auto-generated method stub
		return nvQuyetdinhchamduthopdongRepository.findAllActiveQuyetDinhChamDutHopDongNative();
	}

	@Override
	public int countQuaTrinhCongTac() {
		// TODO Auto-generated method stub
		return nvQuyetdinhchamduthopdongRepository.countQuyetDinhChamDutHopDong();
	}

	@Override
	public Page<NvQuyetdinhchamduthopdong> findAllByQuyetDinhChamDutHopDong(Pageable pageable) {
		// TODO Auto-generated method stub
		return nvQuyetdinhchamduthopdongRepository.findAllByQuyetDinhChamDutHopDong(pageable);
	}

	@Override
	public NvQuyetdinhchamduthopdong findAllByQuyetDinhChamDutHopDong(Integer id) {
		// TODO Auto-generated method stub
		return nvQuyetdinhchamduthopdongRepository.findAllByQuyetDinhChamDutHopDong(id);
	}

	@Override
	public NvQuyetdinhchamduthopdong findAllById(Integer id) {
		// TODO Auto-generated method stub
		return nvQuyetdinhchamduthopdongRepository.findAllById(id);
	}

	@Override
	public long countquyetdinhchamduthopdong() {
		// TODO Auto-generated method stub
		return nvQuyetdinhchamduthopdongRepository.countquyetdinhchamduthopdong();
	}

}
