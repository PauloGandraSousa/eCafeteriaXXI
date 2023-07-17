package org.pagsousa.ecafeteriaxxi.mealmanagement.api;

import org.mapstruct.Mapper;
import org.pagsousa.ecafeteriaxxi.mealmanagement.domain.model.Meal;
import org.pagsousa.ecafeteriaxxi.util.mapping.AbstractViewMapper;

/**
 *
 * @author Paulo Gandra Sousa 17/07/2023.
 */
@Mapper(componentModel = "spring")
interface MealViewMapper extends AbstractViewMapper {

	MealView toView(Meal d);

	Iterable<MealView> toView(Iterable<Meal> ds);
}
