package org.pagsousa.ecafeteriaxxi.dishmanagement.application;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class CreateOrReplaceDishRequest {

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

	@Schema(required = true, description = "The acronym of the dish type")
	@NotNull
	@NotBlank
	private String dishType;

	@Size(min = 1, max = 2048)
	private String longDescription;
}
