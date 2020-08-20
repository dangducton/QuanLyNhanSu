package com.dangmailinh.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dangmailinh.entities.DmTrinhdo;

@Repository
public interface DmTrinhdoRepository extends CrudRepository<DmTrinhdo, Integer> {
	@Query(value = "SELECT * FROM dm_trinhdo u WHERE u.status = 1", nativeQuery = true)
	List<DmTrinhdo> findAllActiveDmTrinhDoNative();

	@Query(value = "SELECT count(*) FROM dm_trinhdo", nativeQuery = true)
	int countTrinhDo();

	@Query(value = "SELECT * FROM dm_trinhdo n where n.tentrinhdo like ?1%", nativeQuery = true)
	List<DmTrinhdo> findAllByTenTrinhDo(String tenTrinhDo);

	@Query(value = "SELECT * FROM dm_trinhdo order by id desc", nativeQuery = true)
	Page<DmTrinhdo> findAllTrinhDo(Pageable pageable);
	
	@Query(value = "SELECT * FROM dm_trinhdo where id = ?1", nativeQuery = true)
	DmTrinhdo findTrinhDoById(Integer id);
}
