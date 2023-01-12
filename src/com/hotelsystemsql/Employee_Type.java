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
					
					System.out.println("Employee type id:"+ employeeType_id + ", employee_type_name:" + employee_type_name + ", created_date:" + created_date + ", updated_date:" + updated_date + ", is_Active:" + is_Active + " ");
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
		System.out.println("enter id you want to search:");
		int employeeType_id = in.nextInt();
		
		String sql = "select * from Employee_type where employeeType_id='" + employeeType_id + "'";
		ResultSet rs = st.executeQuery(sql);
		while (rs.next() && count <= employeeType_id) {
			int id = rs.getInt(1);
			String employee_type_name = rs.getString(2);
			Date created_date = rs.getDate(3);
			Date updated_date = rs.getDate(4);
			String is_Active = rs.getString(5);
			
			System.out.println("Employee type id:" + id + "," + "employee type name:" + employee_type_name
			+ "," + "created date:" + created_date + ","+ "updated date:" + updated_date + "," 
			+ "is_Active:"+ is_Active);
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
	System.out.println("Enter the id of the row to update: ");
	int id = in.nextInt();
	Connection con = null;
	try {
		Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
		DriverManager.registerDriver(driver);
		con = DriverManager.getConnection(url, user, pass);
		
		//some instructions i follow from this website: https://alvinalexander.com/java/java-mysql-update-query-example/
		
		// create the java mysql update preparedstatement
		String sql = "update Employee_Type set employee_type_name = ?, created_date =?, updated_date =?, is_Active=? where employeeType_id = ?";
		PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(sql);
		
		System.out.println("enter employee type name to be updated:");
		String employeeTypeName=in.next();
		
		pstmt.setString(1,employeeTypeName);
		pstmt.setDate(2, new Date(System.currentTimeMillis()));
		pstmt.setDate(3, new Date(System.currentTimeMillis()));
		pstmt.setBoolean(4, true);
		pstmt.setInt(5, id);
		pstmt.executeUpdate();
		
		String sql2 = "SELECT * FROM Employee_Type WHERE employeeType_id = ?";
		PreparedStatement pstmt2 = (PreparedStatement) con.prepareStatement(sql2);
		pstmt2.setInt(1, id);
		ResultSet rs = pstmt2.executeQuery();
		if (rs.next()) {
			String employee_type_name = rs.getString("employee_type_name");
			Date createdDate = rs.getDate("created_date");
			Date updatedDate = rs.getDate("updated_date");
			boolean isActive = rs.getBoolean("is_Active");
			
			System.out.println("id:"+id + ", employee type name:" + employee_type_name + ", created date:" + createdDate + ", updated date:" + updatedDate + ", isActive:" + isActive);
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
       
       System.out.println("enter id you want to delete:");
    	int employeeType_id=in.nextInt();
   	
   	//below steps from this website:https://alvinalexander.com/java/java-mysql-delete-query-example/ 
       String sql = "delete from Employee_Type where employeeType_id = ?";
       PreparedStatement preparedStmt = (PreparedStatement) con.prepareStatement(sql);
       preparedStmt.setInt(1, employeeType_id);
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
	 
	 System.out.println("enter id to keep it not active:");
	 int employeeType_id=in.nextInt();
	 
	 // similar way as update by id
   /* hotel name and hotel location i did not mention because i want to keep them same as previous value
     in database without change them.
    */
	String sql = "update Employee_Type set created_date = ?, updated_date =?, is_Active =? where employeeType_id = ?";
	PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(sql);
	
	pstmt.setDate(1, new Date(System.currentTimeMillis()));
	pstmt.setDate(2, new Date(System.currentTimeMillis()));
	pstmt.setBoolean(3, false); //true=1, false=0 
	pstmt.setInt(4, employeeType_id);
	pstmt.executeUpdate();
	
	String sql2 = "SELECT * FROM Employee_Type WHERE employeeType_id = ?";
	
	PreparedStatement pstmt2 = (PreparedStatement) con.prepareStatement(sql2);
	pstmt2.setInt(1, employeeType_id);
	ResultSet rs = pstmt2.executeQuery();
	if (rs.next()) {
		String employee_type_name = rs.getString("employee_type_name");
		Date createdDate = rs.getDate("created_date");
		Date updatedDate = rs.getDate("updated_date");
		boolean isActive = rs.getBoolean("is_Active");
		System.out.println("employee type id:"+employeeType_id + ", employee type name:" + employee_type_name + ", created date:" + createdDate + ", updated date:" + updatedDate+ ", isActive:" + isActive);
	}
} catch (Exception e) {
	System.out.println(e);
}
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
		
Scanner in=new Scanner(System.in);
		
		boolean isExit=true;
		
	do {
		System.out.println("Menu:");
		System.out.println("1:Create Employee_Type table.");
		System.out.println("2:Insert values into EmployeeType table.");
		System.out.println("3:Delete by EmployeeType id.");
		System.out.println("4:update by EmployeeType id.");
		System.out.println("5:Read From EmployeeType Table.");
		System.out.println("6:Exit App.");
		System.out.println("7:Get by id. (full row information).");
		System.out.println("8:Make Is_Active False By Id.");
		System.out.println("*******************************");
		System.out.println("Enter any number from menu above");
		int option=in.nextInt();
		
		switch(option) {
		  case 1:{
			isEmployeeTypeTableCreated();
			System.out.println("*******************************");
			break;
		 }case 2:{
			 insertIntoEmployeeTypeTable(); 
			 System.out.println("*******************************");
			 break;
		 } case 3:{
			 deleteById();
			 System.out.println("*******************************");
			 break; 
		 }case 4:{
			 updateById();
			 System.out.println("*******************************");
			 break;
		 }case 5:{
			 readFromEmployeeTypeTable();
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
		 } default:{
			 System.out.println("it is not an option, try again.");
			 System.out.println("*******************************");
		 }
		}
	}while(isExit);
	

	}

}
