package org.pagsousa.ecafeteriaxxi.usermanagement.application;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Based on https://github.com/Yoh0xFF/java-spring-security-example
 *
 * @TODO move to generic package
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SearchRequest<T> {
	@Valid
	@NotNull
	Page page;

	@Valid
	@NotNull
	T query;
}
