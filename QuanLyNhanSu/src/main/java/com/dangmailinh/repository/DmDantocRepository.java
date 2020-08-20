package com.dangmailinh.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dangmailinh.entities.DmDantoc;

@Repository
public interface DmDantocRepository extends CrudRepository<DmDantoc, Integer>{
	@Query(value = "SELECT * FROM dm_dantoc u WHERE u.status = 1", nativeQuery = true)
	List<DmDantoc> findAllActiveDanTocNative();

	@Query(value = "SELECT count(*) FROM dm_dantoc", nativeQuery = true)
	int countDanToc();

	@Query(value = "SELECT * FROM dm_dantoc n where n.tendantoc like ?1%", nativeQuery = true)
	List<DmDantoc> findAllByTenDanToc(String tenDanToc);
	@Query(
			  value = "SELECT * FROM dm_dantoc", 
			  nativeQuery = true)
	Page<DmDantoc> findAllByDanToc(Pageable pageable);
}
