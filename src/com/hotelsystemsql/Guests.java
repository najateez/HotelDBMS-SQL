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
			 String sql = "SELECT * FROM Guests";
			 ResultSet rs = st.executeQuery(sql);  //ResultSet class import from library
			 
			while (rs.next() && count < readRows) {
				int guests_id = rs.getInt("guests_id");
				String guest_name = rs.getString("guest_name");
				String guest_phone = rs.getString("guest_phone");
				int guest_accompanying_members = rs.getInt("guest_accompanying_members");
				int guest_payment_amount = rs.getInt("guest_payment_amount");
				int rooms_id = rs.getInt("rooms_id");
				int id = rs.getInt("id");
				Date created_date = rs.getDate("created_date");
				Date updated_date = rs.getDate("updated_date");
				boolean is_Active = rs.getBoolean("is_Active");
				
				System.out.println("guests id:"+guests_id + ", guest name:" + guest_name +", guest phone:" + guest_phone +", guest accompanying members:" +guest_accompanying_members+", guest payment amount:"+ guest_payment_amount+ ", rooms id:"+ rooms_id+ ", id:"+id+ ", created date:"+ created_date + ", updated date:" + updated_date + ", is_Active:" + is_Active + " ");
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
		System.out.println("enter guests id you want to search:");
		int guests_id = in.nextInt();
		
		String sql = "select * from Guests where guests_id='" + guests_id + "'";
		ResultSet rs = st.executeQuery(sql);
		while (rs.next() && count <= guests_id) {
			int guestsId = rs.getInt(1);
			String guest_name = rs.getString(2);
			String guest_phone = rs.getString(3);
			int guest_accompanying_members = rs.getInt(4);
			int guest_payment_amount = rs.getInt(5);
			int rooms_id = rs.getInt(6);
			int id = rs.getInt(7);
			Date create_date = rs.getDate(8);
			Date update_date = rs.getDate(9);
			String is_Active = rs.getString(10);
			
			System.out.println("Id:" + guestsId + "," + "guest name:" + guest_name + ","
			+ "guest phone:" + guest_phone + "," + "guest accompanying members:" + guest_accompanying_members + ","
			+ "guest payment amount:"+ guest_payment_amount +","+"rooms id:"+rooms_id+","
			+ "id:"+id+","+ "created date:" + create_date + "," + "updated date:" + update_date
			+ "," + "is_Active:"+ is_Active);
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
System.out.println("Enter the guests id of the row to update:");
int guests_id = in.nextInt();
Connection con = null;
try {
	Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
	DriverManager.registerDriver(driver);
	con = DriverManager.getConnection(url, user, pass);
	
	//some instructions i follow from this website: https://alvinalexander.com/java/java-mysql-update-query-example/
	
	// create the java mysql update preparedstatement
	String sql = "update Guests set guest_name = ?, guest_phone = ?, guest_accompanying_members = ?, guest_payment_amount = ?,  created_date =?, updated_date =?, is_Active=? where guests_id = ?";
	PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(sql);
	
	System.out.println("enter guest name to be updated:");
	String guest_name=in.next();
	System.out.println("enter guest phone to be updated:");
	String guest_phone=in.next();
	System.out.println("enter guest accompanying members to be updated:");
	int guest_accompanying_members=in.nextInt();
	System.out.println("enter guest payment amount to be updated:");
	int guest_payment_amount=in.nextInt();
	
	pstmt.setString(1,guest_name);
	pstmt.setString(2,guest_phone);
	pstmt.setInt(3,guest_accompanying_members);
	pstmt.setInt(4,guest_payment_amount);
	pstmt.setDate(5, new Date(System.currentTimeMillis()));
	pstmt.setDate(6, new Date(System.currentTimeMillis()));
	pstmt.setBoolean(7, true);
	pstmt.setInt(8, guests_id);
	pstmt.executeUpdate();
	
	String sql2 = "SELECT * FROM Guests WHERE guests_id = ?";
	PreparedStatement pstmt2 = (PreparedStatement) con.prepareStatement(sql2);
	pstmt2.setInt(1, guests_id);
	ResultSet rs = pstmt2.executeQuery();
	if (rs.next()) {
		String guest_namee = rs.getString("guest_name");
		String guest_phonee = rs.getString("guest_phone");
		int guest_accompanying_memberss = rs.getInt("guest_accompanying_members");
		int guest_payment_amountt = rs.getInt("guest_payment_amount");
		int rooms_id = rs.getInt("rooms_id");
		int id = rs.getInt("id");
		Date createdDate = rs.getDate("created_date");
		Date updatedDate = rs.getDate("updated_date");
		boolean isActive = rs.getBoolean("is_Active");
		System.out.println("guests id:"+guests_id + ", guest name:" + guest_namee + ", guest phone:" + guest_phonee + ", guest accompanying members:" + guest_accompanying_memberss+ ", guest payment amount:"+ guest_payment_amountt + ", rooms id:" + rooms_id+ ", id:"+ id+", created date:"+  createdDate + ", updated date:" + updatedDate+ ", isActive:" + isActive);
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
       
       System.out.println("enter guests id you want to delete:");
     	int guests_id=in.nextInt();
   	
   	//below steps from this website:https://alvinalexander.com/java/java-mysql-delete-query-example/ 
       String sql = "delete from Guests where guests_id = ?";
       PreparedStatement preparedStmt = (PreparedStatement) con.prepareStatement(sql);
       preparedStmt.setInt(1, guests_id);
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
	 
	 System.out.println("enter guests id to keep it not active:");
	 int guests_id=in.nextInt();
	 
	 // similar way as update by id
   /* hotel name and hotel location i did not mention because i want to keep them same as previous value
     in database without change them.
    */
	String sql = "update Guests set created_date = ?, updated_date =?, is_Active =? where guests_id = ?";
	PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(sql);
	
	pstmt.setDate(1, new Date(System.currentTimeMillis()));
	pstmt.setDate(2, new Date(System.currentTimeMillis()));
	pstmt.setBoolean(3, false); //true=1, false=0 
	pstmt.setInt(4, guests_id);
	pstmt.executeUpdate();
	
	String sql2 = "SELECT * FROM Guests WHERE guests_id = ?";
	
	PreparedStatement pstmt2 = (PreparedStatement) con.prepareStatement(sql2);
	pstmt2.setInt(1, guests_id);
	ResultSet rs = pstmt2.executeQuery();
	if (rs.next()) {
		String guest_name = rs.getString("guest_name");
		String guest_phone = rs.getString("guest_phone");
		int guest_accompanying_members = rs.getInt("guest_accompanying_members");
		int guest_payment_amount = rs.getInt("guest_payment_amount");
		int rooms_id = rs.getInt("rooms_id");
		int id = rs.getInt("id");
		Date createdDate = rs.getDate("created_date");
		Date updatedDate = rs.getDate("updated_date");
		boolean isActive = rs.getBoolean("is_Active");
		System.out.println("guests id:"+guests_id + ", guest name:" + guest_name + ", guest phone:" + guest_phone + ", guest accompanying members:" + guest_accompanying_members+", guest payment amount:"+ guest_payment_amount+ ", rooms id:"+ rooms_id+", id: "+ id+", created date:"+ createdDate + ", updated date:" + updatedDate+ ", isActive:" + isActive);
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
         int guests_id=i+numberToAdd;
         
        String guest_name = "najat" + numberToAdd;
         String guest_phone = "najat" + numberToAdd; 
         int guest_accompanying_members =  numberToAdd;
         int guest_payment_amount =  numberToAdd;
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
		
Scanner in=new Scanner(System.in);
		
		boolean isExit=true;
		
	do {
		System.out.println("Menu:");
		System.out.println("1:Create guests table.");
		System.out.println("2:Insert values into guests table.");
		System.out.println("3:Delete by guests id.");
		System.out.println("4:update by guests id.");
		System.out.println("5:Read From guests Table.");
		System.out.println("6:Exit App.");
		System.out.println("7:Get by id. (full row information).");
		System.out.println("8:Make Is_Active False By Id.");
		System.out.println("*******************************");
		System.out.println("Enter any number from menu above");
		int option=in.nextInt();
		
		switch(option) {
		case 1:{
		 isGuestsTableCreated();
		 break;
		}case 2:{
		 insertIntoTable();
		 break;
		}case 3:{
		 deleteById();
		 break;
		}case 4:{
		 updateById();
		 break;
		}case 5:{
		 readFromTable();
		 break;
		}case 6:{
		 isExit=false;
		 break;
		}case 7:{
		 getById();
		 break;
		}case 8:{
		 makeIsActiveFalseById();	
		 break;
		}default:{
			System.out.println("it is not an option, try again.");	
		}
		}
	}while(isExit);
	

	

	}

}
