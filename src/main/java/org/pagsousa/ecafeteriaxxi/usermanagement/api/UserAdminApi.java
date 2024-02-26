package org.pagsousa.ecafeteriaxxi.usermanagement.api;

import org.pagsousa.ecafeteriaxxi.usermanagement.application.CreateUserRequest;
import org.pagsousa.ecafeteriaxxi.usermanagement.application.EditUserRequest;
import org.pagsousa.ecafeteriaxxi.usermanagement.application.SearchRequest;
import org.pagsousa.ecafeteriaxxi.usermanagement.application.SearchUsersQuery;
import org.pagsousa.ecafeteriaxxi.usermanagement.application.UserService;
import org.pagsousa.ecafeteriaxxi.usermanagement.domain.model.Role;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
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
		final var searchUsers = userService.searchUsers(request.getPage(), request.getQuery());
		return new ListResponse<>(userViewMapper.toUserView(searchUsers));
	}
}
