package org.pagsousa.ecafeteriaxxi.dishmanagement.application;

import java.util.Optional;

import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.model.DishType;

/**
 *
 * @author Paulo Gandra Sousa 06/07/2023.
 *
 */
public interface DishTypeService {

	/**
	 *
	 * @return
	 */
	Iterable<DishType> findAll();

	/**
	 *
	 * @return
	 */
	Optional<DishType> findByAcronym(String acronym);

	/**
	 *
	 * @param acronym
	 * @param request
	 * @return
	 */
	DishType create(String acronym, CreateOrReplaceDishTypeRequest request);

	/**
	 * partial update
	 *
	 * @param acronym
	 * @param request
	 * @return
	 */
	DishType update(String acronym, UpdateDishTypeRequest request, long expectedVersion);

	/**
	 * full update
	 *
	 * @param acronym
	 * @param request
	 * @return
	 */
	DishType replace(String acronym, CreateOrReplaceDishTypeRequest request, long expectedVersion);

	/**
	 * the state of the dish type is not possible to change via the update or
	 * replace method and must be explicitly changed
	 *
	 * @param acronym
	 * @return
	 */
	DishType toogleState(String acronym, long expectedVersion);
}
