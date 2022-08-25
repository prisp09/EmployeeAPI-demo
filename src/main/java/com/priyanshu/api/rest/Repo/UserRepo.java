package com.priyanshu.api.rest.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.priyanshu.api.rest.Models.User;

public interface UserRepo extends JpaRepository<User, Long> {
	
}
