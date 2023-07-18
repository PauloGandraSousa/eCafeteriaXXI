package org.pagsousa.ecafeteriaxxi.util.mapping;

import java.util.Optional;

import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.money.domain.model.Money;

/**
 *
 * @author Paulo Gandra Sousa 17/07/2023.
 */
public interface AbstractViewMapper {

	default String map(final Designation name) {
		return name.toString();
	}

	default String map(final Description desc) {
		return desc.toString();
	}

	default String map(final Optional<Description> maybeDesc) {
		return maybeDesc.map(Description::toString).orElse(null);
	}

	default String map(final Money p) {
		return p.toSimpleString();
	}
}
