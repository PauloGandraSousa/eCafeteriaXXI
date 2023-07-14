package org.pagsousa.ecafeteriaxxi.dishmanagement.infrastructure.repositories.impl;

import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.repositories.DishesPerCaloricCategory;

/**
 * Declare the query method that will use a custom implementation since spring
 * data is not able to map native queries to POJOs
 *
 * @author Paulo Gandra Sousa
 */
public interface SpringDataDishReportingRepositoryCustom {

	Iterable<DishesPerCaloricCategory> reportDishesPerCaloricCategory();
}
