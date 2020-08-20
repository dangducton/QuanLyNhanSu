package com.dangmailinh.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.dangmailinh.entities.DmKyluat;

@Repository
public interface DmKyluatRepository extends CrudRepository<DmKyluat, Integer> {
	@Query(value = "SELECT * FROM dm_kyluat u WHERE u.status = 1", nativeQuery = true)
	List<DmKyluat> findAllActiveKyLuatNative();

	@Query(value = "SELECT count(*) FROM dm_kyluat", nativeQuery = true)
	int countKyLuat();

	@Query(value = "SELECT * FROM dm_kyluat n where n.tenkyluat like ?1%", nativeQuery = true)
	List<DmKyluat> findAllByTenKyLuat(String tenKyLuat);

	@Query(value = "SELECT * FROM dm_kyluat", nativeQuery = true)
	Page<DmKyluat> findAllKyLuat(Pageable pageable);
}
