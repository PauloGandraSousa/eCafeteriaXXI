package org.pagsousa.ecafeteriaxxi.dishmanagement.api;

import org.mapstruct.Mapper;
import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.model.DishType;
import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.model.DishTypeAcronym;
import org.pagsousa.ecafeteriaxxi.util.mapping.AbstractViewMapper;

/**
 *
 * @author Paulo Gandra Sousa 06/07/2023.
 */
@Mapper(componentModel = "spring")
interface DishTypeViewMapper extends AbstractViewMapper {

	DishTypeView toView(DishType dt);

	Iterable<DishTypeView> toView(Iterable<DishType> dts);

	default String toView(final DishTypeAcronym acronym) {
		return acronym.toString();
	}
}
