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
	 * @param resource
	 * @return
	 */
	Dish create(CreateDishRequest resource);

}
