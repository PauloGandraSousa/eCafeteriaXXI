package org.pagsousa.ecafeteriaxxi.dishmanagement.domain.repositories;

import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.model.DishTypeAcronym;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * A reporting object.
 *
 * @author Paulo Gandra Sousa
 */
@Data
@AllArgsConstructor
public class DishesPerDishType {

	private DishTypeAcronym dishType;
	private long quantityOfDishes;
}
