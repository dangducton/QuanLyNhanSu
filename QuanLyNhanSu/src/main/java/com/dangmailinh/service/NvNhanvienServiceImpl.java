package com.dangmailinh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dangmailinh.entities.NvNhanvien;
import com.dangmailinh.repository.NvNhanvienRepository;

@Service
@Transactional
public class NvNhanvienServiceImpl implements NvNhanvienService{
	@Autowired(required = true)
	public NvNhanvienRepository nvNhanvienRepository;

	@Override
	public Optional<NvNhanvien> findById(Integer id) {
		// TODO Auto-generated method stub
		return nvNhanvienRepository.findById(id);
	}

	@Override
	public void save(NvNhanvien nv) {
		// TODO Auto-generated method stub
		nvNhanvienRepository.save(nv);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		nvNhanvienRepository.deleteById(id);
	}

	@Override
	public int findMaxID() {
		// TODO Auto-generated method stub
		return nvNhanvienRepository.countNhanVien();
	}

	@Override
	public Page<NvNhanvien> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return nvNhanvienRepository.findAll(pageable);
	}

	@Override
	public NvNhanvien findNhanVienByID(Integer id) {
		// TODO Auto-generated method stub
		return nvNhanvienRepository.findNhanVienByID(id);
	}

	@Override
	public NvNhanvien findNhanVienByIDNhanVien(String id) {
		// TODO Auto-generated method stub
		return nvNhanvienRepository.findNhanVienByIDNhanVien(id);
	}

	@Override
	public List<NvNhanvien> findAllNhanVienAcctive() {
		// TODO Auto-generated method stub
		return nvNhanvienRepository.findAllNhanVienAcctive();
	}

	@Override
	public Page<NvNhanvien> findAllByTenNhanVien(String tennhanvien, Pageable pageable) {
		// TODO Auto-generated method stub
		return nvNhanvienRepository.findAllByTenNhanVien(tennhanvien, pageable);
	}

	@Override
	public int countNhanVien() {
		// TODO Auto-generated method stub
		return nvNhanvienRepository.countNhanVien();
	}

	@Override
	public Page<NvNhanvien> findAllNhanVienTheoChucDanh(Integer id, Pageable pageable) {
		// TODO Auto-generated method stub
		return nvNhanvienRepository.findAllNhanVienTheoChucDanh(id, pageable);
	}

	@Override
	public Page<NvNhanvien> findAllNhanVienTheoTrinhDo(Integer id, Pageable pageable) {
		// TODO Auto-generated method stub
		return nvNhanvienRepository.findAllNhanVienTheoTrinhDo(id, pageable);
	}

	@Override
	public Page<NvNhanvien> findAllNhanVienTheoPhongBan(Integer id, Pageable pageable) {
		// TODO Auto-generated method stub
		return nvNhanvienRepository.findAllNhanVienTheoPhongBan(id, pageable);
	}

	@Override
	public Page<NvNhanvien> findAllNhanVienTheoChuyenNganh(Integer id, Pageable pageable) {
		// TODO Auto-generated method stub
		return nvNhanvienRepository.findAllNhanVienTheoChuyenNganh(id, pageable);
	}

	@Override
	public List<NvNhanvien> findAllNhanVienBaoCaoTheoChucDanh(Integer id) {
		// TODO Auto-generated method stub
		return nvNhanvienRepository.findAllNhanVienBaoCaoTheoChucDanh(id);
	}

	@Override
	public List<NvNhanvien> findAllNhanVienBaoCaoTheoTrinhDo(Integer id) {
		// TODO Auto-generated method stub
		return nvNhanvienRepository.findAllNhanVienBaoCaoTheoTrinhDo(id);
	}

	@Override
	public List<NvNhanvien> findAllNhanVienBaoCaoTheoPhongBan(Integer id) {
		// TODO Auto-generated method stub
		return nvNhanvienRepository.findAllNhanVienBaoCaoTheoPhongBan(id);
	}

	@Override
	public List<NvNhanvien> findAllNhanVienBaoCaoTheoChuyenNganh(Integer id) {
		// TODO Auto-generated method stub
		return nvNhanvienRepository.findAllNhanVienBaoCaoTheoChuyenNganh(id);
	}

	@Override
	public long countnhanvien() {
		// TODO Auto-generated method stub
		return nvNhanvienRepository.countnhanvien();
	}

}
