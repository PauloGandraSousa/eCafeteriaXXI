package org.pagsousa.ecafeteriaxxi.mealmanagement.application;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

	@NotNull
	@NotBlank
	private String cafeteriaName;

	@Schema(description = "'false' = does not save the planned meals. The client can use the result of this operation as a suggestion and afterwards create each meal.")
	private boolean createMeals;
}
