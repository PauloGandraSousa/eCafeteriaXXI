package org.pagsousa.ecafeteriaxxi.dishmanagement.bootstrapping;

import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.model.Dish;
import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.model.DishType;
import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.model.DishTypeAcronym;
import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.repositories.DishRepository;
import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.repositories.DishTypeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.money.domain.model.Money;
import lombok.RequiredArgsConstructor;

/**
 * Spring will load and execute all components that implement the interface
 * CommandLineRunner on startup, so we will use that as a way to bootstrap some
 * data for testing purposes.
 * <p>
 * In order to enable this bootstraping make sure you activate the spring
 * profile "bootstrap" in application.properties
 *
 * @author Paulo Gandra de Sousa 17/7/2023
 *
 */
@Component
@RequiredArgsConstructor
@Profile("ecafeteriaxxi.bootstrap.demo")
public class DishBootstrapper implements CommandLineRunner {

	private final DishTypeRepository dishTypeRepo;
	private final DishRepository dishRepo;

	@Override
	@Transactional
	public void run(final String... args) throws Exception {
		final var peixe = dishTypeRepo.findByAcronym(DishTypeAcronym.valueOf("peixe")).orElseThrow();
		final var carne = dishTypeRepo.findByAcronym(DishTypeAcronym.valueOf("carne")).orElseThrow();

		createDish("Bacalhau à Bráz", "Receita que não sabemos bem qual o nome mas é deliciosa.", peixe,
				Money.euros(3.99));
		createDish("Bacalhau à Zé do Pipo", "Receita que não sabemos bem qual o nome mas é deliciosa.", peixe,
				Money.euros(4.99));
		createDish("Costeleta à salsicheiro", "Receita que não sabemos bem qual o nome mas é deliciosa.", carne,
				Money.euros(2.99));
		createDish("Picanha", "Receita que não sabemos bem qual o nome mas é deliciosa.", carne, Money.euros(5.99));
	}

	private Dish createDish(final String dsg, final String desc, final DishType type, final Money price) {
		// check if we already have a dish with this name
		final var ds = dishRepo.searchByName(dsg);
		if (ds.iterator().hasNext()) {
			return ds.iterator().next();
		}

		// create a new one if not
		final var theDsg = Designation.valueOf(dsg);
		final var theDesc = Description.valueOf(desc);
		final var d = new Dish(type, theDsg, price, theDesc);
		return dishRepo.save(d);
	}
}
