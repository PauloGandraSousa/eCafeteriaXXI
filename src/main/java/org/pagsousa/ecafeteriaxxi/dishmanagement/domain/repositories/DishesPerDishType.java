package org.pagsousa.ecafeteriaxxi.dishmanagement.domain.repositories;

import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.model.DishTypeAcronym;

import lombok.Data;

/**
 * A reporting object.
 *
 * @author Paulo Gandra Sousa
 */
@Data
public class DishesPerDishType {

	private String dishType;
	private long quantityOfDishes;

	public DishesPerDishType(final DishTypeAcronym dishType, final long quantityOfDishes) {
		this.dishType = dishType.toString();
		this.quantityOfDishes = quantityOfDishes;
	}
}
