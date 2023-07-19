package org.pagsousa.ecafeteriaxxi.dishmanagement.bootstrapping;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.model.Allergen;
import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.repositories.AllergenRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

/**
 * Spring will load and execute all components that implement the interface
 * CommandLineRunner on startup. We will use this feature to create some
 * reference data for the application.
 *
 * @author Paulo Gandra de Sousa 19/7/2023
 *
 */
@Component
@RequiredArgsConstructor
public class AllergenBootstrapper implements CommandLineRunner {
	private static final Logger LOGGER = LogManager.getLogger(AllergenBootstrapper.class);

	private final AllergenRepository repo;

	@Override
	@Transactional
	public void run(final String... args) throws Exception {
		register("gluten",
				"Cereais que contêm glúten (trigo, centeio, cevada, aveia, espelta, gamut ou outras estirpes hibridizadas) e produtos à base destes cereais");
		register("crustaceos", "Crustáceos e produtos à base de crustáceos");
		register("ovos", "Ovos e produtos à base de ovos");
		register("peixes", "Peixes e produtos à base de peixe", "images/sardinha.jpg");
		register("amendoins", "Amendoins e produtos à base de amendoins");
		register("soja", "Soja e produtos à base de sojaAmendoins e produtos à base de amendoins");
		register("leite", "Leite e produtos à base de leite (incluindo lactose)");
		register("frutossecos",
				"Frutos de casca rija, nomeadamente, amêndoas, avelãs, nozes, castanhas de caju, pistácios, entre outros");
		register("aipo", "Aipo e produtos à base de aipo");
		register("mostarda", "Mostarda e produtos à base de mostarda");
		register("sesamo", "Sementes de sésamo e produtos à base de sementes de sésamo");
		register("sulfitos", "Dióxido de enxofre e sulfitos em concentrações superiores a 10mg/Kg ou 10ml/L");
		register("tremoco", "Tremoço e produtos à base de tremoço");
		register("moluscos", "Moluscos e produtos à base de moluscos");

	}

	private Allergen register(final String name, final String description) {
		return register(name, description, null);
	}

	private Allergen register(final String name, final String description, final String imageFilename) {
		final var maybeAllergen = repo.findByShortName(name);
		if (maybeAllergen.isEmpty()) {
			final var newAllergen = new Allergen(name, description);
			if (imageFilename != null) {
				final var imageStream = this.getClass().getClassLoader().getResourceAsStream(imageFilename);
				if (imageStream == null) {
					LOGGER.warn("Could not load image {}", imageFilename);
				} else {
					try {
						newAllergen.changeImage(IOUtils.toByteArray(imageStream));
					} catch (final IOException e) {
						LOGGER.warn("Error loading file {}", imageFilename, e);
					}
				}
			}
			LOGGER.debug("creating allergen {}", name);
			return repo.save(newAllergen);
		}
		return maybeAllergen.get();
	}
}
