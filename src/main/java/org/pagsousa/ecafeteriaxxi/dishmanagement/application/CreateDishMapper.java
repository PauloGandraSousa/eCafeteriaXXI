package org.pagsousa.ecafeteriaxxi.dishmanagement.application;

import org.mapstruct.Mapper;
import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.model.Dish;
import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.model.DishType;
import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.model.DishTypeAcronym;
import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.repositories.DishTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.money.domain.model.Money;

/**
 *
 * @author Paulo Gandra Sousa 14/07/2023.
 *
 */
@Mapper(componentModel = "spring")
abstract class CreateDishMapper {

	@Autowired
	private DishTypeRepository dishTypeRepo;

	public abstract Dish create(CreateDishRequest request);

	public Money toMoney(final String price) {
		return Money.valueOf(price);
	}

	public DishType toDishType(final String dishTypeAcronym) {
		return dishTypeRepo.findByAcronym(DishTypeAcronym.valueOf(dishTypeAcronym))
				.orElseThrow(() -> new IllegalArgumentException("DishType '" + dishTypeAcronym + "' not found"));
	}

	public Designation toDesignation(final String desc) {
		return Designation.valueOf(desc);
	}

	public Description toDescription(final String desc) {
		if (desc != null) {
			return Description.valueOf(desc);
		}
		return null;
	}
}
