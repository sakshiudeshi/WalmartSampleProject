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
	
	private static void createTables() {
		
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
