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
package org.pagsousa.ecafeteriaxxi.usermanagement.api;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import org.pagsousa.ecafeteriaxxi.usermanagement.application.CreateUserRequest;
import org.pagsousa.ecafeteriaxxi.usermanagement.application.EditUserRequest;
import org.pagsousa.ecafeteriaxxi.usermanagement.application.SearchRequest;
import org.pagsousa.ecafeteriaxxi.usermanagement.application.SearchUsersQuery;
import org.pagsousa.ecafeteriaxxi.usermanagement.application.UserService;
import org.pagsousa.ecafeteriaxxi.usermanagement.domain.model.Role;
import org.pagsousa.ecafeteriaxxi.usermanagement.domain.model.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * Based on https://github.com/Yoh0xFF/java-spring-security-example
 *
 */
@Tag(name = "UserAdmin")
@RestController
@RequestMapping(path = "api/admin/user")
@RolesAllowed(Role.USER_ADMIN)
@RequiredArgsConstructor
public class UserAdminApi {

	private final UserService userService;
	private final UserViewMapper userViewMapper;

	@PostMapping
	public UserView create(@RequestBody @Valid final CreateUserRequest request) {
		final var user = userService.create(request);
		return userViewMapper.toUserView(user);
	}

	@PutMapping("{id}")
	public UserView update(@PathVariable final Long id, @RequestBody @Valid final EditUserRequest request) {
		final var user = userService.update(id, request);
		return userViewMapper.toUserView(user);
	}

	@DeleteMapping("{id}")
	public UserView delete(@PathVariable final Long id) {
		final var user = userService.delete(id);
		return userViewMapper.toUserView(user);
	}

	@GetMapping("{id}")
	public UserView get(@PathVariable final Long id) {
		final var user = userService.getUser(id);
		return userViewMapper.toUserView(user);
	}

	@PostMapping("search")
	public ListResponse<UserView> search(@RequestBody final SearchRequest<SearchUsersQuery> request) {
		final List<User> searchUsers = userService.searchUsers(request.getPage(), request.getQuery());
		return new ListResponse<>(userViewMapper.toUserView(searchUsers));
	}
}
