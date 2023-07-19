package org.pagsousa.ecafeteriaxxi.dishmanagement.bootstrapping;

import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.model.DishType;
import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.model.DishTypeAcronym;
import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.repositories.DishTypeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import eapli.framework.general.domain.model.Description;
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
public class DishTypeBootstrapper implements CommandLineRunner {

	private final DishTypeRepository repo;

	@Override
	@Transactional
	public void run(final String... args) throws Exception {
		if (repo.findByAcronym(DishTypeAcronym.valueOf("peixe")).isEmpty()) {
			final var u1 = new DishType(DishTypeAcronym.valueOf("peixe"), Description.valueOf("pratos do mar"));
			repo.save(u1);
		}

		if (repo.findByAcronym(DishTypeAcronym.valueOf("carne")).isEmpty()) {
			final var u1 = new DishType(DishTypeAcronym.valueOf("carne"), Description.valueOf("pratos da terra"));
			repo.save(u1);
		}
	}
}
