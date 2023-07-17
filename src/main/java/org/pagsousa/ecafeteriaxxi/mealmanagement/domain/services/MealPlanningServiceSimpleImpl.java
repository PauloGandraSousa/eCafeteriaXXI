package org.pagsousa.ecafeteriaxxi.mealmanagement.domain.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.model.Dish;
import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.model.DishType;
import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.repositories.DishRepository;
import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.repositories.DishTypeRepository;
import org.pagsousa.ecafeteriaxxi.mealmanagement.domain.model.Meal;
import org.pagsousa.ecafeteriaxxi.mealmanagement.domain.model.MealType;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

/**
 * very simple (and inefficient) meal planner
 *
 * @author Paulo Gandra Sousa 17/07/2023
 */
@Service
@AllArgsConstructor
@Profile("ecafeteriaxxi.MealPlanner.Simple")
public class MealPlanningServiceSimpleImpl implements MealPlanningService {

	private final DishTypeRepository dishTypeRepo;
	private final DishRepository dishRepo;

	@Override
	@Transactional
	public Iterable<Meal> plan(final LocalDate from, final LocalDate to) {
		final List<Meal> menu = new ArrayList<>();

		var theDay = from;
		while (theDay.compareTo(to) <= 0) {
			generateForADay(menu, theDay, MealType.LUNCH);
			generateForADay(menu, theDay, MealType.DINNER);
			theDay = theDay.plusDays(1);
		}

		return menu;
	}

	private void generateForADay(final List<Meal> menu, final LocalDate theDay, final MealType type) {
		final var dts = dishTypeRepo.findAllActive();
		for (final DishType dt : dts) {
			final var ds = dishRepo.findAllByDishType(dt);
			for (final Dish d : ds) {
				final var m = new Meal(type, theDay, d);
				menu.add(m);
				// just choose the first dish of this type
				break;
			}
		}
	}
}
