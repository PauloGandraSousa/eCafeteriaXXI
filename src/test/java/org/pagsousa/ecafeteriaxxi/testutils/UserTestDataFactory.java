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
package org.pagsousa.ecafeteriaxxi.testutils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.pagsousa.ecafeteriaxxi.usermanagement.api.UserView;
import org.pagsousa.ecafeteriaxxi.usermanagement.api.UserViewMapper;
import org.pagsousa.ecafeteriaxxi.usermanagement.model.User;
import org.pagsousa.ecafeteriaxxi.usermanagement.services.CreateUserRequest;
import org.pagsousa.ecafeteriaxxi.usermanagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Based on https://github.com/Yoh0xFF/java-spring-security-example
 *
 */
@Service
public class UserTestDataFactory {

	@Autowired
	private UserService userService;
	@Autowired
	private UserViewMapper userViewMapper;

	@Transactional
	public UserView createUser(final String username, final String fullName, final String password) {
		final CreateUserRequest createRequest = new CreateUserRequest(username, fullName, password);

		final User user = userService.create(createRequest);

		assertNotNull(user.getId(), "User id must not be null!");
		assertEquals(fullName, user.getFullName(), "User name update isn't applied!");

		return userViewMapper.toUserView(user);
	}

	public UserView createUser(final String username, final String fullName) {
		return createUser(username, fullName, "Test12345_");
	}

	@Transactional
	public void deleteUser(final Long id) {
		userService.delete(id);
	}

}
