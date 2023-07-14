package org.pagsousa.ecafeteriaxxi.dishmanagement.application;

import javax.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
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
public class UpdateDishRequest {

	@Size(min = 1, max = 100)
	private String name;

	private String price;

	@Size(min = 1, max = 255)
	private String shortDescription;

	@Schema(required = true, description = "The acronym of the dish type")
	private String dishType;

	@Size(min = 1, max = 2048)
	private String longDescription;
}
