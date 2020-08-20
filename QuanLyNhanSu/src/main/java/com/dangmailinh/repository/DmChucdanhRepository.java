package com.dangmailinh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dangmailinh.entities.DmChucdanh;

@Repository
public interface DmChucdanhRepository extends CrudRepository<DmChucdanh, Integer> {
	@Query(value = "SELECT * FROM dm_chucdanh u WHERE u.status = 1", nativeQuery = true)
	List<DmChucdanh> findAllActiveChucDanhNative();

	@Query(value = "SELECT count(*) FROM dm_chucdanh", nativeQuery = true)
	int countChucDanh();

	@Query(value = "SELECT * FROM dm_chucdanh n where n.tenchucdanh like ?1%", nativeQuery = true)
	List<DmChucdanh> findAllByChucDanh(String tenChucDanh);
	
	@Query(value = "SELECT * FROM dm_chucdanh n where n.id = ?1", nativeQuery = true)
	DmChucdanh findChucDanhByID(Integer id);
	
	@Query(value ="SELECT count(id) from dm_chucdanh where status = 1", nativeQuery = true)
	public long countchucdanh();
}
