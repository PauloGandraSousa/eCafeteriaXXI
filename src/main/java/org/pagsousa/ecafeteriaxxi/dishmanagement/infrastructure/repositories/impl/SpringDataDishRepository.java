package org.pagsousa.ecafeteriaxxi.dishmanagement.infrastructure.repositories.impl;

import java.util.UUID;

import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.model.Allergen;
import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.model.Dish;
import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.repositories.DishRepository;
import org.springframework.data.jpa.repository.Query;

import eapli.framework.infrastructure.repositories.impl.springdata.SpringDataBaseRepository;

/**
 * @author Paulo Gandra Sousa
 *
 */
public interface SpringDataDishRepository extends DishRepository, SpringDataBaseRepository<Dish, UUID> {

	/**
	 * Eventough this method follows the Spring Data naming convention we want to
	 * use the @Query annotation to specify the sorting of the results.
	 *
	 * @see eapli.framework.domain.repositories.DomainRepository#findAll()
	 */
	@Override
	@Query("SELECT e FROM #{#entityName} e ORDER BY e.name ASC")
	Iterable<Dish> findAll();

	@Override
	@Query("SELECT d FROM Dish d WHERE :allergen MEMBER OF d.allergens")
	Iterable<Dish> findAllDishesWithAllergen(final Allergen allergen);

	@Override
	@Query("SELECT d FROM Dish d WHERE :allergen NOT MEMBER OF d.allergens")
	Iterable<Dish> findAllDishesWithoutAllergen(final Allergen allergen);
}
