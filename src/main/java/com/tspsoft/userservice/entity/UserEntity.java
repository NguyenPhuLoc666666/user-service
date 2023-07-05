package com.tspsoft.userservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userId")
	long userId;
	
	@Column(name = "username", nullable = false)
	//@NotBlank(message = "Username is required")
	String username;

	@Column(name = "password", nullable = false)
	//@NotBlank(message = "Password is required")
	String password;

	@Column(name = "firstName", nullable = false)
	String firstName;
	
	@Column(name = "lastName", nullable = false)
	String lastName;

	@Column(name = "email", nullable = false)
	//@NotBlank(message = "Email is required")
	String email;

	@Column(name = "status", nullable = false)
	Boolean status;

	@Column(name = "createDate")
	String createDate;

	@Column(name = "lastModifiedDate")
	String lastModifiedDate;
	
	@Column(name = "createBy")
	String createBy;

	@Column(name = "lastModifiedBy")
	String lastModifiedBy;
}