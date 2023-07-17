package org.pagsousa.ecafeteriaxxi.mealmanagement.api;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 *
 * @author Paulo Gandra Sousa 17/07/2023.
 *
 */
@Data
public class MealPlanRequest {

	@NotNull
	@NotBlank
	private String from;

	@NotNull
	@NotBlank
	private String to;

	@Schema(description = "'false' = does not save the planned meals. The client can use the result of this operation as a suggestion and afterwards create each meal.")
	private boolean createMeals;
}
