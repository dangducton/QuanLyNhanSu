package com.dangmailinh.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.dangmailinh.entities.User;
import com.dangmailinh.repository.UserRepository;
import com.dangmailinh.util.PassWordUtil;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public User save(User nguoiDung) {
		nguoiDung.setStatus(1);
		if (StringUtils.hasText(nguoiDung.getMatkhau())) {
			nguoiDung.setMatkhau(PassWordUtil.getEncoderPassword(nguoiDung.getMatkhau()));
		}
		return userRepository.save(nguoiDung);
	}

	@Override
	public User findBytendangnhap(String tendangnhap) {
		// TODO Auto-generated method stub
		return userRepository.findBytendangnhap(tendangnhap);
	}

	@Override
	public Optional<User> findById(Integer id) {
		// TODO Auto-generated method stub
		return userRepository.findById(id);
	}

	@Override
	public void update(User dbNguoiDung) {
		// TODO Auto-generated method stub
		userRepository.save(dbNguoiDung);
	}

	@Override
	public User findBytendangnhapAndEnabled(String tendangnhap) {
		// TODO Auto-generated method stub
		return userRepository.findBytendangnhapAndEnabled(tendangnhap);
	}

	@Override
	public User findByIDNhanVien(Integer id, String tendangnhap){
		// TODO Auto-generated method stub
		return userRepository.findByIDNhanVien(id, tendangnhap);
	}

	@Override
	public Page<User> findAllQuanTri(Pageable pageable) {
		// TODO Auto-generated method stub
		return userRepository.findAllQuanTri(pageable);
	}

	@Override
	public long countquantri() {
		// TODO Auto-generated method stub
		return userRepository.countquantri();
	}
}
