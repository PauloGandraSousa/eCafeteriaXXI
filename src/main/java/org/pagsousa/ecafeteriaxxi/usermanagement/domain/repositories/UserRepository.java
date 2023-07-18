package org.pagsousa.ecafeteriaxxi.usermanagement.domain.repositories;

import java.util.List;
import java.util.Optional;

import org.pagsousa.ecafeteriaxxi.exceptions.NotFoundException;
import org.pagsousa.ecafeteriaxxi.usermanagement.application.Page;
import org.pagsousa.ecafeteriaxxi.usermanagement.application.SearchUsersQuery;
import org.pagsousa.ecafeteriaxxi.usermanagement.domain.model.User;

/**
 * @author Paulo Gandra de Sousa
 */
public interface UserRepository {

	<S extends User> List<S> saveAll(Iterable<S> entities);

	<S extends User> S save(S entity);

	Optional<User> findById(Long objectId);

	default User getById(final Long id) {
		final var maybeUser = findById(id);
		// throws 404 Not Found if the user does not exist or is not enabled
		return maybeUser.filter(User::isEnabled).orElseThrow(() -> new NotFoundException(User.class, id));
	}

	Optional<User> findByUsername(String username);

	List<User> searchUsers(Page page, SearchUsersQuery query);
}
