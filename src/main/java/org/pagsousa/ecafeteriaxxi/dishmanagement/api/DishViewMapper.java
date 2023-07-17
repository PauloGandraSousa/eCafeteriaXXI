package org.pagsousa.ecafeteriaxxi.dishmanagement.api;

import org.mapstruct.Mapper;
import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.model.Dish;
import org.pagsousa.ecafeteriaxxi.util.mapping.AbstractViewMapper;

/**
 *
 * @author Paulo Gandra Sousa 13/07/2023.
 */
@Mapper(componentModel = "spring")
interface DishViewMapper extends AbstractViewMapper {

	DishView toView(Dish d);

	Iterable<DishView> toView(Iterable<Dish> ds);
}
