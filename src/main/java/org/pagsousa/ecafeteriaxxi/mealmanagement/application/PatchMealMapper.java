package org.pagsousa.ecafeteriaxxi.mealmanagement.application;

import java.util.UUID;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.model.Dish;
import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.repositories.DishRepository;
import org.pagsousa.ecafeteriaxxi.exceptions.NotFoundException;
import org.pagsousa.ecafeteriaxxi.mealmanagement.domain.model.Meal;
import org.pagsousa.ecafeteriaxxi.util.mapping.AbstractMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Paulo Gandra Sousa 17/07/2023.
 *
 */
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
abstract class PatchMealMapper implements AbstractMapper {

	@Autowired
	private DishRepository dishRepo;

	public abstract void patch(@MappingTarget Meal d, UpdateMealRequest request);

	public Dish map(final String dish) {
		return dishRepo.findById(UUID.fromString(dish)).orElseThrow(() -> new NotFoundException(Dish.class, dish));
	}

}
