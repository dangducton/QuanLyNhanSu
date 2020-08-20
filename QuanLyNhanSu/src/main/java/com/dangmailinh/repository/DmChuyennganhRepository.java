package com.dangmailinh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dangmailinh.entities.DmChuyennganh;

@Repository
public interface DmChuyennganhRepository extends CrudRepository<DmChuyennganh, Integer> {
	@Query(value = "SELECT * FROM dm_chuyennganh u WHERE u.status = 1", nativeQuery = true)
	List<DmChuyennganh> findAllActiveChuyenNganhNative();

	@Query(value = "SELECT count(*) FROM dm_chuyennganh", nativeQuery = true)
	int countChucDanh();

	@Query(value = "SELECT * FROM dm_chuyennganh n where n.tenchuyennganh like ?1%", nativeQuery = true)
	List<DmChuyennganh> findAllByTenChuyenNganh(String tenChuyenNganh);
	
	@Query(value = "SELECT * FROM dm_chuyennganh where id = ?1", nativeQuery = true)
	DmChuyennganh findChuyenNganhById(Integer id);
}
