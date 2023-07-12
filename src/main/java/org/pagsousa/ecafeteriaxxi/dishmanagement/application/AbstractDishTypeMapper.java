package org.pagsousa.ecafeteriaxxi.dishmanagement.application;

import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.model.DishTypeAcronym;

import eapli.framework.general.domain.model.Description;

/**
 *
 * @author Paulo Gandra Sousa 06/07/2023.
 *
 */
interface AbstractDishTypeMapper {

	/**
	 * this mapping is used both for long and short description since we use the
	 * same value object type. however, short description is mandatory and long
	 * description is optional.
	 *
	 * @param description
	 * @return
	 */
	default Description toDescription(final String description) {
		return description != null ? Description.valueOf(description) : null;
	}

	default DishTypeAcronym toAcronym(final String acronym) {
		return DishTypeAcronym.valueOf(acronym);
	}
}
