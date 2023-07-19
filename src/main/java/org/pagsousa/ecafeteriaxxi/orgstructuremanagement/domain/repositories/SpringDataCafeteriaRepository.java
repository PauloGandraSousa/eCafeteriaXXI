package org.pagsousa.ecafeteriaxxi.orgstructuremanagement.domain.repositories;

import java.util.Optional;

import org.pagsousa.ecafeteriaxxi.orgstructuremanagement.domain.model.Cafeteria;
import org.pagsousa.ecafeteriaxxi.orgstructuremanagement.domain.model.OrganicUnit;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import eapli.framework.general.domain.model.Designation;

/**
 *
 * @author Paulo Gandra Sousa 19/07/2023.
 *
 */
public interface SpringDataCafeteriaRepository extends CafeteriaRepository, CrudRepository<Cafeteria, Long> {

	@Override
	@Query("SELECT e FROM Cafeteria e WHERE e.name.cafe=?1 AND e.organicUnit=?2")
	Optional<Cafeteria> findByName(Designation cafeName, OrganicUnit ou);
}
