package com.hotelsystemsql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.Scanner;

import com.mysql.jdbc.PreparedStatement;

public class Guests {
	
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
         int guests_id=i+numberToAdd;
         
        String guest_name = "najat" + numberToAdd;
         String guest_phone = "najat" + numberToAdd; 
         int guest_accompanying_members =  numberToAdd;
         int guest_payment_amount =  numberToAdd;
         int is_Active = 1;  
         
      System.out.println("enter room type name:");
         String room_type_name=in.next(); 
         
         System.out.println("enter hotel name:");
         String hotel_name=in.next();
         
         String sql1="SELECT rooms_id FROM Rooms INNER JOIN Room_type ON Rooms.roomType_id=Room_type.roomType_id='"+room_type_name+"'";
         ResultSet rs = st.executeQuery(sql1);
         rs.next();
			int rooms_id = rs.getInt("rooms_id"); 
			
         String sql2="SELECT id FROM Hotels WHERE hotel_name='"+hotel_name+"'";
         ResultSet rs1 = st.executeQuery(sql2);
          rs1.next();
			int id = rs1.getInt("id");
			
         String sql = "INSERT INTO Guests VALUES ("+guests_id+",'"+guest_name+"','"+guest_phone+"','"+guest_accompanying_members+"',"+guest_payment_amount+","+rooms_id+","+id+",'"+created_date+"','"+updated_date+"',"+is_Active+")";
			
       
          prst = (PreparedStatement) con.prepareStatement(sql);
          prst.executeUpdate();

         System.out.println(sql);
         }
         
 } catch (Exception e) {
     System.out.println(e.getMessage());
 }
}

	
	// the way of creating table in database using java code
    public static boolean isGuestsTableCreated() {
		
    	//for boolean when we create table we can use Bit/tinyint.

		String url = "jdbc:mysql://localhost:3306/hoteldbms";
		 String user = "root";
	     String pass = "10@104Ar$"; 
			
		String roomsTable = "CREATE TABLE Guests (" 
		        + "guests_id INT PRIMARY KEY,"  
		        + "guest_name VARCHAR(50) NOT NULL," 
		        + "guest_phone VARCHAR(50) NOT NULL,"  
		        + "guest_accompanying_members INT NOT NULL,"
		        + "guest_payment_amount INT NOT NULL,"
		        + "rooms_id int REFERENCES Rooms (rooms_id),"
		        + "id int REFERENCES Hotels (id),"
		        + "created_date date NOT NULL,"
		        + "updated_date date,"
		        + "is_Active bit NOT NULL)";
		
	
	        Connection con = null;
	        
	        try {

	            Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
	            DriverManager.registerDriver(driver);
	            
	            con = DriverManager.getConnection(url, user,pass);
	            Statement st = con.createStatement();

	            int m = st.executeUpdate(roomsTable);
	            if (m >=  1) {
	                System.out.println("table rooms created successfully : " + roomsTable);
	                return true;
	            }
	            else {
	                System.out.println("table rooms not created, try again");
	            }
	            con.close();
	        } catch (Exception ex) {
	            System.err.println(ex);
	  }
	        return false;
	}

	public static void main(String[] args) {
		
		isGuestsTableCreated();
		insertIntoTable();

	}

}
