package org.pagsousa.ecafeteriaxxi.dishmanagement.infrastructure.repositories.impl;

import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.model.Allergen;
import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.repositories.AllergenRepository;

import eapli.framework.infrastructure.repositories.impl.springdata.SpringDataBaseRepository;

/**
 * @author Paulo Gandra de Sousa
 *
 */
public interface SpringDataAllergenRepository extends AllergenRepository, SpringDataBaseRepository<Allergen, Long> {

}
