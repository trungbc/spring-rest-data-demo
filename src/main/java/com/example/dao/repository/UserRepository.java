package com.example.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.dao.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>, CrudRepository<User, Integer> {

	public User findByAccount(String account);

}
