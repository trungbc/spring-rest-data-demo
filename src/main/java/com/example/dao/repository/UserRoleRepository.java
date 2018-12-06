package com.example.dao.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.dao.entity.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer>, CrudRepository<UserRole, Integer>{
	
	public List<UserRole> findByUserId(int userId);

}
