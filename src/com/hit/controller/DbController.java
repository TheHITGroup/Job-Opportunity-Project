package com.hit.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 
 * Controller for the database. Uses mysql and jdbc
 *
 */
public class DbController
{
	
	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_CONNECTION = "jdbc:mysql://cs336.cvvd0wxa95qe.us-east-2.rds.amazonaws.com:3306/hitdb";
	private static final String DB_USER = "user";
	private static final String DB_PASSWORD = "user123";
	
	public Connection getDBConnection() {
		Connection dbConnection = null;
		try {
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}

		try {
			dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
			return dbConnection;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return dbConnection;
	}

	public PreparedStatement getPreparedStatement(Connection conn, String sql)
	{
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement =  conn.prepareStatement(sql);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return preparedStatement;
	}

}
