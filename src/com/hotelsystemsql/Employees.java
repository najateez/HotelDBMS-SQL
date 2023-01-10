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

public class Employees {
	
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

public static void insertIntoTable(){
	
	 String url = "jdbc:mysql://localhost:3306/hoteldbms";
	 String user = "root";
     String pass = "10@104Ar$";
     
     Connection con = null;
     PreparedStatement prst = null;
     
     Scanner in= new Scanner(System.in);
     
     System.out.println("Enter how many rows you want to insert:");
     int noOfRowsToBeInserted=in.nextInt();
     
   /*      String hotel_name="bustan";
   	     String hotel_location="Athaiba"; */
   	     Date created_date = new Date(System.currentTimeMillis()); //will take current date/today's date.
   	     Date updated_date= new Date(System.currentTimeMillis());
   //	     int is_Active=1;
   	     
   	  try {  //register driver and connect with sql
 	        Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
            DriverManager.registerDriver(driver);
            
            con = DriverManager.getConnection(url, user,pass);
            Statement st = con.createStatement();
            
             
           for (int i = 0; i < noOfRowsToBeInserted; i++) {
           Random rn = new Random();   //here i import library of class Random.
           int numberToAdd = rn.nextInt(100);
           int employees_id=i+numberToAdd;
           
      /*     String hotel_name = "najat" + numberToAdd;
           String hotel_location = "najat" + numberToAdd; */
           int is_Active = 1;  
            
           System.out.println("enter employee_type_name from employee type table:");
           String employee_type_name=in.next();
           
         /*  System.out.println("enter room_type_name:");
           String room_type_name=in.next(); */
           
           String sql1="SELECT employeeType_id FROM Employee_Type WHERE employee_type_name='" + employee_type_name+"'";
           ResultSet rs = st.executeQuery(sql1);
			rs.next();
			int employeeType_id = rs.getInt("employeeType_id");
			
			/* -we used inner join here because after first columns all id's come. (rooms table).
			  - if no id's after first column, but there is name,... we will use select.
			 */

			String sql2 = "SELECT rooms_id FROM Rooms INNER JOIN Room_type ON Rooms.roomType_id=Room_type.roomType_id";
				//	+ room_type_name + "'";

			ResultSet rs1 = st.executeQuery(sql2);
            rs1.next();
            int rooms_id=rs1.getInt("rooms_id");
           
            String sql = "INSERT INTO Employees VALUES ("+employees_id+",'"+employeeType_id+"','"+rooms_id+"','"+created_date+"','"+updated_date+"',"+is_Active+")";
           
            prst = (PreparedStatement) con.prepareStatement(sql);
            prst.executeUpdate();

           System.out.println(sql);
         }
           
   } catch (Exception e) {
       System.out.println(e.getMessage());
   }
}

	
	// the way of creating table in database using java code
    public static boolean isEmployeesTableCreated() {
		
    	//for boolean when we create table we can use Bit/tinyint.

		String url = "jdbc:mysql://localhost:3306/hoteldbms";
		 String user = "root";
	     String pass = "10@104Ar$"; 
			
		String employeesTable = "CREATE TABLE Employees (" 
		        + "employees_id INT PRIMARY KEY,"  
		        + "employeeType_id int REFERENCES Employee_Type (employeeType_id)," 
		        + "rooms_id int REFERENCES Rooms (rooms_id),"  
		        + "created_date date NOT NULL,"
		        + "updated_date date,"
		        + "is_Active bit NOT NULL)";
		
	        Connection con = null;
	        
	        try {

	            Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
	            DriverManager.registerDriver(driver);
	            
	            con = DriverManager.getConnection(url, user,pass);
	            Statement st = con.createStatement();

	            int m = st.executeUpdate(employeesTable);
	            if (m >=  1) {
	                System.out.println("table employees Type created successfully : " + employeesTable);
	                return true;
	            }
	            else {
	                System.out.println("table employees not created, try again");
	            }
	            con.close();
	        } catch (Exception ex) {
	            System.err.println(ex);
	  }
	        return false;
	}

	public static void main(String[] args) {
		
		isEmployeesTableCreated();
		insertIntoTable();

	}

}
