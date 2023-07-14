package org.pagsousa.ecafeteriaxxi.dishmanagement.domain.repositories;

import java.util.Optional;
import java.util.UUID;

import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.model.Allergen;
import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.model.Dish;

/**
 *
 * @author Jorge Santos ajs@isep.ipp.pt 02/04/2016
 * @author Paulo Gandra Sousa
 */
public interface DishRepository {
	/**
	 *
	 * @return
	 */
	Iterable<Dish> findAll();

	/**
	 *
	 * @param acronym
	 * @return
	 */
	Optional<Dish> findById(UUID id);

	/**
	 *
	 * @param request
	 * @return
	 */
	Dish save(Dish request);

	/**
	 *
	 * @param a
	 * @return
	 */
	Iterable<Dish> findAllDishesWithAllergen(Allergen a);

	/**
	 *
	 * @param a
	 * @return
	 */
	Iterable<Dish> findAllDishesWithoutAllergen(Allergen a);
}
