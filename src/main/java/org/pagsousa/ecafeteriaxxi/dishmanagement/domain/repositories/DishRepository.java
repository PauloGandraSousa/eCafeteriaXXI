package org.pagsousa.ecafeteriaxxi.dishmanagement.domain.repositories;

import java.util.Optional;
import java.util.UUID;

import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.model.Allergen;
import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.model.Dish;
import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.model.DishType;

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

	/**
	 *
	 * @param id
	 * @param expectedVersion
	 * @return
	 */
	int deleteByIdIfMatch(UUID id, long expectedVersion);

	/**
	 *
	 * @param dt
	 * @return
	 */
	Iterable<Dish> findAllByDishType(DishType dt);

	/**
	 *
	 * @param dsg
	 * @return
	 */
	Iterable<Dish> searchByName(String dsg);
}
