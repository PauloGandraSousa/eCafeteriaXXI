package org.pagsousa.ecafeteriaxxi.dishmanagement.application;

import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.model.DishType;
import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.model.DishTypeAcronym;
import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.repositories.DishTypeRepository;
import org.pagsousa.ecafeteriaxxi.util.mapping.AbstractMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Paulo Gandra Sousa 14/07/2023.
 *
 */
abstract class AbstractDishMapper implements AbstractMapper {

	@Autowired
	private DishTypeRepository dishTypeRepo;

	public DishType toDishType(final String dishTypeAcronym) {
		return dishTypeRepo.findByAcronym(DishTypeAcronym.valueOf(dishTypeAcronym))
				.orElseThrow(() -> new IllegalArgumentException("DishType '" + dishTypeAcronym + "' not found"));
	}
}
