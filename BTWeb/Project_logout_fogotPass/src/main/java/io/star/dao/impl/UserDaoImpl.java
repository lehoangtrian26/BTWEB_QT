package io.star.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import io.star.config.DBConnect;
import io.star.dao.UserDao;
import io.star.models.User;

public class UserDaoImpl implements UserDao {

	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;

	@Override
	public User login(String username, String password) {
		String sql = "SELECT * FROM users WHERE username=? AND password=?";
		try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, username);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return extractUser(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User get(String username) {
		String sql = "SELECT * FROM users WHERE username = ?";
		try {
			conn = DBConnect.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			rs = ps.executeQuery();

			if (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setUserName(rs.getString("username"));
				user.setPassWord(rs.getString("password"));
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e) {
			}
			try {
				if (ps != null)
					ps.close();
			} catch (Exception e) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
		return null;
	}

	// Hàm private để tái sử dụng (convert từ ResultSet -> User object)
	private User extractUser(ResultSet rs) throws Exception {
		User u = new User();
		u.setId(rs.getInt("id"));
		u.setUserName(rs.getString("username"));
		u.setPassWord(rs.getString("password"));
		return u;
	}

	@Override
	public void insert(User user) throws Exception {
		String sql = "INSERT INTO users (username, password) VALUES (?,?)";
		try (Connection conn = new DBConnect().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getPassWord()); // đã hash trước khi gọi
			ps.executeUpdate();
		}
	}

	private boolean existsByQuery(String query, String param) throws Exception {
		try (Connection conn = new DBConnect().getConnection();
				PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setString(1, param);
			try (ResultSet rs = ps.executeQuery()) {
				return rs.next();
			}
		}
	}

	@Override
	public boolean checkExistUsername(String username) throws Exception {
		return existsByQuery("SELECT 1 FROM users WHERE username = ?", username);
	}
}
