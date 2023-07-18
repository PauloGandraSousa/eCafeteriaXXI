package org.pagsousa.ecafeteriaxxi.usermanagement.api;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Based on https://github.com/Yoh0xFF/java-spring-security-example
 *
 * @TODO move to generic package
 */
@Data
@AllArgsConstructor
public class ListResponse<T> {
	private List<T> items;
}
