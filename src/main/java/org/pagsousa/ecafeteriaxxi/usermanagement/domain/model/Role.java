package org.pagsousa.ecafeteriaxxi.usermanagement.domain.model;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Value;

/**
 * Based on https://github.com/Yoh0xFF/java-spring-security-example
 *
 */
@Value
@AllArgsConstructor
public class Role implements GrantedAuthority {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String authority;

	/*
	 * roles
	 */
	public static final String USER_ADMIN = "USER_ADMIN";

	public static final String DISH_ADMIN = "DISH_ADMIN";

	public static final String MEAL_ADMIN = "MEAL_ADMIN";

}
