/*
 * Copyright (c) 2013-2023 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.pagsousa.ecafeteriaxxi.dishmanagement.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import eapli.framework.general.domain.model.Description;

/**
 * @author mcn
 */
class DishTypeTest {

	@Test
	void ensureAcronymIsMandatory() {
		final var desc = Description.valueOf("vegetarian dish");
		assertThrows(IllegalArgumentException.class, () -> new DishType(null, desc));
	}

	@Test
	void ensureShortDescriptionIsMandatory() {
		final var acro = DishTypeAcronym.valueOf("veg1");
		assertThrows(IllegalArgumentException.class, () -> new DishType(acro, null));
	}

	@Test
	void ensureCreatedDishTypeIsActive() {
		final var acronym = DishTypeAcronym.valueOf("veg1");
		final var instance = new DishType(acronym, Description.valueOf("vegetarian dish"));
		assertTrue(instance.isActive());
	}

	@Test
	void ensureCanToogleDishTypeState() {
		final var instance = new DishType(DishTypeAcronym.valueOf("veg1"), Description.valueOf("vegetarian dish"));

		final var state = instance.isActive();
		instance.toogleState();

		assertEquals(!state, instance.isActive());
	}

	@Test
	void ensureCannotChangeShortDescriptionToNull() {
		final var instance = new DishType(DishTypeAcronym.valueOf("veg1"), Description.valueOf("vegetarian dish"));

		assertThrows(IllegalArgumentException.class, () -> instance.setShortDescription(null));
	}

	@Test
	void ensureCanChangeShortDescription() {
		final var instance = new DishType(DishTypeAcronym.valueOf("veg1"), Description.valueOf("vegetarian dish"));
		final var newDescription = Description.valueOf("new description");
		instance.setShortDescription(newDescription);

		assertEquals(newDescription, instance.getShortDescription());
	}

	@Test
	void ensureLongDescriptionCanBeNull() {
		final var instance = new DishType(DishTypeAcronym.valueOf("veg1"), Description.valueOf("vegetarian dish"),
				null);

		assertNotNull(instance);
	}

	@Test
	void ensureCanHaveLongDescription() {
		final var instance = new DishType(DishTypeAcronym.valueOf("veg1"), Description.valueOf("vegetarian dish"),
				Description.valueOf("a really long description f what a veggie dish is"));

		assertNotNull(instance);
	}

	@Test
	void ensureCanChangeLongDescriptionToNull() {
		final var org = Description.valueOf("a really long description f what a veggie dish is");
		final var instance = new DishType(DishTypeAcronym.valueOf("veg1"), Description.valueOf("vegetarian dish"), org);

		instance.setLongDescription(null);

		assertTrue(instance.getLongDescription().isEmpty());
	}

	@Test
	void ensureCanChangeLongDescription() {
		final var org = Description.valueOf("a really long description f what a veggie dish is");
		final var instance = new DishType(DishTypeAcronym.valueOf("veg1"), Description.valueOf("vegetarian dish"), org);

		final var changed = Description.valueOf("another long description");
		instance.setLongDescription(changed);

		assertTrue(instance.getLongDescription().isPresent());
		assertEquals(changed, instance.getLongDescription().orElseThrow());
	}

	@Test
	void ensureLongDescriptionIsEmptyOptionalIfNotExist() {
		final var instance = new DishType(DishTypeAcronym.valueOf("veg1"), Description.valueOf("vegetarian dish"));

		assertTrue(instance.getLongDescription().isEmpty());
	}

	@Test
	void ensureLongDescriptionHasValue() {
		final var org = Description.valueOf("a really long description f what a veggie dish is");
		final var instance = new DishType(DishTypeAcronym.valueOf("veg1"), Description.valueOf("vegetarian dish"), org);

		assertTrue(instance.getLongDescription().isPresent());
		assertEquals(org, instance.getLongDescription().orElseThrow());
	}

	@Test
	void ensureDishTypeCreatedWithAnIdentityHasThatIdentity() {
		final var acronym = DishTypeAcronym.valueOf("veg1");
		final var instance = new DishType(acronym, Description.valueOf("vegetarian dish"));

		assertEquals(acronym, instance.identity());
	}

	@Test
	void ensureDishTypeCreatedWithAnIdentityHasThatIdentity2() {
		final var id = DishTypeAcronym.valueOf("veg1");
		final var description = Description.valueOf("vegetarian dish");
		final var instance = new DishType(id, description);

		assertTrue(instance.hasIdentity(id));
	}
}
