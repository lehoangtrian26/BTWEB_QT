package io.star.config;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnect {

	private static final String URL = "jdbc:mysql://localhost:3306/webht";
    private static final String USER = "root";
    private static final String PASSWORD = "huynhthang";
    private static Connection con;
    
    public static Connection getConnection() throws IOException{
        con = null;
        try {
            // Nạp driver
        	DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        	con = (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
    public static void main(String[] args) {
		try {
			Connection c = getConnection();
			if (c == null) {
				System.out.println("wrong");
			} else {
				System.out.println("ok");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
