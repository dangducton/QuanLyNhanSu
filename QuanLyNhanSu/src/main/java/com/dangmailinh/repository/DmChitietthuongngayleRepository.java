package com.dangmailinh.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.dangmailinh.entities.DmChitietthuongngayle;

@Repository
public interface DmChitietthuongngayleRepository extends CrudRepository<DmChitietthuongngayle, Integer> {
	@Query(value = "SELECT * FROM dm_chitietthuongngayle order by id desc", nativeQuery = true)
	Page<DmChitietthuongngayle> findAllChiTietThuongLe(Pageable pageable);
	
	@Query(value = "SELECT * FROM dm_chitietthuongngayle where id =?1", nativeQuery = true)
	DmChitietthuongngayle findByIdChiTietThuongLe(Integer id);
	
	@Query(value = "SELECT * FROM dm_chitietthuongngayle where id_chucdanh =?1", nativeQuery = true)
	List<DmChitietthuongngayle> findByIdChiTietThuongLeByChucDanh(Integer id);
}
