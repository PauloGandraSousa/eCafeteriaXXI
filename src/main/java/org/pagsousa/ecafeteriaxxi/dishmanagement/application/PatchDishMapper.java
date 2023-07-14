package org.pagsousa.ecafeteriaxxi.dishmanagement.application;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.model.Dish;

/**
 *
 * @author Paulo Gandra Sousa 14/07/2023.
 *
 */
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
abstract class PatchDishMapper extends AbstractDishMapper {

	public abstract void patch(@MappingTarget Dish d, UpdateDishRequest request);

}
