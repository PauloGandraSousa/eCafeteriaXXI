package org.pagsousa.ecafeteriaxxi.orgstructuremanagement.domain.model;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import eapli.framework.general.domain.model.Designation;

/**
 *
 * @author arocha
 * @author Paulo Gandra de Sousa
 */
class OrganicUnitTest {

	@Test
	void ensureAcronymMustNotBeNull() {
		assertThrows(IllegalArgumentException.class, () -> new OrganicUnit(null, Designation.valueOf("Instituto")));
	}

	@Test
	void ensureNameMustNotBeNull() {
		assertThrows(IllegalArgumentException.class, () -> new OrganicUnit(Designation.valueOf("main"), null));
	}

	/**
	 * Test of is method, of class OrganicUnit.
	 */
	@Test
	void testIs() {
		final var id = Designation.valueOf("ISEP");
		final var instance = new OrganicUnit(id, Designation.valueOf("Instituto"));
		assertTrue(instance.hasIdentity(id));
	}
}
