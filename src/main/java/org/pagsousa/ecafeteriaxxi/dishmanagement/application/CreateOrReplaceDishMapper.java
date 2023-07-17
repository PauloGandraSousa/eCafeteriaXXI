package org.pagsousa.ecafeteriaxxi.dishmanagement.application;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.model.Dish;

/**
 *
 * @author Paulo Gandra Sousa 14/07/2023.
 *
 */
@Mapper(componentModel = "spring")
abstract class CreateOrReplaceDishMapper extends AbstractDishMapper {

	public abstract Dish create(CreateOrReplaceDishRequest request);

	public abstract void replace(@MappingTarget Dish d, CreateOrReplaceDishRequest request);

}
