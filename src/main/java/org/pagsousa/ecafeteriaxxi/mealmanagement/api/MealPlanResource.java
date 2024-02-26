package org.pagsousa.ecafeteriaxxi.mealmanagement.api;

import org.pagsousa.ecafeteriaxxi.mealmanagement.application.MealPlanRequest;
import org.pagsousa.ecafeteriaxxi.mealmanagement.application.MealPlanService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author Paulo Gandra Sousa 17/07/2023.
 *
 */
@Tag(name = "Meal management", description = "Endpoints for managing meals")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mealplan")
public class MealPlanResource {

	private final MealPlanService service;
	private final MealViewMapper viewMapper;

	@Operation(summary = "Generates the menu for a certain period.", description = "Generates a meal plan for a certain period")
	@PostMapping
	public Iterable<MealView> plan(@Valid @RequestBody final MealPlanRequest request) {
		final var menu = service.plan(request);
		return viewMapper.toView(menu);
	}
}
