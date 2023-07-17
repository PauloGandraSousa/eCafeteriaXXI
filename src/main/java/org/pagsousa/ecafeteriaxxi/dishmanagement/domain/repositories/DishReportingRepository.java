package org.pagsousa.ecafeteriaxxi.dishmanagement.domain.repositories;

import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.model.Dish;

import eapli.framework.domain.repositories.ReportingRepository;

/**
 * A separate repository just for reporting use cases
 *
 * @author PAG
 */
@ReportingRepository
public interface DishReportingRepository {

	/**
	 *
	 * @return
	 */
	Iterable<DishesPerDishType> reportDishesPerDishType();

	/**
	 *
	 * @return
	 */
	Iterable<Dish> reportHighCaloriesDishes();

	/**
	 * showcase the repository can return specific DTOs for the result set
	 *
	 * @return
	 */
	Iterable<DishesPerCaloricCategory> reportDishesPerCaloricCategory();

	/**
	 * showcase the repository can return a generic object array as a tuple for the
	 * result set
	 *
	 * @return
	 */
	Iterable<Object[]> reportDishesPerCaloricCategoryAsTuples();
}
