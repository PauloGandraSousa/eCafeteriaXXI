package org.pagsousa.ecafeteriaxxi.orgstructuremanagement.domain.repositories;

import org.pagsousa.ecafeteriaxxi.orgstructuremanagement.domain.model.OrganicUnit;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Paulo Gandra Sousa 19/07/2023.
 *
 */
public interface SpringDataOrganicUnitRepository extends OrganicUnitRepository, CrudRepository<OrganicUnit, Long> {

}
