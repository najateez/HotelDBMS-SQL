package com.hotelsystemsql;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Statement;

public class Employee_Type {
	
	public static void readFromTable(){
		
		
	}
	
public static void getById(){
	
	}

public static void updateById(){
	
	}

public static void deleteById() {
	
	}

public static void makeIsActiveFalseById() {
	
	}

public void insertIntoTable(){

	}
	
	// the way of creating table in database using java code
    public static boolean isEmployeeTypeTableCreated() {
		
    	//for boolean when we create table we can use Bit/tinyint.

		String url = "jdbc:mysql://localhost:3306/hoteldbms";
		 String user = "root";
	     String pass = "10@104Ar$"; 
			
		String employeeTypeTable = "CREATE TABLE Employee_Type (" 
		        + "employeeType_id INT PRIMARY KEY,"  
		        + "employee_type_name VARCHAR(50) NOT NULL," 
		        + "created_date date NOT NULL,"  
		        + "updated_date date,"
		        + "is_Active bit NOT NULL)";
		
	        Connection con = null;
	        
	        try {

	            Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
	            DriverManager.registerDriver(driver);
	            
	            con = DriverManager.getConnection(url, user,pass);
	            Statement st = con.createStatement();

	            int m = st.executeUpdate(employeeTypeTable);
	            if (m >=  1) {
	                System.out.println("table room Type created successfully : " + employeeTypeTable);
	                return true;
	            }
	            else {
	                System.out.println("table room Type not created, try again");
	            }
	            con.close();
	        } catch (Exception ex) {
	            System.err.println(ex);
	  }
	        return false;
	}

	public static void main(String[] args) {
		
		isEmployeeTypeTableCreated();

	}

}
