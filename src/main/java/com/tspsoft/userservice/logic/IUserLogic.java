package com.tspsoft.userservice.logic;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.tspsoft.userservice.dto.UserRequest;
import com.tspsoft.userservice.dto.UserResponse;
import com.tspsoft.userservice.entity.UserEntity;

@Component
public interface IUserLogic {

	List<UserResponse> getAllUsers();

	void disableUser(UserEntity user);

	Optional<UserEntity> findById(Long userId);

	void updateUser(Long userId, UserRequest userDetails);

	void deleteById(Long userId);

	void saveUser(UserEntity user);
	
	UserEntity findByEmail(String email);
	
	void createUser(UserEntity user);

	void logout();
}
