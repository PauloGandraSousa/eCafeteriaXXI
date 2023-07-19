package org.pagsousa.ecafeteriaxxi.orgstructuremanagement.bootstrapping;

import org.pagsousa.ecafeteriaxxi.orgstructuremanagement.domain.model.Cafeteria;
import org.pagsousa.ecafeteriaxxi.orgstructuremanagement.domain.model.OrganicUnit;
import org.pagsousa.ecafeteriaxxi.orgstructuremanagement.domain.repositories.CafeteriaRepository;
import org.pagsousa.ecafeteriaxxi.orgstructuremanagement.domain.repositories.OrganicUnitRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import eapli.framework.general.domain.model.Designation;
import lombok.RequiredArgsConstructor;

/**
 * Spring will load and execute all components that implement the interface
 * CommandLineRunner on startup, so we will use that as a way to bootstrap some
 * data for testing purposes.
 * <p>
 * In order to enable this bootstraping make sure you activate the spring
 * profile "bootstrap" in application.properties
 *
 * @author Paulo Gandra de Sousa
 *
 */
@Component
@RequiredArgsConstructor
@Profile("ecafeteriaxxi.bootstrap.demo")
@Order(10)
public class OrganizationalStructureBootstrapper implements CommandLineRunner {

	private final OrganicUnitRepository ouRepo;
	private final CafeteriaRepository cafeRepo;

	@Override
	@Transactional
	public void run(final String... args) throws Exception {
		final var isep = registerOU("ISEP", "Instituto de Engenharia");
		registerCafe(isep, "cantina");
		registerCafe(isep, "Bar F");
		registerCafe(isep, "Bar G");

		final var ese = registerOU("ESE", "Escola de Educação");
		registerCafe(ese, "cantina");
	}

	private Cafeteria registerCafe(final OrganicUnit ou, final String name) {
		final var maybeCafe = cafeRepo.findByName(Designation.valueOf(name), ou);
		if (maybeCafe.isPresent()) {
			return maybeCafe.get();
		}

		final var cafe = new Cafeteria(ou, Designation.valueOf(name));
		return cafeRepo.save(cafe);
	}

	private OrganicUnit registerOU(final String acro, final String name) {
		final var maybeOU = ouRepo.findByAcronym(Designation.valueOf(acro));
		if (maybeOU.isPresent()) {
			return maybeOU.get();
		}

		final var ou = new OrganicUnit(Designation.valueOf(acro), Designation.valueOf(name));
		return ouRepo.save(ou);
	}
}
