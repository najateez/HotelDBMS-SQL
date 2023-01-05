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

public class Room_Type {
	
	public static void readFromTableRoomType(){	
		
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
				 
				 //follow mr explaination on board
				 String sql = "SELECT * FROM Room_Type";
				 ResultSet rs = st.executeQuery(sql);  //ResultSet class import from library
				 
				while (rs.next() && count < readRows) {
					int roomType_id = rs.getInt("roomType_id");
					String room_type_name = rs.getString("room_type_name");
					Date created_date = rs.getDate("created_date");
					Date updated_date = rs.getDate("updated_date");
					boolean is_Active = rs.getBoolean("is_Active");
					
					System.out.println(roomType_id + " " + room_type_name + " " + created_date + " " + updated_date + " " + is_Active + " ");
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
	String sql = "update Room_Type set room_type_name = ?, created_date =?, updated_date =?, is_Active=? where roomType_id = ?";
	PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(sql);
	
	System.out.println("enter room type name to be updated:");
	String roomTypeName=in.next();
	
	pstmt.setString(1,roomTypeName);
	pstmt.setDate(2, new Date(System.currentTimeMillis()));
	pstmt.setDate(3, new Date(System.currentTimeMillis()));
	pstmt.setBoolean(4, true);
	pstmt.setInt(5, id);
	pstmt.executeUpdate();
	
	String sql2 = "SELECT * FROM Room_Type WHERE roomType_id = ?";
	PreparedStatement pstmt2 = (PreparedStatement) con.prepareStatement(sql2);
	pstmt2.setInt(1, id);
	ResultSet rs = pstmt2.executeQuery();
	if (rs.next()) {
		String room_type_name = rs.getString("room_type_name");
		Date createdDate = rs.getDate("created_date");
		Date updatedDate = rs.getDate("updated_date");
		boolean isActive = rs.getBoolean("is_Active");
		System.out.println(id + " " + room_type_name + " " + createdDate + " " + updatedDate + " " + isActive);
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
     	int roomType_id=in.nextInt();
   	
   	//below steps from this website:https://alvinalexander.com/java/java-mysql-delete-query-example/ 
       String sql = "delete from Room_type where roomType_id = ?";
       PreparedStatement preparedStmt = (PreparedStatement) con.prepareStatement(sql);
       preparedStmt.setInt(1, roomType_id);
       // execute the preparedstatement
       preparedStmt.execute();
       con.close(); 
    }
 catch (Exception ex) {
     System.err.println(ex);
   }
}

public static void makeIsActiveFalseById() {
	
	}

public static void insertIntoRoomTypeTable(){
	
	String url = "jdbc:mysql://localhost:3306/hoteldbms";
	 String user = "root";
    String pass = "10@104Ar$";
    
    Connection con = null;
    PreparedStatement prst = null;
    
    Scanner in= new Scanner(System.in);
    
    System.out.println("Enter how many rows you want to insert:");
    int noOfRowsToBeInserted=in.nextInt();
    
   //      String room_type_name="TripleRoom";
  	     Date created_date = new Date(System.currentTimeMillis());
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
          int roomType_id=i+numberToAdd;
          
          String room_type_name = "najat" + numberToAdd;
          int is_Active = 1;  
          
          
          String roomTypeSql = "INSERT INTO Room_Type VALUES ("+roomType_id+",'"+room_type_name+"','"+created_date+"','"+updated_date+"',"+is_Active+")";
          
           prst = (PreparedStatement) con.prepareStatement(roomTypeSql);
           prst.executeUpdate();

          System.out.println(roomTypeSql);
        }
          
  } catch (Exception e) {
      System.out.println(e.getMessage());
  }
}

	
	
	// the way of creating table in database using java code
    public static boolean isRoomTypeTableCreated() {
		
    	//for boolean when we create table we can use Bit/tinyint.

		String url = "jdbc:mysql://localhost:3306/hoteldbms";
		 String user = "root";
	     String pass = "10@104Ar$"; 
			
		String roomTypeTable = "CREATE TABLE Room_Type (" 
		        + "roomType_id INT PRIMARY KEY,"  
		        + "room_type_name VARCHAR(50) NOT NULL," 
		        + "created_date date,"  
		        + "updated_date date,"
		        + "is_Active tinyint NOT NULL)";
		
	        Connection con = null;
	        
	        try {

	            Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
	            DriverManager.registerDriver(driver);
	            
	            con = DriverManager.getConnection(url, user,pass);
	            Statement st = con.createStatement();

	            int m = st.executeUpdate(roomTypeTable);
	            if (m >=  1) {
	                System.out.println("table room Type created successfully : " + roomTypeTable);
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
		
		isRoomTypeTableCreated();
		deleteById();
	/*	updateById();
		readFromTableRoomType();
		insertIntoRoomTypeTable(); */

	}

}
