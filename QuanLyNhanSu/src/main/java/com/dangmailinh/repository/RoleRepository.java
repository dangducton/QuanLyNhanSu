package com.dangmailinh.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dangmailinh.entities.Role;
@Repository
public interface RoleRepository extends CrudRepository<Role, Integer>{
	@Query(value = "SELECT * FROM role n where n.tenquyen = ?1", nativeQuery = true)
	Role findByName(String name);
}
