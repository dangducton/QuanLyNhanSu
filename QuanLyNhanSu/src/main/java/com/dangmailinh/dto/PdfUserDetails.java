package com.dangmailinh.dto;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.dangmailinh.entities.User;

public class PdfUserDetails implements UserDetails {
    
	private static final long serialVersionUID = 1L;
	private User user;
	
    public PdfUserDetails(User user) {
        this.user = user;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getRoleCollection().stream().map(authority -> new SimpleGrantedAuthority(authority.getTenquyen().toString())).collect(Collectors.toList());
    }
    public long getId() {
        return user.getId();
    }
    @Override
    public String getPassword() {
        return user.getMatkhau();
    }
    @Override
    public String getUsername() {
        return user.getTendangnhap();
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
    public User getUserDetails() {
        return user;
    }

}
