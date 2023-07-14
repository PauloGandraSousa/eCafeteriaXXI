package org.pagsousa.ecafeteriaxxi.dishmanagement.application;

import java.util.Optional;

import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.model.Dish;

/**
 *
 * @author Paulo Gandra Sousa 13/07/2023.
 *
 */
public interface DishService {

	/**
	 *
	 * @param uuid
	 * @return
	 */
	Optional<Dish> findById(String uuid);

	/**
	 *
	 * @return
	 */
	Iterable<Dish> findAll();

	/**
	 *
	 * @param request
	 * @return
	 */
	Dish create(CreateOrReplaceDishRequest request);

	/**
	 *
	 * @param id
	 * @param request
	 * @param expectedVersion
	 * @return
	 */
	Dish replace(String id, CreateOrReplaceDishRequest request, long expectedVersion);

	/**
	 *
	 * @param id
	 * @param request
	 * @param expectedVersion
	 * @return
	 */
	Dish update(String id, UpdateDishRequest request, long expectedVersion);

	/**
	 *
	 * @param id
	 * @param expectedVersion
	 * @return
	 */
	int deleteById(String id, long expectedVersion);

}
