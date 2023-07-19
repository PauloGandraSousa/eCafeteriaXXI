package org.pagsousa.ecafeteriaxxi.dishmanagement.api;

import org.mapstruct.Mapper;
import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.model.Allergen;
import org.pagsousa.ecafeteriaxxi.util.mapping.AbstractViewMapper;

/**
 *
 * @author Paulo Gandra Sousa 19/07/2023.
 */
@Mapper(componentModel = "spring")
interface AllergenViewMapper extends AbstractViewMapper {

	AllergenView toView(Allergen dt);

	Iterable<AllergenView> toView(Iterable<Allergen> dts);
}
