package org.pagsousa.ecafeteriaxxi.orgstructuremanagement.api;

import org.mapstruct.Mapper;
import org.pagsousa.ecafeteriaxxi.orgstructuremanagement.domain.model.OrganicUnit;
import org.pagsousa.ecafeteriaxxi.util.mapping.AbstractViewMapper;

/**
 *
 * @author Paulo Gandra Sousa 19/07/2023.
 */
@Mapper(componentModel = "spring")
interface OrganicUnitViewMapper extends AbstractViewMapper {

	OrganicUnitView toView(OrganicUnit dt);

	Iterable<OrganicUnitView> toView(Iterable<OrganicUnit> dts);
}
