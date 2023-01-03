package com.hotelsystemsql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;
import java.util.Scanner;

import com.mysql.jdbc.PreparedStatement;

public class Employee_Type {
	
	public static void readFromEmployeeTypeTable(){
		
		 String url = "jdbc:mysql://localhost:3306/hoteldbms";
		    String user = "root";
	        String pass = "10@104Ar$";
	    
			Scanner in = new Scanner(System.in);
			
			int readRows;
			System.out.println("Enter how many users you want to print on consol:");
			readRows = in.nextInt();
			
			int count = 0;
			Connection con = null;
			
			try {
				 Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
				 DriverManager.registerDriver(driver);
				 con = DriverManager.getConnection(url, user, pass);
				 Statement st = con.createStatement();
				 
				 //as mr explaination on board
				 String sql = "SELECT * FROM Employee_Type";
				 ResultSet rs = st.executeQuery(sql);  //ResultSet class import from library
				 
				while (rs.next() && count < readRows) {
					int employeeType_id = rs.getInt("employeeType_id");
					String employee_type_name = rs.getString("employee_type_name");
					Date created_date = rs.getDate("created_date");
					Date updated_date = rs.getDate("updated_date");
					boolean is_Active = rs.getBoolean("is_Active");
					
					System.out.println(employeeType_id + " " + employee_type_name + " " + created_date + " " + updated_date + " " + is_Active + " ");
					count++;
				 }
				con.close();
			}catch (Exception ex) {
				System.err.println(ex);
			}
		}

public static void getById(){
	
	}

public static void updateById(){
	
	}

public static void deleteById() {
	
	}

public static void makeIsActiveFalseById() {
	
	}

public static void insertIntoEmployeeTypeTable(){
	
	String url = "jdbc:mysql://localhost:3306/hoteldbms";
	String user = "root";
    String pass = "10@104Ar$";
    
    Connection con = null;
    PreparedStatement prst = null;
    
    Scanner in= new Scanner(System.in);
    
    System.out.println("Enter how many rows you want to insert:");
    int noOfRowsToBeInserted=in.nextInt();
    
      //   String employee_type_name="Manager";
  	     Date created_date = new Date(System.currentTimeMillis());
  	     Date updated_date= new Date(System.currentTimeMillis());
  	  //   int is_Active=1;
  	     
  	  try {  //register driver and connect with sql
	        Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
            DriverManager.registerDriver(driver);
           
           con = DriverManager.getConnection(url, user,pass);
           Statement st = con.createStatement();
           
            
          for (int i = 0; i < noOfRowsToBeInserted; i++) {
          Random rn = new Random();   //here i import library of class Random.
          int numberToAdd = rn.nextInt(100);
          int employeeType_id=i+numberToAdd;
          
          String employee_type_name = "najat" + numberToAdd;
          int is_Active = 1;
          
          String EmployeeTypeSql = "INSERT INTO Employee_Type VALUES ("+employeeType_id+",'"+employee_type_name+"','"+created_date+"','"+updated_date+"',"+is_Active+")";
          
           prst = (PreparedStatement) con.prepareStatement(EmployeeTypeSql);
           prst.executeUpdate();

          System.out.println(EmployeeTypeSql);
        }
          
  } catch (Exception e) {
      System.out.println(e.getMessage());
  }
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
		readFromEmployeeTypeTable();
		insertIntoEmployeeTypeTable();

	}

}
