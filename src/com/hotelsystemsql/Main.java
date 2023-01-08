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

public class Main {
	
	public static void insert10000Hotels(long noOfHotelToBeInserted) {
		
		String url = "jdbc:mysql://localhost:3306/hoteldbms";
		 String user = "root";
	     String pass = "10@104Ar$";
	     
	     Connection con = null;
	     PreparedStatement prst = null;
	     
	     Scanner in= new Scanner(System.in);
	     
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
	            
	             
              for (int i = 0; i < noOfHotelToBeInserted; i++) {
              Random rn = new Random();   //here i import library of class Random.
              int numberToAdd = rn.nextInt(100);
              int id=i+numberToAdd;
              
              String hotel_name = "najat" + numberToAdd;
              String hotel_location = "najat" + numberToAdd;
              int is_Active = 1;  
               
              String sql = "INSERT INTO Hotels VALUES ("+id+",'"+hotel_name+"','"+hotel_location+"','"+created_date+"','"+updated_date+"',"+is_Active+")";
              
	            prst = (PreparedStatement) con.prepareStatement(sql);
	            prst.executeUpdate();

              System.out.println(sql);
            }
              
      } catch (Exception e) {
          System.out.println(e.getMessage());
      }
  }
	
	public static void insert1Hotel(int noOfHotelToBeInserted) {
		
		String url = "jdbc:mysql://localhost:3306/hoteldbms";
		 String user = "root";
	     String pass = "10@104Ar$";
	     
	     Connection con = null;
	     PreparedStatement prst = null;
	     
	     Scanner in= new Scanner(System.in);
	     
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
	            
	             
             for (int i = 0; i < noOfHotelToBeInserted; i++) {
             Random rn = new Random();   //here i import library of class Random.
             int numberToAdd = rn.nextInt(100);
             int id=i+numberToAdd;
             
             String hotel_name = "najat" + numberToAdd;
             String hotel_location = "najat" + numberToAdd;
             int is_Active = 1;  
              
             String sql = "INSERT INTO Hotels VALUES ("+id+",'"+hotel_name+"','"+hotel_location+"','"+created_date+"','"+updated_date+"',"+is_Active+")";
             
	            prst = (PreparedStatement) con.prepareStatement(sql);
	            prst.executeUpdate();

             System.out.println(sql);
           }
             
     } catch (Exception e) {
         System.out.println(e.getMessage());
     }
 }
	
	public static void print10Hotels(int noOfHotelsToBePrinted){
		
		 String url = "jdbc:mysql://localhost:3306/hoteldbms";
		    String user = "root";
	        String pass = "10@104Ar$";
	    
			Scanner in = new Scanner(System.in);
			
			int count = 0;
			Connection con = null;
			
			try {
				 Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
				 DriverManager.registerDriver(driver);
				 con = DriverManager.getConnection(url, user, pass);
				 Statement st = con.createStatement();
				 
				 //as mr explaination on board
				 String sql = "SELECT * FROM Hotels";
				 ResultSet rs = st.executeQuery(sql);  //ResultSet class import from library
				 
				while (rs.next() && count < noOfHotelsToBePrinted) {
					int id = rs.getInt("id");
					String hotel_name = rs.getString("hotel_name");
					String hotel_location = rs.getString("hotel_location");
					Date created_date = rs.getDate("created_date");
					Date updated_date = rs.getDate("updated_date");
					boolean isActive = rs.getBoolean("is_Active");
					
					System.out.println(id + " " + hotel_name+ " " + hotel_location +" " + created_date + " "
							+updated_date+ " "+ isActive);
					count++;
				 }
				con.close();
			}catch (Exception ex) {
				System.err.println(ex);
			}
		}
	
	
	public static void printHotelInformationByUserInput() {
		
		// by id
		
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
			int id = in.nextInt();
			
			String sql = "select * from Hotels where id='" + id + "'";
			ResultSet rs = st.executeQuery(sql);
			while (rs.next() && count <= id) {
				int hotel_id = rs.getInt(1);
				String hotel_name = rs.getString(2);
				String hotel_location = rs.getString(3);
				Date create_date = rs.getDate(4);
				Date update_date = rs.getDate(5);
				String is_Active = rs.getString(6);
				
				System.out.println("Id:" + id + "," + "hotel name:" + hotel_name + ","
				+ "hotel location:" + hotel_location + "," + "created date:" + create_date + ","  
				+ "updated date:" + update_date + "," + "is_Active:"+ is_Active);
				count++;
			}
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	
	public static void firsr10Hotelsis_Activefalse() {
		
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
		 
		 System.out.println("enter id to keep it not active with id's above it:");
    	 int id=in.nextInt();
    	 
    	 // similar way as update by id
       /* hotel name and hotel location i did not mention because i want to keep them same as previous value
         in database without change them.
        */
    	 
    	 /* from this sql below, when we enter any id from database, that id and the remaining id's above it
    	    (is_Active) will print 0, as false. 
    	  */
    	String sql = "update Hotels set created_date = ?, updated_date =?, is_Active =? where id <= ?";
		PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(sql);
		
		pstmt.setDate(1, new Date(System.currentTimeMillis()));
		pstmt.setDate(2, new Date(System.currentTimeMillis()));
		pstmt.setBoolean(3, false); //true=1, false=0 
		pstmt.setInt(4, id);
		pstmt.executeUpdate();
		
		String sql2 = "SELECT * FROM Hotels WHERE id = ?";
		
		PreparedStatement pstmt2 = (PreparedStatement) con.prepareStatement(sql2);
		pstmt2.setInt(1, id);
		ResultSet rs = pstmt2.executeQuery();
		if (rs.next()) {
			String hotelName = rs.getString("hotel_name");
			String hotelLocation = rs.getString("hotel_location");
			Date createdDate = rs.getDate("created_date");
			Date updatedDate = rs.getDate("updated_date");
			boolean isActive = rs.getBoolean("is_Active");
			System.out.println("this row of id "+ id + " and above it id's (is_Active) will be equal to zero. check in db." );
		}
	} catch (Exception e) {
		System.out.println(e);
	}
}


	public static void main(String[] args) {
		
        Scanner in=new Scanner(System.in);
		
		boolean isExit=true;
		
	do {
		System.out.println("Menu:");
		System.out.println("1:Insert 10,000 hotels.");
		System.out.println("2:Insert 1 hotel.");
		System.out.println("3:Print 10 hotels");
		System.out.println("4:Make first 10 hotels 'is_Active' = false");
		System.out.println("5:Print hotel information by user input");
		System.out.println("6:Exit App.");
		System.out.println("*******************************");
		System.out.println("Enter any number from menu above");
		int option=in.nextInt();
		
		switch(option) {
		case 1:{
			insert10000Hotels(10000);
			System.out.println("*******************************");
			break;
		}case 2:{
			insert1Hotel(1);
			System.out.println("*******************************");
			break;
		}case 3:{
			print10Hotels(10);
			System.out.println("*******************************");
			break;
		}case 4:{
			firsr10Hotelsis_Activefalse();
			System.out.println("*******************************");
			break;
		}case 5:{
			printHotelInformationByUserInput(); //id
			System.out.println("*******************************");
			break;
		}case 6:{
			isExit=false;
			break;
		}default:{
			System.out.println("it is not an option, try again.");
			System.out.println("*******************************");
	       }
		}
	}while(isExit);
	}

}
