package org.pagsousa.ecafeteriaxxi.mealmanagement.api;

import org.pagsousa.ecafeteriaxxi.dishmanagement.api.DishView;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 *
 * @author Paulo Gandra Sousa 17/07/2023.
 *
 */
@Data
@Schema(description = "A meal")
public class MealView {
	private String id;
	private String mealType;
	private String day;
	private String cafeteria;
	private DishView dish;
}
