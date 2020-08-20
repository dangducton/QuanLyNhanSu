package com.dangmailinh.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dangmailinh.entities.User;
@Repository
public interface UserRepository extends CrudRepository<User, Integer>{
	@Query(value = "SELECT * FROM user n where n.tendangnhap = ?1 and status = 1", nativeQuery = true)
	User findBytendangnhapAndEnabled(String tendangnhap);
	@Query(value = "SELECT * FROM user n where n.tendangnhap = ?1 and status = 1", nativeQuery = true)
	User findBytendangnhap(String tendangnhap);
	
	@Query(value = "SELECT * FROM user n where n.id_nhanvien = ?1 or n.tendangnhap", nativeQuery = true)
	User findByIDNhanVien(Integer id, String tendangnhap);

	@Query(
			  value = "SELECT * FROM user order by id desc", 
			  nativeQuery = true)
	Page<User> findAllQuanTri(Pageable pageable);
	
	@Query(value ="SELECT count(id) from user where status = 1", nativeQuery = true)
	public long countquantri();
}
