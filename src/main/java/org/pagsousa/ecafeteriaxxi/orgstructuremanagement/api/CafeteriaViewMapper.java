package org.pagsousa.ecafeteriaxxi.orgstructuremanagement.api;

import org.mapstruct.Mapper;
import org.pagsousa.ecafeteriaxxi.orgstructuremanagement.domain.model.Cafeteria;
import org.pagsousa.ecafeteriaxxi.orgstructuremanagement.domain.model.CafeteriaName;
import org.pagsousa.ecafeteriaxxi.orgstructuremanagement.domain.model.OrganicUnit;
import org.pagsousa.ecafeteriaxxi.util.mapping.AbstractViewMapper;

/**
 *
 * @author Paulo Gandra Sousa 19/07/2023.
 */
@Mapper(componentModel = "spring")
interface CafeteriaViewMapper extends AbstractViewMapper {

	CafeteriaView toView(Cafeteria dt);

	Iterable<CafeteriaView> toView(Iterable<Cafeteria> dts);

	default String map(final CafeteriaName s) {
		return s.cafe().toString();
	}

	default String map(final OrganicUnit ou) {
		return ou.getAcronym().toString();
	}
}
