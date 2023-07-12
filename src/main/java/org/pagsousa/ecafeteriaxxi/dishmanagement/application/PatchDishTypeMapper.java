package org.pagsousa.ecafeteriaxxi.dishmanagement.application;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.model.DishType;

/**
 *
 * @author Paulo Gandra Sousa 06/07/2023.
 *
 */
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
interface PatchDishTypeMapper extends AbstractDishTypeMapper {

	void patch(@MappingTarget DishType dt, CreateOrReplaceDishTypeRequest request);

}
