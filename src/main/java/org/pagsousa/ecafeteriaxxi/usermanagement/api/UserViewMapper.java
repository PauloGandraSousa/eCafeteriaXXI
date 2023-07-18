package org.pagsousa.ecafeteriaxxi.usermanagement.api;

import java.util.List;

import org.mapstruct.Mapper;
import org.pagsousa.ecafeteriaxxi.usermanagement.domain.model.User;

/**
 * Based on https://github.com/Yoh0xFF/java-spring-security-example
 *
 */
@Mapper(componentModel = "spring")
public interface UserViewMapper {

	UserView toUserView(User user);

	List<UserView> toUserView(List<User> users);
}
