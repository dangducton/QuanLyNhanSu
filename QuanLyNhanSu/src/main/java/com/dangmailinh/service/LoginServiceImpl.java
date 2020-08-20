package com.dangmailinh.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dangmailinh.dto.PdfUserDetails;
import com.dangmailinh.entities.User;
import com.dangmailinh.repository.UserRepository;

@Service
@Transactional
public class LoginServiceImpl implements LoginService{
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String tendangnhap) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepository.findBytendangnhap(tendangnhap);
		if(user == null) {
			throw new UsernameNotFoundException("User not found for "+tendangnhap);
		}
		return new PdfUserDetails(user);
	}
	
}