package com.tspsoft.userservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tspsoft.userservice.entity.UserEntity;

@Repository
public interface IUserRepository extends CrudRepository<UserEntity, Long> {
	UserEntity findByEmail(String email);
}
