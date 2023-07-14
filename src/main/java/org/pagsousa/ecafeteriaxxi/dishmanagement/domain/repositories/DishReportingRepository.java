package org.pagsousa.ecafeteriaxxi.dishmanagement.domain.repositories;

import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.model.Dish;

import eapli.framework.domain.repositories.ReportingRepository;

/**
 *
 * @author PAG
 */
@ReportingRepository
public interface DishReportingRepository {

	Iterable<DishesPerDishType> reportDishesPerDishType();

	Iterable<Dish> reportHighCaloriesDishes();

	Iterable<DishesPerCaloricCategory> reportDishesPerCaloricCategory();

	Iterable<Object[]> reportDishesPerCaloricCategoryAsTuples();
}
