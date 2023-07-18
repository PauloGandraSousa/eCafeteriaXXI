package org.pagsousa.ecafeteriaxxi.mealmanagement.infrastructure.repositories.impl;

import java.time.LocalDate;

import org.pagsousa.ecafeteriaxxi.mealmanagement.domain.model.Meal;
import org.pagsousa.ecafeteriaxxi.mealmanagement.domain.model.MealType;
import org.pagsousa.ecafeteriaxxi.mealmanagement.domain.repositories.MealRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import eapli.framework.infrastructure.repositories.impl.springdata.SpringDataBaseRepository;

/**
 * @author Paulo Gandra Sousa
 *
 */
public interface SpringDataMealRepository extends MealRepository, SpringDataBaseRepository<Meal, Long> {

	@Override
	@Query("SELECT e FROM #{#entityName} e WHERE  e.day = :ofDay AND e.mealType=:forMeal")
	Iterable<Meal> findByDayAndType(@Param("ofDay") LocalDate forDay, @Param("forMeal") MealType forMeal);

	@Override
	@Query("SELECT e FROM #{#entityName} e WHERE e.day = :ofDay")
	Iterable<Meal> findByDay(@Param("ofDay") LocalDate ofDay);

	@Override
	@Query("SELECT e FROM #{#entityName} e WHERE  e.day BETWEEN :beginDate AND :endDate")
	Iterable<Meal> findByPeriod(@Param("beginDate") LocalDate beginDate, @Param("endDate") LocalDate endDate);

	@Override
	@Modifying
	@Query("DELETE FROM Meal f WHERE f.id = ?1 AND f.version = ?2")
	int deleteByIdIfMatch(Long id, long desiredVersion);
}
