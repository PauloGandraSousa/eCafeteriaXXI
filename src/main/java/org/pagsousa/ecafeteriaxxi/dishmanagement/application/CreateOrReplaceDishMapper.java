package org.pagsousa.ecafeteriaxxi.dishmanagement.application;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.model.Dish;
import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.repositories.DishTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Paulo Gandra Sousa 14/07/2023.
 *
 */
@Mapper(componentModel = "spring")
abstract class CreateOrReplaceDishMapper extends AbstractDishMapper {

	@Autowired
	private DishTypeRepository dishTypeRepo;

	public abstract Dish create(CreateOrReplaceDishRequest request);

	public abstract void replace(@MappingTarget Dish d, CreateOrReplaceDishRequest request);

}
