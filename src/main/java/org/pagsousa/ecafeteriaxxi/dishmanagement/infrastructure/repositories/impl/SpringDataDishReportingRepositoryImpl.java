package org.pagsousa.ecafeteriaxxi.dishmanagement.infrastructure.repositories.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.repositories.DishesPerCaloricCategory;
import org.springframework.transaction.annotation.Transactional;

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
		final Query query = entityManager.createNativeQuery(Queries.DISHES_PER_CALORIC_CATEGORY,
				"DishesPerCaloricCategoryMapping");
		return query.getResultList();
	}
}
