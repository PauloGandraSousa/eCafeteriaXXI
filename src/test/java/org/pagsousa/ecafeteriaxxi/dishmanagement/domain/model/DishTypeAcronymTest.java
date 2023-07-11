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

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import eapli.framework.math.util.NumberGenerator;
import eapli.framework.strings.util.Strings;

/**
 * @author Paulo Gandra 06/07/2023
 */
class DishTypeAcronymTest {
	@Test
	void ensureAcronym() {
		assertNotNull(DishTypeAcronym.valueOf("veg"));
	}

	@Test
	void ensureAcronymMustNotBeEmpty() {
		assertThrows(IllegalArgumentException.class, () -> DishTypeAcronym.valueOf(""));
	}

	@Test
	void ensureAcronymMustNotBeNull() {
		assertThrows(IllegalArgumentException.class, () -> DishTypeAcronym.valueOf(null));
	}

	@Test
	void ensureAcronymMustNotHaveLeadingSpaces() {
		assertThrows(IllegalArgumentException.class, () -> DishTypeAcronym.valueOf("  veg"));
	}

	@Test
	void ensureAcronymMustNotHaveTrailingSpaces() {
		assertThrows(IllegalArgumentException.class, () -> DishTypeAcronym.valueOf("veg  "));
	}

	@Test
	void ensureAcronymMustNotContainSpaces() {
		assertThrows(IllegalArgumentException.class, () -> DishTypeAcronym.valueOf("ve  g"));
	}

	@Test
	void ensureAcronymCanHave12Characters() {
		for (var i = 1; i <= 12; i++) {
			final var s = Strings.repeat('a', i);
			assertNotNull(DishTypeAcronym.valueOf(s));
		}
	}

	@Test
	void ensureAcronymCannotHaveMoreThan12Characters() {
		final var max = NumberGenerator.anInt(13, 256);
		for (var i = 13; i <= max; i++) {
			final var s = Strings.repeat('a', i);
			assertThrows(IllegalArgumentException.class, () -> DishTypeAcronym.valueOf(s));
		}
	}
}
