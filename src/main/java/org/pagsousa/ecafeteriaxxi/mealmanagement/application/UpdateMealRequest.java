package org.pagsousa.ecafeteriaxxi.mealmanagement.application;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * the DTO for updating a meal
 *
 * @author Paulo Gandra Sousa 17/07/2023.
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMealRequest {

	@Schema(required = true, description = "The id of the dish")
	private String dish;
}
