package com.dangmailinh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dangmailinh.entities.TlBangluong;
import com.dangmailinh.repository.TlBangluongRepository;

@Service
@Transactional
public class TIBangluongServiceImpl implements TIBangluongService {
	@Autowired(required = true)
	public TlBangluongRepository tlBangluongRepository;

	@Override
	public Iterable<TlBangluong> findAll() {
		// TODO Auto-generated method stub
		return tlBangluongRepository.findAll();
	}

	@Override
	public Optional<TlBangluong> findById(Integer id) {
		// TODO Auto-generated method stub
		return tlBangluongRepository.findById(id);
	}

	@Override
	public void save(TlBangluong phuCap) {
		// TODO Auto-generated method stub
		tlBangluongRepository.save(phuCap);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		tlBangluongRepository.deleteById(id);
	}

	@Override
	public List<TlBangluong> findAllActiveBangLuongNative() {
		// TODO Auto-generated method stub
		return tlBangluongRepository.findAllActiveBangLuongNative();
	}

	@Override
	public int countQuyetDinhThangChuc() {
		// TODO Auto-generated method stub
		return tlBangluongRepository.countBangLuong();
	}

	@Override
	public Page<TlBangluong> findAllByBangLuong(Pageable pageable) {
		// TODO Auto-generated method stub
		return tlBangluongRepository.findAllByBangLuong(pageable);
	}

	@Override
	public TlBangluong findAllByBangLuong(Integer id, Integer nam, Integer thang) {
		// TODO Auto-generated method stub
		return tlBangluongRepository.findAllByBangLuong(id, nam, thang);
	}

	@Override
	public TlBangluong findAllById(Integer id) {
		// TODO Auto-generated method stub
		return tlBangluongRepository.findAllById(id);
	}

	@Override
	public List<TlBangluong> findAllBaoCaoLuongTheoPhongBan(Integer id,Integer thang,Integer nam) {
		// TODO Auto-generated method stub
		return tlBangluongRepository.findAllBaoCaoLuongTheoPhongBan(id,thang,nam);
	}

	@Override
	public List<TlBangluong> findAllBaoCaoLuongTheoChucDanh(Integer id,Integer thang,Integer nam) {
		// TODO Auto-generated method stub
		return tlBangluongRepository.findAllBaoCaoLuongTheoChucDanh(id,thang,nam);
	}

	@Override
	public List<TlBangluong> findAllBaoCaoLuongTheoThang(Integer thang, Integer nam) {
		// TODO Auto-generated method stub
		return tlBangluongRepository.findAllBaoCaoLuongTheoThang(thang, nam);
	}
}
