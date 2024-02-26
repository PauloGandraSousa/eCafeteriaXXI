package org.pagsousa.ecafeteriaxxi.dishmanagement.infrastructure.repositories.impl;

import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.repositories.DishesPerCaloricCategory;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 * since SpringData was not able to run native queries and apply the custom
 * mapping to our POJO there was the need to do a custom implementation of the
 * method
 *
 * @author Paulo Gandra Sousa
 *
 */
@Transactional(readOnly = true)
public class SpringDataDishReportingRepositoryImpl implements SpringDataDishReportingRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public Iterable<DishesPerCaloricCategory> reportDishesPerCaloricCategory() {
		final var query = entityManager.createNativeQuery(Queries.DISHES_PER_CALORIC_CATEGORY,
				"DishesPerCaloricCategoryMapping");
		return query.getResultList();
	}
}
