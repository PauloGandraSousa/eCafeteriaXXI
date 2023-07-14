package org.pagsousa.ecafeteriaxxi.dishmanagement.infrastructure.repositories.impl;

import java.math.BigInteger;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.repositories.DishesPerCaloricCategory;

/**
 *
 * @author Paulo Gandra de Sousa
 *
 */
@Converter(autoApply = true)
public class DishPerCaloricCategoryConverter implements AttributeConverter<DishesPerCaloricCategory, Object[]> {

	@Override
	public Object[] convertToDatabaseColumn(final DishesPerCaloricCategory arg0) {
		final var col = new Object[2];
		col[0] = arg0.getCaloricCategory();
		col[1] = arg0.getQuantityOfDishes().longValue();
		return col;
	}

	@Override
	public DishesPerCaloricCategory convertToEntityAttribute(final Object[] arg0) {
		assert (arg0.length == 2);
		return new DishesPerCaloricCategory((String) arg0[0], BigInteger.valueOf((long) arg0[1]));
	}
}
