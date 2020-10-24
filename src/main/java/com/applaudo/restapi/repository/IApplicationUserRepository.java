package com.applaudo.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.applaudo.restapi.model.User;

public interface IApplicationUserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
}