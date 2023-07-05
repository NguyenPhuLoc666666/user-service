package com.tspsoft.userservice.logic.impl;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tspsoft.userservice.dto.UserRequest;
import com.tspsoft.userservice.dto.UserResponse;
import com.tspsoft.userservice.entity.UserEntity;
import com.tspsoft.userservice.logic.IUserLogic;
import com.tspsoft.userservice.repository.IUserRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class UserLogicImpl implements IUserLogic {

	@Autowired
	private IUserRepository userRepository;

	private UserResponse mapToUserResponse(UserEntity user) {
		return UserResponse.builder()
				.userId(user.getUserId())
				.username(user.getUsername())
				.password(user.getPassword())
				.firstName(user.getFirstName())
				.lastName(user.getLastName())
				.email(user.getEmail())
				.status(user.getStatus())
				.createDate(user.getCreateDate())
				.lastModifiedDate(user.getLastModifiedDate())
				.createBy(user.getLastModifiedBy())
				.lastModifiedBy(user.getCreateBy())
				.build();
	}

	@Override
	public List<UserResponse> getAllUsers() {
		List<UserEntity> listUsers = (List<UserEntity>) userRepository.findAll();
		return listUsers.stream().map(User -> mapToUserResponse(User)).toList();
	}

	@Override
	public void disableUser(UserEntity user) {
		user.setStatus(!user.getStatus());
		userRepository.save(user);
	}

	@Override
	public Optional<UserEntity> findById(Long userId) {
		return userRepository.findById(userId);
	}

	@Override
	public void updateUser(Long userId, UserRequest userDetails) {
		UserEntity newUser = new UserEntity();/* = userRepository.findById(Long.valueOf(userId)); */
		newUser.setUsername(userDetails.getUsername());
		newUser.setPassword(userDetails.getPassword());
		newUser.setFirstName(userDetails.getFirstName());
		newUser.setLastName(userDetails.getLastName());
		newUser.setEmail(userDetails.getEmail());
		newUser.setStatus(userDetails.isStatus());
		newUser.setCreateDate(userDetails.getCreateDate());
		newUser.setLastModifiedDate(userDetails.getLastModifiedDate());
		newUser.setCreateBy(userDetails.getCreateBy());
		newUser.setLastModifiedBy(userDetails.getLastModifiedBy());
		userRepository.save(newUser);
	}

	@Override
	public void deleteById(Long userId) {
		userRepository.deleteById(userId);
	}

	@Override
	public void saveUser(UserEntity user) {
		userRepository.save(user);
	}

	
	@Override
	public UserEntity findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
    
	final String EMAIL_CLAIMS = "http://wso2.org/claims/emailaddress";
	final String FIRSTNAME_CLAIMS = "http://wso2.org/claims/givenname";
	final String LASTNAME_CLAIMS = "http://wso2.org/claims/lastname";
	final String CALLBACK_URL = "https://localhost:9443/myaccount/login";
	final String restAPI_CreateUser_url = "https://localhost:9443/api/identity/user/v1.0/me";
	final String SCIM2_CreateUser_url = "https://localhost:9443/scim2/Users";
	@Override
	public void createUser(UserEntity user) {
//		String data = "{\"user\": {\"username\": \""+user.getUsername()+"\",\"realm\": \"PRIMARY\","
//				+ " \"password\": \""+user.getPassword()+"\","
//						+ "\"claims\": ["
//						+ "{\"uri\": \""+EMAIL_CLAIMS+"\",\"value\": \""+user.getEmail()+"\"},"
//						+ "{\"uri\": \""+FIRSTNAME_CLAIMS+"\",\"value\": \""+user.getFirstName()+"\" },"
//						+ "{\"uri\": \""+LASTNAME_CLAIMS+"\",\"value\": \""+user.getLastName()+"\"} ]},"
//						+ "\"properties\": [{\"key\":\"callback\",\"value\": \""+CALLBACK_URL+"\"}]}";

		String scim2_data ="{\"schemas\":[],\"name\":{\"familyName\":\""+ user.getLastName()
				+ "\",\"givenName\":\""+user.getFirstName()
				+ "\"},\"userName\":\""+user.getUsername()
				+ "kim\",\"password\":\""+user.getPassword()
				+ "\",\"emails\":[{\"primary\":true,\"value\":\""+user.getEmail()
				+ "\",\"type\":\"home\"},"
				+ "{\"value\":\""+user.getEmail()
				+ "\",\"type\":\"work\"}]}";
		
//		String credentials = "admin" + ":" + "admin";
//		String auth = "Basic " + Base64.getEncoder().encodeToString(credentials.getBytes());
		
		HttpClient client = HttpClient.newBuilder()
			    .followRedirects(HttpClient.Redirect.NORMAL)
			    .build();
		
		HttpRequest request = HttpRequest.newBuilder()
		    .uri(URI.create(SCIM2_CreateUser_url))
		    .POST(BodyPublishers.ofString(scim2_data))
		    .setHeader("Authorization", "Basic YWRtaW46YWRtaW4=")
		    .setHeader("Content-Type", "application/json")
		    .build();
		
			try {
				System.out.println("req: "+request.toString());
				HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
				log.info(">>>>>>>>>>>>>>>>>>>>>>Start end rest api<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
				System.out.println("res: "+response.body());
			} catch (java.io.IOException | InterruptedException e) {
				e.printStackTrace();
			}	
			
    }

	@Override
	public void logout() {
		HttpClient client = HttpClient.newBuilder()
			    .followRedirects(HttpClient.Redirect.NORMAL)
			    .build();
		HttpRequest request = HttpRequest.newBuilder()
			    .uri(URI.create("https://localhost:9443/oidc/logout"))
			    .GET()
			    .build();
		try {
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			System.out.println("res: "+response.body());
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}
