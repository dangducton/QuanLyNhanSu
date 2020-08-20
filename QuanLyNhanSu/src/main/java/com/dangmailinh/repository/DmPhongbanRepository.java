package com.dangmailinh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dangmailinh.entities.DmPhongban;

@Repository
public interface DmPhongbanRepository extends CrudRepository<DmPhongban, Integer> {
	@Query(value = "SELECT * FROM dm_phongban u WHERE u.status = 1", nativeQuery = true)
	List<DmPhongban> findAllActivePhongBanNative();

	@Query(value = "SELECT count(*) FROM dm_phongban", nativeQuery = true)
	int countPhongBan();

	@Query(value = "SELECT * FROM dm_phongban n where n.tenphongban like ?1%", nativeQuery = true)
	List<DmPhongban> findAllByTenPhongBan(String tenphongban);
	
	
	@Query(value = "SELECT * FROM dm_phongban n where n.id = ?1", nativeQuery = true)
	DmPhongban findPhongBanByID(Integer id);
	
	@Query(value ="SELECT count(id) from dm_phongban where status = 1", nativeQuery = true)
	public long countphongban();
}
