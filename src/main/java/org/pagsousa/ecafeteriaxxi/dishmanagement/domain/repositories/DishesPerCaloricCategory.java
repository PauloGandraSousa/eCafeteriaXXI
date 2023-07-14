package org.pagsousa.ecafeteriaxxi.dishmanagement.domain.repositories;

import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * A pure DTO for reporting. We don't even bother in having the fields private
 * as the only purpose of this "class" is to carry data as a data bag.
 * <p>
 * We need the constructor just to simplify the JPA query methods
 *
 * @author Paulo Gandra Sousa
 */
@Data
@AllArgsConstructor
public class DishesPerCaloricCategory {

	private final String caloricCategory;
	// this needs to be a BigInteger because it is mapped from a native query
	// and JPA won't do type conversion
	private final BigInteger quantityOfDishes;

	// @Nuno Castro - Necessary for SQLServer
	public DishesPerCaloricCategory(final String caloricCategory, final Integer quantityOfDishes) {
		this.caloricCategory = caloricCategory;
		this.quantityOfDishes = BigInteger.valueOf(quantityOfDishes);
	}
}
