package org.pagsousa.ecafeteriaxxi.usermanagement.application;

import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Based on https://github.com/Yoh0xFF/java-spring-security-example
 *
 */
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {
	@NonNull
	@NotBlank
	@Email
	private String username;

	@NonNull
	@NotBlank
	private String fullName;

	@NonNull
	@NotBlank
	private String password;

	@NonNull
	@NotBlank
	private String rePassword;

	private Set<String> authorities = new HashSet<>();

	public CreateUserRequest(final String username, final String fullName, final String password) {
		this.username = username;
		this.fullName = fullName;
		this.password = password;
		this.rePassword = password;
	}
}
