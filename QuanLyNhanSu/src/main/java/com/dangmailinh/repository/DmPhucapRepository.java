package com.dangmailinh.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dangmailinh.entities.DmPhucap;
@Repository
public interface DmPhucapRepository extends CrudRepository<DmPhucap, Integer>{
	@Query(value = "SELECT * FROM dm_phucap u WHERE u.status = 1", nativeQuery = true)
	List<DmPhucap> findAllActivePhuCapNative();

	@Query(value = "SELECT count(*) FROM dm_phucap", nativeQuery = true)
	int countPhuCap();

	@Query(value = "SELECT * FROM dm_phucap n where n.tenphucap like ?1%", nativeQuery = true)
	List<DmPhucap> findAllByTenPhuCap(String tenPhuCap);
	
	@Query(value = "SELECT * FROM dm_phucap", nativeQuery = true)
	Page<DmPhucap> findAllPhuCap(Pageable pageable);
}
