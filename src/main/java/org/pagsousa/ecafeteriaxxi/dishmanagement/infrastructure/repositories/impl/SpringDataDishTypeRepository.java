package org.pagsousa.ecafeteriaxxi.dishmanagement.infrastructure.repositories.impl;

import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.model.DishType;
import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.repositories.DishTypeRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Paulo Gandra Sousa 06/07/2023.
 *
 */
public interface SpringDataDishTypeRepository extends DishTypeRepository, CrudRepository<DishType, Long> {

	@Override
	@Query("SELECT e FROM DishType e WHERE e.active=true")
	Iterable<DishType> findAllActive();
}
