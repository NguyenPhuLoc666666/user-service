package com.tspsoft.userservice.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tspsoft.userservice.dto.UserRequest;
import com.tspsoft.userservice.dto.UserResponse;
import com.tspsoft.userservice.entity.UserEntity;
import com.tspsoft.userservice.logic.IUserLogic;
import com.tspsoft.userservice.services.IUserService;

@Component
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserLogic userLogic;

	public List<UserResponse> getAllUsers() {
		return userLogic.getAllUsers();
	}

	public void disableUser(UserEntity user) {
		userLogic.disableUser(user);
	}

	@Override
	public void updateUser(Long userId, UserRequest userDetails) {
		userLogic.updateUser(userId, userDetails);
	}

	@Override
	public Optional<UserEntity> findById(Long userId) {
		return userLogic.findById(userId);
	}

	@Override
	public void deleteById(Long userId) {
		userLogic.deleteById(userId);
	}

	@Override
	public void saveUser(UserEntity user) {
		userLogic.saveUser(user);
	}

	@Override
	public UserEntity findByEmail(String email){
		return userLogic.findByEmail(email);
	}

	@Override
	public void createUser(UserEntity user) {
		userLogic.createUser(user);
	}

	@Override
	public void logout() {
		userLogic.logout();
	}
}
