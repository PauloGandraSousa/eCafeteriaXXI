package org.pagsousa.ecafeteriaxxi.dishmanagement.application;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.model.DishType;
import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.model.DishTypeAcronym;

import eapli.framework.general.domain.model.Description;

/**
 *
 * @author Paulo Gandra Sousa 06/07/2023.
 *
 */
@Mapper(componentModel = "spring")
interface CreateOrReplaceDishTypeMapper extends AbstractDishTypeMapper {

	DishType create(String acronym, CreateOrReplaceDishTypeRequest request);

	void replace(@MappingTarget DishType dt, CreateOrReplaceDishTypeRequest request);

	/**
	 * this mapping is used both for long and short description since we use the
	 * same value object type. however, short description is mandatory and long
	 * description is optional.
	 *
	 * @param description
	 * @return
	 */
	@Override
	default Description toDescription(final String description) {
		return description != null ? Description.valueOf(description) : null;
	}

	@Override
	default DishTypeAcronym toAcronym(final String acronym) {
		return DishTypeAcronym.valueOf(acronym);
	}
}
