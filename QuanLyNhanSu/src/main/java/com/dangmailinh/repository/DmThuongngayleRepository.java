package com.dangmailinh.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dangmailinh.entities.DmThuongngayle;
@Repository
public interface DmThuongngayleRepository extends CrudRepository<DmThuongngayle, Integer>{
	@Query(value = "SELECT * FROM dm_thuongngayle u WHERE u.status = 1", nativeQuery = true)
	List<DmThuongngayle> findAllActiveNgayLeNative();

	@Query(value = "SELECT count(*) FROM dm_thuongngayle", nativeQuery = true)
	int countNgayLe();

	@Query(value = "SELECT * FROM dm_thuongngayle n where n.tenngayle like ?1%", nativeQuery = true)
	List<DmThuongngayle> findAllByTenNgayLe(String tenNgayLe);

	@Query(value = "SELECT * FROM dm_thuongngayle order by id desc", nativeQuery = true)
	Page<DmThuongngayle> findAllNgayLe(Pageable pageable);
	
	@Query(value = "SELECT * FROM dm_thuongngayle where id =?1", nativeQuery = true)
	DmThuongngayle findNgayLeById(Integer id);
}
