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

public class Rooms {
	
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
			 String sql = "SELECT * FROM Rooms";
			 ResultSet rs = st.executeQuery(sql);  //ResultSet class import from library
			 
			while (rs.next() && count < readRows) {
				int rooms_id = rs.getInt("rooms_id");
				int roomType_id = rs.getInt("roomType_id");
				int id = rs.getInt("id");
				Date created_date = rs.getDate("created_date");
				Date updated_date = rs.getDate("updated_date");
				boolean is_Active = rs.getBoolean("is_Active");
				
				System.out.println("rooms id:"+rooms_id + ", room type id:" + roomType_id +", id:" + id +", created date:" + created_date + ", updated date:" + updated_date + ", isActive:" + is_Active + " ");
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
		int rooms_id = in.nextInt();
		
		String sql = "select * from Rooms where rooms_id='" + rooms_id + "'";
		ResultSet rs = st.executeQuery(sql);
		while (rs.next() && count <= rooms_id) {
			int r_id = rs.getInt(1);
			int roomType_id = rs.getInt(2);
			int id = rs.getInt(3);
			Date create_date = rs.getDate(4);
			Date update_date = rs.getDate(5);
			String is_Active = rs.getString(6);
			
			System.out.println("rooms id:" + r_id + "," + "room type id:" + roomType_id + ","
			+ "hotel id:" + id + "," + "created date:" + create_date + ","  
			+ "updated date:" + update_date + "," + "is_Active:"+ is_Active);
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
	System.out.println("Enter the id of the row to update:");
	int rooms_id = in.nextInt();
	Connection con = null;
	try {
		Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
		DriverManager.registerDriver(driver);
		con = DriverManager.getConnection(url, user, pass);
		
		//some instructions i follow from this website: https://alvinalexander.com/java/java-mysql-update-query-example/
		
		// create the java mysql update preparedstatement
		String sql = "update Rooms set created_date =?, updated_date =?, is_Active=? where rooms_id = ?";
		PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(sql);
		
	/*	pstmt.setInt(1, roomType_id);
		pstmt.setInt(2, id); */
		pstmt.setDate(1, new Date(System.currentTimeMillis()));
		pstmt.setDate(2, new Date(System.currentTimeMillis()));
		pstmt.setBoolean(3, true);
		pstmt.setInt(4, rooms_id);
		pstmt.executeUpdate();
		
		String sql2 = "SELECT * FROM Rooms WHERE rooms_id = ?";
		PreparedStatement pstmt2 = (PreparedStatement) con.prepareStatement(sql2);
		pstmt2.setInt(1, rooms_id);
		ResultSet rs = pstmt2.executeQuery();
		if (rs.next()) {
			int roomType_id = rs.getInt("roomType_id");
			int id = rs.getInt("id");
			Date createdDate = rs.getDate("created_date");
			Date updatedDate = rs.getDate("updated_date");
			boolean isActive = rs.getBoolean("is_Active");
			System.out.println("rooms id:"+rooms_id + ", room type id:" + roomType_id  + ", id:" + id + ", created date:" + createdDate + ", updated date:" + updatedDate+ ", isActive:" + isActive);
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
       
       System.out.println("enter rooms id you want to delete:");
   	   int rooms_id=in.nextInt();
   	 
       String sql = "delete from Rooms where rooms_id = ?";
       PreparedStatement preparedStmt = (PreparedStatement) con.prepareStatement(sql);
       preparedStmt.setInt(1, rooms_id);
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
	 
	 System.out.println("enter rooms id to keep it not active:");
	 int rooms_id=in.nextInt();
	 
	 // similar way as update by id
   /* hotel name and hotel location i did not mention because i want to keep them same as previous value
     in database without change them.
    */
	String sql = "update Rooms set created_date = ?, updated_date =?, is_Active =? where rooms_id = ?";
	PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(sql);
	
	pstmt.setDate(1, new Date(System.currentTimeMillis()));
	pstmt.setDate(2, new Date(System.currentTimeMillis()));
	pstmt.setBoolean(3, false); //true=1, false=0 
	pstmt.setInt(4, rooms_id);
	pstmt.executeUpdate();
	
	String sql2 = "SELECT * FROM Rooms WHERE rooms_id = ?";
	
	PreparedStatement pstmt2 = (PreparedStatement) con.prepareStatement(sql2);
	pstmt2.setInt(1, rooms_id);
	ResultSet rs = pstmt2.executeQuery();
	if (rs.next()) {
		int roomType_id = rs.getInt("roomType_id");
		int id = rs.getInt("id");
		Date createdDate = rs.getDate("created_date");
		Date updatedDate = rs.getDate("updated_date");
		boolean isActive = rs.getBoolean("is_Active");
		System.out.println("rooms id:"+rooms_id + ", room type id:" + roomType_id + ", id:" + id + ", created date:" + createdDate + ", updated date:" + updatedDate+ ", isActive:" + isActive);
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
          int rooms_id=i+numberToAdd;
          
     /*     String hotel_name = "najat" + numberToAdd;
          String hotel_location = "najat" + numberToAdd; */
          int is_Active = 1;  
          
          System.out.println("enter room type name from room type table:");
          String room_type_name=in.next();
          System.out.println("enter hotel name from hotel table:");
          String hotel_name=in.next();
          
          String sql1="SELECT roomType_id FROM room_type WHERE room_type_name='"+room_type_name+"'";
          ResultSet rs = st.executeQuery(sql1);
          rs.next();
			int roomType_id = rs.getInt("roomType_id");
			
          String sql2="SELECT id FROM hotels WHERE hotel_name='"+hotel_name+"'";
          ResultSet rs1 = st.executeQuery(sql2);
           rs1.next();
			int id = rs1.getInt("id");
			
          String sql = "INSERT INTO Rooms VALUES ("+rooms_id+",'"+roomType_id+"','"+id+"','"+created_date+"','"+updated_date+"',"+is_Active+")";
			
           prst = (PreparedStatement) con.prepareStatement(sql);
           prst.executeUpdate();

          System.out.println(sql);
          }
          
  } catch (Exception e) {
      System.out.println(e.getMessage());
  }
}

	

	
	// the way of creating table in database using java code
    public static boolean isRoomsTableCreated() {
		
    	//for boolean when we create table we can use Bit/tinyint.

		String url = "jdbc:mysql://localhost:3306/hoteldbms";
		 String user = "root";
	     String pass = "10@104Ar$"; 
			
		String roomsTable = "CREATE TABLE Rooms (" 
		        + "rooms_id INT PRIMARY KEY,"  
		        + "roomType_id int REFERENCES Room_Type (roomType_id)," 
		        + "id int REFERENCES Hotels (id),"  
		        + "created_date date NOT NULL,"
		        + "updated_date date,"
		        + "is_Active tinyint NOT NULL)";
		
	
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
		
Scanner in=new Scanner(System.in);
		
		boolean isExit=true;
		
	do {
		System.out.println("Menu:");
		System.out.println("1:Create hotels table.");
		System.out.println("2:Insert values into hotel table.");
		System.out.println("3:Delete by hotel id.");
		System.out.println("4:update by hotel id.");
		System.out.println("5:Read From Hotels Table.");
		System.out.println("6:Exit App.");
		System.out.println("7:Get by id. (full row information).");
		System.out.println("8:Make Is_Active False By Id.");
		System.out.println("*******************************");
		System.out.println("Enter any number from menu above");
		int option=in.nextInt();
		
		switch(option) {
		case 1:{
		 isRoomsTableCreated();
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
			System.out.println("it is not an option, try again.");
			System.out.println("*******************************");
		}	
	}
	}while(isExit);
	}
}
