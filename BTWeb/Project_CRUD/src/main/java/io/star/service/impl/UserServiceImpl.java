package io.star.service.impl;

import io.star.config.PasswordUtil;
import io.star.dao.UserDao;
import io.star.dao.impl.UserDaoImpl;
import io.star.models.User;
import io.star.service.UserService;

public class UserServiceImpl implements UserService {

	private UserDao userDao = new UserDaoImpl();

	@Override
	public User login(String username, String password) {
		// gọi DAO để lấy user theo username
		User user = this.get(username);

		// nếu user tồn tại và mật khẩu trùng khớp thì trả về
		if (user != null && PasswordUtil.verify(password, user.getPassWord())) {
			return user;
		}

		// sai thì return null
		return null;
	}

	@Override
	public User get(String username) {
		return userDao.get(username);
	}

	@Override
	public boolean register(String username, String password, int role)
			throws Exception {
		if (userDao.checkExistUsername(username))
			return false;
		String hashed = PasswordUtil.hashPassword(password);
		User u = new User(username, hashed, role);
		userDao.insert(u);
		return true;
	}

	// delegations
	@Override
	public boolean checkExistUsername(String username) throws Exception {
		return userDao.checkExistUsername(username);
	}

	@Override
	public void insert(User user) throws Exception {
		userDao.insert(user);
	}
}
