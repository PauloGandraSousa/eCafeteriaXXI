package org.pagsousa.ecafeteriaxxi.dishmanagement.infrastructure.repositories.impl;

import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.model.DishType;
import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.model.DishTypeAcronym;
import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.repositories.DishTypeRepository;
import org.springframework.data.jpa.repository.Modifying;
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

	@Override
	@Modifying
	@Query("DELETE FROM DishType f WHERE f.acronym = ?1 AND f.version = ?2")
	int deleteByAcronymIfMatch(DishTypeAcronym acronym, long desiredVersion);
}
