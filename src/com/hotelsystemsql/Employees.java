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
				 String sql = "SELECT * FROM Employees";
				 ResultSet rs = st.executeQuery(sql);  //ResultSet class import from library
				 
				while (rs.next() && count < readRows) {
					int employees_id = rs.getInt("employees_id");
					int employeeType_id = rs.getInt("employeeType_id");
					int rooms_id = rs.getInt("rooms_id");
					Date created_date = rs.getDate("created_date");
					Date updated_date = rs.getDate("updated_date");
					boolean is_Active = rs.getBoolean("is_Active");
					
					System.out.println("employees id:"+employees_id + ", employee type id:" + employeeType_id +", rooms id:" + rooms_id +", created date:" + created_date + ", updated date:" + updated_date + ", isActive:" + is_Active + " ");
					count++;
				 }
				con.close();
			}catch (Exception ex) {
				System.err.println(ex);
			}
		}
		

public static void getById(){
	
	String url = "jdbc:mysql://localhost:3306/hoteldbms";
    String user = "root";
    String pass = "10@104Ar$";
    
    Connection con = null;
    
    try {
		Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
		DriverManager.registerDriver(driver);
		con = DriverManager.getConnection(url, user, pass);
		Statement st = con.createStatement();
		
		Scanner in = new Scanner(System.in);
		
		int count = 0;
		System.out.println("enter employees id you want to search:");
		int employees_id = in.nextInt();
		
		String sql = "select * from Employees where employees_id='" + employees_id + "'";
		ResultSet rs = st.executeQuery(sql);
		while (rs.next() && count <= employees_id) {
			int emps_id = rs.getInt(1);
			int employeeType_id = rs.getInt(2);
			int rooms_id = rs.getInt(3);
			Date create_date = rs.getDate(4);
			Date update_date = rs.getDate(5);
			Boolean is_Active = rs.getBoolean(6);
			
			System.out.println("Id:" + emps_id + "," + "employee type id:" + employeeType_id + ","
			+ "rooms id:" + rooms_id + "," + "created date:" + create_date + ","  
			+ "updated date:" + update_date + "," + "isActive:"+ is_Active);
			count++;
		}
	} catch (Exception e) {
		System.err.println(e);
	}
}


public static void updateById(){
	
	 String url = "jdbc:mysql://localhost:3306/hoteldbms";
	    String user = "root";
  String pass = "10@104Ar$";
	
	Scanner in = new Scanner(System.in);
	System.out.println("Enter employees id of the row to update:");
	int employees_id = in.nextInt();
	Connection con = null;
	try {
		Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
		DriverManager.registerDriver(driver);
		con = DriverManager.getConnection(url, user, pass);
		
		//some instructions i follow from this website: https://alvinalexander.com/java/java-mysql-update-query-example/
		
		// create the java mysql update preparedstatement
		String sql = "update Employees set created_date =?, updated_date =?, is_Active=? where employees_id = ?";
		PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(sql);
		
	/*	pstmt.setInt(1, roomType_id);
		pstmt.setInt(2, id); */
		pstmt.setDate(1, new Date(System.currentTimeMillis()));
		pstmt.setDate(2, new Date(System.currentTimeMillis()));
		pstmt.setBoolean(3, true);
		pstmt.setInt(4, employees_id);
		pstmt.executeUpdate();
		
		String sql2 = "SELECT * FROM Employees WHERE employees_id = ?";
		PreparedStatement pstmt2 = (PreparedStatement) con.prepareStatement(sql2);
		pstmt2.setInt(1, employees_id);
		ResultSet rs = pstmt2.executeQuery();
		if (rs.next()) {
			int employeeType_id = rs.getInt("employeeType_id");
			int rooms_id = rs.getInt("rooms_id");
			Date createdDate = rs.getDate("created_date");
			Date updatedDate = rs.getDate("updated_date");
			boolean isActive = rs.getBoolean("is_Active");
			System.out.println("employees id:"+employees_id + ", employee type id:" + employeeType_id  + ", rooms id:" + rooms_id + ", created date:" + createdDate + ", updated date:" + updatedDate+ ", isActive:" + isActive);
		}
	} catch (Exception e) {
		System.out.println(e);
	}
}
	
	

public static void deleteById() {
	
	String url = "jdbc:mysql://localhost:3306/hoteldbms";
	 String user = "root";
    String pass = "10@104Ar$";
    
    Scanner in=new Scanner(System.in);
    
   Connection con = null;
   try {
       Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
       DriverManager.registerDriver(driver);
       
       con = DriverManager.getConnection(url, user, pass);
       Statement st = con.createStatement();
       
       System.out.println("enter employees id you want to delete:");
   	   int employees_id=in.nextInt();
   	
   	//below steps from this website:https://alvinalexander.com/java/java-mysql-delete-query-example/ 
       String sql = "delete from Employees where employees_id = ?";
       PreparedStatement preparedStmt = (PreparedStatement) con.prepareStatement(sql);
       preparedStmt.setInt(1, employees_id);
       // execute the preparedstatement
       preparedStmt.execute();
       con.close(); 
    }
 catch (Exception ex) {
     System.err.println(ex);
   }
}


public static void makeIsActiveFalseById() {
	
	String url = "jdbc:mysql://localhost:3306/hoteldbms";
	 String user = "root";
    String pass = "10@104Ar$";
    
    Connection con = null;
    
try {
	 Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
	 DriverManager.registerDriver(driver);
	 con = DriverManager.getConnection(url, user, pass);
	 Statement st = con.createStatement();
	 
	 Scanner in= new Scanner(System.in);
	 
	 System.out.println("enter employees id to keep it not active:");
	 int employees_id=in.nextInt();
	 
	 // similar way as update by id
   /* hotel name and hotel location i did not mention because i want to keep them same as previous value
     in database without change them.
    */
	String sql = "update Employees set created_date = ?, updated_date =?, is_Active =? where employees_id = ?";
	PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(sql);
	
	pstmt.setDate(1, new Date(System.currentTimeMillis()));
	pstmt.setDate(2, new Date(System.currentTimeMillis()));
	pstmt.setBoolean(3, false); //true=1, false=0 
	pstmt.setInt(4, employees_id);
	pstmt.executeUpdate();
	
	String sql2 = "SELECT * FROM Employees WHERE employees_id = ?";
	
	PreparedStatement pstmt2 = (PreparedStatement) con.prepareStatement(sql2);
	pstmt2.setInt(1, employees_id);
	ResultSet rs = pstmt2.executeQuery();
	if (rs.next()) {
		int employeeType_id = rs.getInt("employeeType_id");
		int rooms_id = rs.getInt("rooms_id");
		Date createdDate = rs.getDate("created_date");
		Date updatedDate = rs.getDate("updated_date");
		boolean isActive = rs.getBoolean("is_Active");
		System.out.println("employees id:"+ employees_id + ", employee type id:" + employeeType_id + ", rooms id: " + rooms_id + ", created date:" + createdDate + ", updated date:" + updatedDate+ ", isActive:" + isActive);
	}
} catch (Exception e) {
	System.out.println(e);
}
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
		
Scanner in=new Scanner(System.in);
		
		boolean isExit=true;
		
	do {
		System.out.println("Menu:");
		System.out.println("1:Create employees table.");
		System.out.println("2:Insert values into employees table.");
		System.out.println("3:Delete by employees id.");
		System.out.println("4:update by employees id.");
		System.out.println("5:Read From employees Table.");
		System.out.println("6:Exit App.");
		System.out.println("7:Get by id. (full row information).");
		System.out.println("8:Make Is_Active False By Id.");
		System.out.println("*******************************");
		System.out.println("Enter any number from menu above");
		int option=in.nextInt();
		
		switch(option) {
		case 1:{
		 isEmployeesTableCreated();
		 System.out.println("*******************************");
		  break;
		}case 2:{
		 insertIntoTable();
		 System.out.println("*******************************");
		  break;
		}case 3:{
		 deleteById();
		 System.out.println("*******************************");
		 break;
		}case 4:{
		 updateById();
		 System.out.println("*******************************");
		 break;
		}case 5:{
		 readFromTable();
		 System.out.println("*******************************");
		 break;
		}case 6:{
			isExit=false;
			break;
		}case 7:{
			getById();
			System.out.println("*******************************");
			break;
		}case 8:{
			makeIsActiveFalseById();
			System.out.println("*******************************");
			break;
		}default:{
			System.out.println("it is not an option, try again");
			System.out.println("*******************************");
		}
		}
	}while(isExit);
			
		
	


	}

}
