package com.example.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.dao.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>, CrudRepository<Role, Integer> {

	
	
}
