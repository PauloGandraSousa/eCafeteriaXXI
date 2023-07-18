package org.pagsousa.ecafeteriaxxi.usermanagement.application;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Based on https://github.com/Yoh0xFF/java-spring-security-example
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchUsersQuery {
	private String username;
	private String fullName;
}
