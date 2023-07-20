package org.pagsousa.ecafeteriaxxi.mealmanagement.application;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * the DTO for creation of a meal
 *
 * @author Paulo Gandra Sousa 17/07/2023.
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMealRequest {

	@NotNull
	@NotBlank
	private String mealType;

	@NotNull
	@NotBlank
	private String ofDay;

	@NotNull
	@NotBlank
	private String cafeteriaName;

	@Schema(required = true, description = "The id of the dish")
	@NotNull
	@NotBlank
	private String dish;
}
