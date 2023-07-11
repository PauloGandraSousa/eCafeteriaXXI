package org.pagsousa.ecafeteriaxxi.dishmanagement.domain.repositories;

import java.util.Optional;

import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.model.DishType;
import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.model.DishTypeAcronym;

/**
 *
 * @author Paulo Gandra Sousa 06/07/2023.
 *
 */
public interface DishTypeRepository {

	/**
	 *
	 * @return
	 */
	Iterable<DishType> findAll();

	/**
	 *
	 * @return
	 */
	Iterable<DishType> findAllActive();

	/**
	 *
	 * @param acronym
	 * @return
	 */
	Optional<DishType> findByAcronym(DishTypeAcronym acronym);

	/**
	 *
	 * @param request
	 * @return
	 */
	DishType save(DishType request);

}
