package com.dangmailinh.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dangmailinh.entities.DmTinhtranghonnhan;
@Repository
public interface DmTinhtranghonnhanRepository extends CrudRepository<DmTinhtranghonnhan, Integer>{
	@Query(value = "SELECT * FROM dm_tinhtranghonnhan u WHERE u.status = 1", nativeQuery = true)
	List<DmTinhtranghonnhan> findAllActiveTinhTrangHonNhanNative();

	@Query(value = "SELECT count(*) FROM dm_tinhtranghonnhan", nativeQuery = true)
	int countTinhtranghonnhan();

	@Query(value = "SELECT * FROM dm_tinhtranghonnhan n where n.tentinhtranghonnhan like ?1%", nativeQuery = true)
	List<DmTinhtranghonnhan> findAllByTinhTrangHonNhan(String tenTinhTrangHonNhan);

	@Query(value = "SELECT * FROM dm_tinhtranghonnhan order by id desc", nativeQuery = true)
	Page<DmTinhtranghonnhan> findAllTinhTrangHonNhan(Pageable pageable);
	
	@Query(value = "SELECT * FROM dm_tinhtranghonnhan where id = ?1", nativeQuery = true)
	DmTinhtranghonnhan findTinhTrangHonNhanById(Integer id);
}
