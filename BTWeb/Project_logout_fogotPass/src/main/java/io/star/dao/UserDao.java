package io.star.dao;

import io.star.models.User;

public interface UserDao {

	User login(String username, String password);

	User get(String username);

	void insert(User user) throws Exception;

	boolean checkExistUsername(String username) throws Exception;

}
