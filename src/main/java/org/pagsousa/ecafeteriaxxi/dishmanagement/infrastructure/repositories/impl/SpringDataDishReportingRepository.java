package org.pagsousa.ecafeteriaxxi.dishmanagement.infrastructure.repositories.impl;

import java.util.UUID;

import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.model.Dish;
import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.repositories.DishReportingRepository;
import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.repositories.DishesPerDishType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;

/**
 *
 * @author Paulo Gandra Sousa
 */
@RepositoryDefinition(domainClass = Dish.class, idClass = UUID.class)
public interface SpringDataDishReportingRepository
		extends DishReportingRepository, SpringDataDishReportingRepositoryCustom {

	@Override
	@Query("SELECT NEW org.pagsousa.ecafeteriaxxi.dishmanagement.domain.repositories.DishesPerDishType(t.acronym, COUNT(d)) FROM Dish d, DishType t WHERE d.dishType = t GROUP BY t.acronym")
	Iterable<DishesPerDishType> reportDishesPerDishType();

	@Override
	@Query("SELECT d FROM Dish d WHERE d.nutricionalInfo.caloriesInKCalPer100 > 350")
	Iterable<Dish> reportHighCaloriesDishes();

	@Override
	@Query(value = Queries.DISHES_PER_CALORIC_CATEGORY, nativeQuery = true)
	Iterable<Object[]> reportDishesPerCaloricCategoryAsTuples();
}

class Queries {
	private Queries() {
		// ensure utility
	}

	/**
	 * We are using a native query here as JPQL does not support SELECT FROM
	 * sub-query, hence we use the name of the databse columns and nt the name of
	 * the java properties
	 */
	public static final String DISHES_PER_CALORIC_CATEGORY = "SELECT caloricCategory, COUNT(*) as n "
			+ "FROM (SELECT *, CASE WHEN calories_InKCal_Per100 <= 150 THEN 'low' WHEN calories_InKCal_Per100 > 150 AND calories_InKCal_Per100 < 350 THEN 'medium' ELSE 'high' END AS caloricCategory FROM DISH) x "
			+ "GROUP BY caloricCategory";

}