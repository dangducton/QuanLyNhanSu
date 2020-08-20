package com.dangmailinh.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dangmailinh.entities.DmQuanhe;

@Repository
public interface DmQuanheRepository extends CrudRepository<DmQuanhe, Integer> {
	@Query(value = "SELECT * FROM dm_quanhe u WHERE u.status = 1", nativeQuery = true)
	List<DmQuanhe> findAllActiveQuanHeNative();

	@Query(value = "SELECT count(*) FROM dm_quanhe", nativeQuery = true)
	int countQuanHe();

	@Query(value = "SELECT * FROM dm_quanhe n where n.tenquanhe like ?1%", nativeQuery = true)
	List<DmQuanhe> findAllByTenQuanHe(String tenQuanHe);

	@Query(value = "SELECT * FROM dm_quanhe order by id desc", nativeQuery = true)
	Page<DmQuanhe> findAllQuanHe(Pageable pageable);
}
