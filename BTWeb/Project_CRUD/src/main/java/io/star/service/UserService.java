package io.star.service;

import io.star.models.User;

public interface UserService {

	User login(String username, String password);

	User get(String username);

	boolean register(String username, String password, int role) throws Exception;

	boolean checkExistUsername(String username) throws Exception;

	void insert(User user) throws Exception;
}
