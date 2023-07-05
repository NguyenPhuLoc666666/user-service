package com.tspsoft.userservice.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tspsoft.userservice.dto.UserRequest;
import com.tspsoft.userservice.dto.UserResponse;
import com.tspsoft.userservice.entity.UserEntity;

@Service
public interface IUserService {

	void updateUser(Long userId, UserRequest userDetails);

	Optional<UserEntity> findById(Long userId);

	void deleteById(Long userId);

	void saveUser(UserEntity user);

	List<UserResponse> getAllUsers();

	UserEntity findByEmail(String email);
	
	void createUser(UserEntity user);

	void logout();
}
