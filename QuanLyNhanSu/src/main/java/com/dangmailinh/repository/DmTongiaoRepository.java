package com.dangmailinh.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dangmailinh.entities.DmTongiao;
@Repository
public interface DmTongiaoRepository extends CrudRepository<DmTongiao, Integer>{
	@Query(value = "SELECT * FROM dm_tongiao u WHERE u.status = 1", nativeQuery = true)
	List<DmTongiao> findAllActiveTonGiaoNative();

	@Query(value = "SELECT count(*) FROM dm_tongiao", nativeQuery = true)
	int countTonGiao();

	@Query(value = "SELECT * FROM dm_tongiao n where n.tentongiao like ?1%", nativeQuery = true)
	List<DmTongiao> findAllByTenTonGiao(String tenTonGiao);

	@Query(value = "SELECT * FROM dm_tongiao order by id desc", nativeQuery = true)
	Page<DmTongiao> findAllTonGiao(Pageable pageable);
}
