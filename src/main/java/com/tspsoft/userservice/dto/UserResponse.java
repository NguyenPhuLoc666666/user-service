package com.tspsoft.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
	long userId;
	String username;
	String password;
	String firstName;
	String lastName;
	String email;
	boolean status;
	String createDate;
	String lastModifiedDate;
	String createBy;
	String lastModifiedBy;
}
