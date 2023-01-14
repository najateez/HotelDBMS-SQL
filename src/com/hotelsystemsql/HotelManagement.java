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

public class HotelManagement {
	
	public static void insertIntoRoomTypeTable(){
		
		//3 Rooms types are-> 'STANDARD', 'DELUXE', 'SUITE'. only (must use).
		
		String url = "jdbc:mysql://localhost:3306/hoteldbms";
		 String user = "root";
	    String pass = "10@104Ar$";
	    
	    Connection con = null;
	    PreparedStatement prst = null;
	    
	    Scanner in= new Scanner(System.in);
	    
	    System.out.println("Enter how many rows you want to insert:");
	    int noOfRowsToBeInserted=in.nextInt();
	    
	   //      String room_type_name="standard";
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
	          
	          System.out.println("enter room type name(STANDARD/DELUXE/SUITE):");
	          String room_type_name =in.next();
	          int is_Active = 1;  
	          
	       if(room_type_name.equalsIgnoreCase("STANDARD")||room_type_name.equalsIgnoreCase("DELUXE")||room_type_name.equalsIgnoreCase("SUITE"))
	          {
	           String roomTypeSql = "INSERT INTO Room_Type VALUES ("+roomType_id+",'"+room_type_name+"','"+created_date+"','"+updated_date+"',"+is_Active+")";
	          
	           prst = (PreparedStatement) con.prepareStatement(roomTypeSql);
	           prst.executeUpdate();

	          System.out.println(roomTypeSql);
	        }else {
	        	 System.out.println("error: enter one of these options only (STANDARD/DELUXE/SUITE), try again.");
	        }
	     }
	          
	  } catch (Exception e) {
	      System.out.println(e.getMessage());
	  }
	}
	
	
	public static void insertIntoEmployeeTypeTable(){
		
		//Employee types are -> 'MANAGER', 'ATTENDANT', 'VALET', 'BUTLER', 'DIRECTOR'. only (must use).
		
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
	          
	          System.out.println("enter employee type name (MANAGER/ATTENDANT/VALET/BUTLER/DIRECTOR):");
	          String employee_type_name = in.next();
	          int is_Active = 1;
	          
	          if(employee_type_name.equalsIgnoreCase("MANAGER")||employee_type_name.equalsIgnoreCase("ATTENDANT")||employee_type_name.equalsIgnoreCase("VALET")||employee_type_name.equalsIgnoreCase("BUTLER")||employee_type_name.equalsIgnoreCase("DIRECTOR"))
	          {
	          String EmployeeTypeSql = "INSERT INTO Employee_Type VALUES ("+employeeType_id+",'"+employee_type_name+"','"+created_date+"','"+updated_date+"',"+is_Active+")";
	          
	           prst = (PreparedStatement) con.prepareStatement(EmployeeTypeSql);
	           prst.executeUpdate();

	          System.out.println(EmployeeTypeSql);
	        }else {
	        	System.out.println("error: enter one of these options only (MANAGER/ATTENDANT/VALET/BUTLER/DIRECTOR), try again.");
	        }
	          }
	  } catch (Exception e) {
	      System.out.println(e.getMessage());
	  }
	}
	
	
	public static void insertIntoGuestsTable(){
		
		/*  i created insertIntoGuestsTable to solve this function: guestsWhosNameEndWithE(). i inserted guests name
		    who's end with E. and for this function: RoomsWhereGuestsArePayingMoreThan1000(), for payment amount.  
		 */
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
	          
	           
	         for (int i = 1; i < noOfRowsToBeInserted; i++) {
	        	 Random rn = new Random();   //here i import library of class Random.
	             int numberToAdd = rn.nextInt(1000);
	             
	              int guests_id=i+numberToAdd;
	              
	         
	         System.out.println("enter guest name:");
	         String guest_name = in.next();
	        
	         String guest_phone = guest_name + numberToAdd;
	         
	         int guest_accompanying_members =  numberToAdd;
	         
	         System.out.println("enter room guest payment amount:");
	         int guest_payment_amount =  in.nextInt();
	         
	         int is_Active = 1;  
	         
	         System.out.println("enter room type name from room type table:");
	         String room_type_name=in.next(); 
	         
	         System.out.println("enter hotel name from hotel table:");
	         String hotel_name=in.next();
	         
	         String sql1="SELECT rooms_id FROM Rooms INNER JOIN Room_type ON Rooms.roomType_id=Room_type.roomType_id='"+room_type_name+"'";
	         ResultSet rs = st.executeQuery(sql1);
	         rs.next();
				int rooms_id = rs.getInt("rooms_id"); 
				
	         String sql2="SELECT id FROM Hotels WHERE hotel_name='"+hotel_name+"'";
	         ResultSet rs1 = st.executeQuery(sql2);
	          rs1.next();
				int id = rs1.getInt("id");
				
	         String sql = "INSERT INTO Guests VALUES ("+guests_id+",'"+guest_name+"','"+guest_phone+"',"+guest_accompanying_members+","+guest_payment_amount+","+rooms_id+","+id+",'"+created_date+"','"+updated_date+"',"+is_Active+")";
				
	       
	          prst = (PreparedStatement) con.prepareStatement(sql);
	          prst.executeUpdate();

	         System.out.println(sql);
	         
	         }
	         
	 } catch (Exception e) {
	     System.out.println(e.getMessage());
	 }
	}
	
	
	
	public static void guestsWhosNameEndWithE(){
		
		
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
			
			//if adding count or no it will not effects the result
			int count = 0;
			
			String sql = "select * from Guests where guest_name LIKE '%e'";
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				int guests_id = rs.getInt(1);
				String guest_name = rs.getString(2);
				String guest_phone = rs.getString(3);
				int guest_accompanying_members = rs.getInt(4);
				int guest_payment_amount = rs.getInt(5);
				int rooms_id = rs.getInt(6);
				int id = rs.getInt(7);
				Date create_date = rs.getDate(8);
				Date update_date = rs.getDate(9);
				Boolean is_Active = rs.getBoolean(10);
				
				System.out.println("guests id:" + guests_id + "," + "guest name:" + guest_name + ","
				+ "guest phone:" + guest_phone + "," + "guest accompanying members:" + guest_accompanying_members + ","
				+ "guest payment amount:" + guest_payment_amount + ","+ "rooms id:" + rooms_id + ","
				+ "id:" + id + "," + "created date:" + create_date + "," + "updated date:" + update_date + "," 
				+ "is_Active:"+ is_Active);
				count++;
			}
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	
	
  public static void roomsWhereGuestsArePayingMoreThan1000(){
		
		
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
			
			//if adding count or no it will not effects the result
			int count = 0;
			
			String sql = "select * from Guests where guest_payment_amount > 1000";
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				int guests_id = rs.getInt(1);
				String guest_name = rs.getString(2);
				String guest_phone = rs.getString(3);
				int guest_accompanying_members = rs.getInt(4);
				int guest_payment_amount = rs.getInt(5);
				int rooms_id = rs.getInt(6);
				int id = rs.getInt(7);
				Date create_date = rs.getDate(8);
				Date update_date = rs.getDate(9);
				Boolean is_Active = rs.getBoolean(10);
				
				System.out.println("guests id:" + guests_id + "," + "guest name:" + guest_name + ","
				+ "guest phone:" + guest_phone + "," + "guest accompanying members:" + guest_accompanying_members + ","
				+ "guest payment amount:" + guest_payment_amount + ","+ "rooms id:" + rooms_id + ","
				+ "id:" + id + "," + "created date:" + create_date + "," + "updated date:" + update_date + "," 
				+ "is_Active:"+ is_Active);
				count++;
			}
		} catch (Exception e) {
			System.err.println(e);
		}
	}
  
  
  
  public static void allRoomsWhichAreNotActiveButRoomTypeIsDeluxe(){
		
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
			
			
			
			String sql = "select * from Rooms INNER JOIN Room_type ON Rooms.roomType_id=Room_type.roomType_id WHERE Room_type.room_type_name= 'deluxe' AND Rooms.is_Active=0";
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				// the repetition columns from second table i did not mention here because no need to repeat same information 2 times for both table.
				// first table Rooms. Second table Room_type.
				int rooms_id = rs.getInt(1);
				int room_type_id = rs.getInt(2);
				int id = rs.getInt(3);
				Date created_date = rs.getDate(4);
				Date updated_date = rs.getDate(5);
				Boolean is_Active = rs.getBoolean(6);
				String room_type_name=rs.getString(8); //here must be 8 not 7 because the place of this column is in 8th place. if i wrote 7 it will give result of roomType_id=37, which is wrong answer of column.
				
				System.out.println("Rooms id:" + rooms_id + "," + "room type id:" + room_type_id
				+ "," + "id:" + id + "," + "created date:" + created_date + ","+ "updated date:" + updated_date 
				+ ","+ "is_Active:"+ is_Active+ ","+"room type name:"+room_type_name);
			
			}
		} catch (Exception e) {
			System.err.println(e);
		}
	}
  
  
  
  public static void countOfGuestsWhoAreStayingInDELUXERooms() {
	  
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
			
			String sql = "SELECT count(guests_id) FROM guests INNER JOIN Room_type ON guests.rooms_id=Room_type.roomType_id where Room_type.room_type_name='deluxe';";
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				int guests_id = rs.getInt(1);

				System.out.println("count: No.of Guests Who Are Staying In DELUXE Room= " + guests_id);
				
			}
		} catch (Exception e) {
			System.err.println(e);
		}
	}

  
  
  public static void allRoomTypeInHotelsWhosNameHaveHorAreActiveButHaveMoreThan5Rooms(){
	  
	  String url = "jdbc:mysql://localhost:3306/hoteldbms";
		 String user = "root";
	     String pass = "10@104Ar$";
	     
	     int count = 0;
	     Connection con = null;
	     
        Scanner in= new Scanner(System.in);
		 
		 

	     
	try {
		 Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
		 DriverManager.registerDriver(driver);
		 con = DriverManager.getConnection(url, user, pass);
		 Statement st = con.createStatement();
		 
		 int shouldBeMoreThan5Rooms;
			System.out.println("Enter how many rooms you want to print on consol:");
			shouldBeMoreThan5Rooms = in.nextInt();
			
			if(shouldBeMoreThan5Rooms<6) {
				System.out.println("value entered less than 6, so no result on consol");	
			}else {

			
		String sql2 = "SELECT * FROM Room_type WHERE room_type_name LIKE '%h%' AND is_Active=1";
		
		ResultSet rs = st.executeQuery(sql2);
		
		while (rs.next() && count < shouldBeMoreThan5Rooms) {
			int roomType_id = rs.getInt("roomType_id");
			String room_type_name = rs.getString("room_type_name");
			Date createdDate = rs.getDate("created_date");
			Date updatedDate = rs.getDate("updated_date");
			boolean isActive = rs.getBoolean("is_Active");
			
			System.out.println("room type id:"+roomType_id + ", room type name:" + room_type_name + ", created date:" + createdDate + ", updated date:" + updatedDate+ ", isActive:" + isActive);
			count++;
		}
		con.close();
			}	
	} catch (Exception e) {
		System.out.println(e);
	}
	}
	 
  
public static void guestsWhoAreStayingInRoomsAndServedByEmployeeWhoHaveAInTheirName() {
	  
	
	
	
  }
  
  


	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
		while(true) {
		System.out.println("HotelManagement Task");
		System.out.println("Menu:");
		System.out.println("1:insert room_type values.But 3 Rooms types must be -> 'STANDARD', 'DELUXE', 'SUITE'.");
		System.out.println("2:insert employee_types values.But Employee Types must be-> 'MANAGER', 'ATTENDANT', 'VALET', 'BUTLER', 'DIRECTOR'");
		System.out.println("3:insert guests values. to solve 2 functions (4,5).");
		System.out.println("4:Guests who's name end with 'E'.");
		System.out.println("5:Rooms where guests are paying more than 1000.");
		System.out.println("6:Count of guests who are staying in 'DELUXE' rooms.");
		System.out.println("7:Guests who are staying in rooms and served by employee who have 'A' in their name.");
		System.out.println("8:All rooms which are not active but room type is 'Deluxe'.");
		System.out.println("9:All room type in hotels who's name have 'H' or are active but have more than 5 rooms.");
		System.out.println("10:Exit App.");
		System.out.println("*******************************");
		System.out.println("Enter any number from menu above");
		int option=in.nextInt();
		
		switch(option) {
		case 1:{
			insertIntoRoomTypeTable();
			System.out.println("*******************************");
			break;
		}case 2:{
			insertIntoEmployeeTypeTable();
			System.out.println("*******************************");
			break;
		}case 3:{
			insertIntoGuestsTable();
			System.out.println("*******************************");
			break;
		}case 4:{
			guestsWhosNameEndWithE();
			System.out.println("*******************************");
			break;
		}case 5:{
			roomsWhereGuestsArePayingMoreThan1000();
			System.out.println("*******************************");
			break;
		}case 6:{
			countOfGuestsWhoAreStayingInDELUXERooms();
			System.out.println("*******************************");
			break;
		}case 7:{
			
			
			break;
		}case 8:{
			allRoomsWhichAreNotActiveButRoomTypeIsDeluxe();
			System.out.println("*******************************");
			break;
		}case 9:{
			allRoomTypeInHotelsWhosNameHaveHorAreActiveButHaveMoreThan5Rooms();
			System.out.println("*******************************");
			break;
		}case 10:{
			return;
		}default:{
			System.out.println("it is not an option, try again.");
			System.out.println("*******************************");
		}
		}
		}
		
	
		

	}

}
