package org.pagsousa.ecafeteriaxxi.mealmanagement.domain.repositories;

import java.time.LocalDate;
import java.util.Optional;

import org.pagsousa.ecafeteriaxxi.mealmanagement.domain.model.Meal;
import org.pagsousa.ecafeteriaxxi.mealmanagement.domain.model.MealType;

/**
 *
 * @author Paulo Gandra de Sousa
 */
public interface MealRepository {

	Iterable<Meal> findByDayAndType(LocalDate forDay, MealType forMeal);

	Iterable<Meal> findByDay(LocalDate forDay);

	Iterable<Meal> findByPeriod(LocalDate beginDate, LocalDate endDate);

	Optional<Meal> findById(Long entityId);

	Meal save(Meal s);
}
