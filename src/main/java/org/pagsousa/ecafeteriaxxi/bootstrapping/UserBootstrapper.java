/*
 * Copyright (c) 2022-2022 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package org.pagsousa.ecafeteriaxxi.bootstrapping;

import org.pagsousa.ecafeteriaxxi.usermanagement.domain.model.Role;
import org.pagsousa.ecafeteriaxxi.usermanagement.domain.model.User;
import org.pagsousa.ecafeteriaxxi.usermanagement.domain.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

/**
 * Spring will load and execute all components that implement the interface
 * CommandLineRunner on startup, so we will use that as a way to bootstrap some
 * data for testing purposes.
 * <p>
 * In order to enable this bootstraping make sure you activate the spring
 * profile "bootstrap" in application.properties
 *
 * @author pgsou
 *
 */
@Component
@RequiredArgsConstructor
@Profile("bootstrap")
public class UserBootstrapper implements CommandLineRunner {

	private final UserRepository userRepo;

	private final PasswordEncoder encoder;

	@Override
	@Transactional
	public void run(final String... args) throws Exception {
		// admin
		if (userRepo.findByUsername("u1@mail.com").isEmpty()) {
			final User u1 = new User("u1@mail.com", encoder.encode("Password1"));
			u1.addAuthority(new Role(Role.USER_ADMIN));
			userRepo.save(u1);
		}

		// user
		if (userRepo.findByUsername("mary@mail.com").isEmpty()) {
			final User u2 = new User("mary@mail.com", encoder.encode("myMy123!"));
			u2.addAuthority(new Role(Role.DISH_ADMIN));
			userRepo.save(u2);
		}

		if (userRepo.findByUsername("mary1@mail.com").isEmpty()) {
			final User u2 = User.newUser("mary1@mail.com", encoder.encode("myMy123!"), "Mary One", Role.DISH_ADMIN);
			userRepo.save(u2);
		}

		if (userRepo.findByUsername("mary2@mail.com").isEmpty()) {
			final User u2 = User.newUser("mary2@mail.com", encoder.encode("myMy123!"), "Mary Two", Role.DISH_ADMIN);
			userRepo.save(u2);
		}

		if (userRepo.findByUsername("mary3@mail.com").isEmpty()) {
			final User u2 = User.newUser("mary3@mail.com", encoder.encode("myMy123!"), "Mary Three", Role.DISH_ADMIN);
			userRepo.save(u2);
		}
		if (userRepo.findByUsername("mary4@mail.com").isEmpty()) {
			final User u2 = User.newUser("mary4@mail.com", encoder.encode("myMy123!"), "Mary Four", Role.DISH_ADMIN);
			userRepo.save(u2);
		}
		if (userRepo.findByUsername("mary5@mail.com").isEmpty()) {
			final User u2 = User.newUser("mary5@mail.com", encoder.encode("myMy123!"), "Mary Five", Role.DISH_ADMIN);
			userRepo.save(u2);
		}

		if (userRepo.findByUsername("adam2@mail.com").isEmpty()) {
			final User u2 = User.newUser("adam2@mail.com", encoder.encode("myMy123!"), "Adam Two", Role.DISH_ADMIN);
			userRepo.save(u2);
		}
	}
}
