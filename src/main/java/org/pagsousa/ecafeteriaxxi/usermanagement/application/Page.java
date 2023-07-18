package org.pagsousa.ecafeteriaxxi.usermanagement.application;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Based on https://github.com/Yoh0xFF/java-spring-security-example
 *
 * @TODO move to generic package
 */
@AllArgsConstructor
@Data
public class Page {
	@Min(value = 1, message = "Paging must start with page 1")
	int number;

	@Min(value = 1, message = "You can request minimum 1 records")
	@Max(value = 100, message = "You can request maximum 100 records")
	int limit;

	public Page() {
		this(1, 10);
	}
}