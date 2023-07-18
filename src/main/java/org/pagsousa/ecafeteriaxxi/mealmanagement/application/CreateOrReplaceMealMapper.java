package org.pagsousa.ecafeteriaxxi.mealmanagement.application;

import java.util.UUID;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.model.Dish;
import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.repositories.DishRepository;
import org.pagsousa.ecafeteriaxxi.mealmanagement.domain.model.Meal;
import org.pagsousa.ecafeteriaxxi.util.mapping.AbstractMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Paulo Gandra Sousa 17/07/2023.
 *
 */
@Mapper(componentModel = "spring")
abstract class CreateOrReplaceMealMapper implements AbstractMapper {

	@Autowired
	private DishRepository dishRepo;

	protected Dish map(final String dishId) {
		return dishRepo.findById(UUID.fromString(dishId))
				.orElseThrow(() -> new IllegalArgumentException("Dish '" + dishId + "' not found"));
	}

	public abstract Meal create(CreateMealRequest request);

	public abstract void replace(@MappingTarget Meal d, ReplaceMealRequest request);

}
