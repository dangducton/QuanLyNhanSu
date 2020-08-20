package com.dangmailinh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dangmailinh.entities.NvQuyetdinhthangchuc;
import com.dangmailinh.repository.NvQuyetdinhthangchucRepository;

@Service
@Transactional
public class NvQuyetdinhthangchucServiceImpl implements NvQuyetdinhthangchucService {
	@Autowired(required = true)
	public NvQuyetdinhthangchucRepository nvQuyetdinhthangchucRepository;

	@Override
	public Iterable<NvQuyetdinhthangchuc> findAll() {
		// TODO Auto-generated method stub
		return nvQuyetdinhthangchucRepository.findAll();
	}

	@Override
	public Optional<NvQuyetdinhthangchuc> findById(Integer id) {
		// TODO Auto-generated method stub
		return nvQuyetdinhthangchucRepository.findById(id);
	}

	@Override
	public void save(NvQuyetdinhthangchuc phuCap) {
		// TODO Auto-generated method stub
		nvQuyetdinhthangchucRepository.save(phuCap);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		nvQuyetdinhthangchucRepository.deleteById(id);
	}

	@Override
	public List<NvQuyetdinhthangchuc> findAllActiveQuyetDinhThangChucnNative() {
		// TODO Auto-generated method stub
		return nvQuyetdinhthangchucRepository.findAllActiveQuyetDinhThangChucNative();
	}

	@Override
	public int countQuyetDinhThangChuc() {
		// TODO Auto-generated method stub
		return nvQuyetdinhthangchucRepository.countQuyetDinhKhenThuong();
	}

	@Override
	public Page<NvQuyetdinhthangchuc> findAllByQuyetDinhThangChuc(Pageable pageable) {
		// TODO Auto-generated method stub
		return nvQuyetdinhthangchucRepository.findAllByQuyetDinhNangThangChuc(pageable);
	}

	@Override
	public NvQuyetdinhthangchuc findAllByQuyetDinhThangChuc(Integer id, Integer nam, Integer thang) {
		// TODO Auto-generated method stub
		return nvQuyetdinhthangchucRepository.findAllByQuyetDinhThangChuc(id, nam, thang);
	}

	@Override
	public NvQuyetdinhthangchuc findAllById(Integer id) {
		// TODO Auto-generated method stub
		return nvQuyetdinhthangchucRepository.findAllById(id);
	}

	@Override
	public long countquyetdinhthangchuc() {
		// TODO Auto-generated method stub
		return nvQuyetdinhthangchucRepository.countquyetdinhthangchuc();
	}
}
