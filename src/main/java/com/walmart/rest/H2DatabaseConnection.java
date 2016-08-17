package com.walmart.rest;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.h2.tools.DeleteDbFiles;

public class H2DatabaseConnection {

    private static Connection dbConnection = null;
	private static final String DB_DRIVER = "org.h2.Driver";
	private static final String DB_CONNECTION = "jdbc:h2:~/test";
	private static final String DB_USER = "";
	private static final String DB_PASSWORD = "";

	public static void main(String[] args) {
		DeleteDbFiles.execute("~", "test", true);
	}
	
	static void createTables() throws SQLException {
		String CreateQuery1 = "CREATE TABLE orders (orderid INT PRIMARY KEY AUTO_INCREMENT NOT NULL, name VARCHAR(255), address VARCHAR(255), order_date DATE NOT NULL DEFAULT(GETDATE()));";
		String CreateQuery2 = "CREATE TABLE items (orderid INT, itemid VARCHAR(255), quantity INT);";
		Connection connection = getDBConnection();
		if (connection == null) System.out.println("connection is null");
        
		connection.setAutoCommit(false);
		

        PreparedStatement createPreparedStatement1 = null;
        PreparedStatement createPreparedStatement2 = null;
        createPreparedStatement1 = connection.prepareStatement(CreateQuery1);
        createPreparedStatement1.executeUpdate();

        createPreparedStatement2 = connection.prepareStatement(CreateQuery2);
        createPreparedStatement2.executeUpdate();
        
        createPreparedStatement1.close();
        createPreparedStatement2.close();
       

	}
	
	static int insertOrder(String address, String name, ArrayList<Pair<String, Integer>> items) throws SQLException {
		Connection connection = getDBConnection();
        Statement stmt = null;
        int orderID = 0;
        
        connection.setAutoCommit(false);

//        PreparedStatement insertPreparedStatement1 = null;
//        PreparedStatement insertPreparedStatement2 = null;
        String InsertQuery1 = "INSERT INTO ORDER" + "(name, address, order_date) values" + "(" + name + ", " + address + ")";

        stmt = connection.createStatement();
        String getLast = "select scope_identity()";
        stmt.execute(InsertQuery1);
		ResultSet rs = stmt.execute(getLast);
		
        return orderID;
	}
	
	
	
	static Connection getDBConnection() {
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
        	dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER,
                    DB_PASSWORD);
        	System.out.println("Connection made!! \n");
            			
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }
	
	

}
