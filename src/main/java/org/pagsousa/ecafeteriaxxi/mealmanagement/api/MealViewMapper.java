package org.pagsousa.ecafeteriaxxi.mealmanagement.api;

import org.mapstruct.Mapper;
import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.model.DishType;
import org.pagsousa.ecafeteriaxxi.mealmanagement.domain.model.Meal;
import org.pagsousa.ecafeteriaxxi.orgstructuremanagement.domain.model.Cafeteria;
import org.pagsousa.ecafeteriaxxi.util.mapping.AbstractViewMapper;

/**
 *
 * @author Paulo Gandra Sousa 17/07/2023.
 */
@Mapper(componentModel = "spring")
interface MealViewMapper extends AbstractViewMapper {

	MealView toView(Meal d);

	Iterable<MealView> toView(Iterable<Meal> ds);

	default String map(final DishType dt) {
		return dt.getAcronym().toString();
	}

	default String map(final Cafeteria s) {
		return s.getName().toString();
	}
}
