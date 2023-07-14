package org.pagsousa.ecafeteriaxxi.dishmanagement.application;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * the DTO for creation of a dish
 *
 * @author Paulo Gandra Sousa 13/07/2023.
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateDishRequest {

	@NotNull
	@NotBlank
	@Size(min = 1, max = 100)
	private String name;

	@NotNull
	@NotBlank
	private String price;

	@NotNull
	@NotBlank
	@Size(min = 1, max = 255)
	private String shortDescription;

	@NotNull
	@NotBlank
	private String dishType;

	@Size(min = 1, max = 2048)
	private String longDescription;
}
