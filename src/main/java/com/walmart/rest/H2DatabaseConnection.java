package com.walmart.rest;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.h2.tools.DeleteDbFiles;

public class H2DatabaseConnection {

    private static Connection dbConnection = null;
	private static final String DB_DRIVER = "org.h2.Driver";
	private static final String DB_CONNECTION = "jdbc:h2:~/ecom";
	private static final String DB_USER = "";
	private static final String DB_PASSWORD = "";

	public static void main(String[] args) {
		
	}
	
	static void createTables() throws SQLException {
		String CreateQuery1 = "CREATE TABLE IF NOT EXISTS orders (orderid INT PRIMARY KEY AUTO_INCREMENT NOT NULL, name VARCHAR(255), address VARCHAR(255), order_date DATE)";
		String CreateQuery2 = "CREATE TABLE IF NOT EXISTS items (orderid INT FOREIGN KEY REFERENCES orders(orderid), itemid VARCHAR(255));";
		Connection connection = getDBConnection();
		if (connection == null) System.out.println("connection is null");
        
		connection.setAutoCommit(false);
		

        PreparedStatement createPreparedStatement = null;
        createPreparedStatement = connection.prepareStatement(CreateQuery1);
        createPreparedStatement.executeUpdate();

        createPreparedStatement = connection.prepareStatement(CreateQuery2);
        createPreparedStatement.executeUpdate();
        
        createPreparedStatement.close();
       

	}
	
	static void insertOrder() throws SQLException { 
		
	}
	
	
	static Connection getDBConnection() {
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            if (dbConnection == null) dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER,
                    DB_PASSWORD);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }
	
	

}
