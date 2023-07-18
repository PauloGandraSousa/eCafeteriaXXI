package org.pagsousa.ecafeteriaxxi.mealmanagement.application;

import java.util.Optional;

import org.pagsousa.ecafeteriaxxi.mealmanagement.domain.model.Meal;

/**
 *
 * @author Paulo Gandra Sousa 17/07/2023.
 *
 */
public interface MealService {

	/**
	 *
	 * @param id
	 * @return
	 */
	Optional<Meal> findById(String id);

	/**
	 *
	 * @return
	 */
	Iterable<Meal> findAll();

	/**
	 *
	 * @param request
	 * @return
	 */
	Meal create(CreateMealRequest request);

	/**
	 *
	 * @param id
	 * @param request
	 * @param expectedVersion
	 * @return
	 */
	Meal replace(String id, ReplaceMealRequest request, long expectedVersion);

	/**
	 *
	 * @param id
	 * @param request
	 * @param expectedVersion
	 * @return
	 */
	Meal update(String id, UpdateMealRequest request, long expectedVersion);

	/**
	 *
	 * @param id
	 * @param expectedVersion
	 * @return
	 */
	int deleteById(String id, long expectedVersion);
}
