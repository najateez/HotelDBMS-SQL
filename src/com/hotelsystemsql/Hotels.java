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



public class Hotels {
	
	public static void readFromHotelsTable(){
		
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
				 String sql = "SELECT * FROM Hotels";
				 ResultSet rs = st.executeQuery(sql);  //ResultSet class import from library
				 
				while (rs.next() && count < readRows) {
					int id = rs.getInt("id");
					String hotel_name = rs.getString("hotel_name");
					String hotel_locationn = rs.getString("hotel_location");
					Date created_date = rs.getDate("created_date");
					Date updated_date = rs.getDate("updated_date");
					boolean is_Active = rs.getBoolean("is_Active");
					
					System.out.println("id:"+id + ", hotel name:" + hotel_name +", hotel location: " + hotel_locationn +", created date:" + created_date + ", updated date:" + updated_date + ", isActive:" + is_Active + " ");
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

	
	public static void updateById(){
		
		    String url = "jdbc:mysql://localhost:3306/hoteldbms";
		    String user = "root";
	        String pass = "10@104Ar$";
		
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the id of the row to update:");
		int id = in.nextInt();
		Connection con = null;
		try {
			Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
			DriverManager.registerDriver(driver);
			con = DriverManager.getConnection(url, user, pass);
			
			//some instructions i follow from this website: https://alvinalexander.com/java/java-mysql-update-query-example/
			
			// create the java mysql update preparedstatement
			String sql = "update Hotels set hotel_name = ?, hotel_location = ?, created_date =?, updated_date =?, is_Active=? where id = ?";
			PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(sql);
			
			System.out.println("enter hotel name to be updated:");
			String hotelNamee=in.next();
			System.out.println("enter hotel location to be updated:");
			String hotelLocationn=in.next();
			
			pstmt.setString(1,hotelNamee);
			pstmt.setString(2,hotelLocationn);
			pstmt.setDate(3, new Date(System.currentTimeMillis()));
			pstmt.setDate(4, new Date(System.currentTimeMillis()));
			pstmt.setBoolean(5, true);
			pstmt.setInt(6, id);
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
				System.out.println("id:"+id + ", hotel name:" + hotelName + ", hotel location:" + hotelLocation + ", created date:" + createdDate + ", updated date:" + updatedDate+ ", isActive:" + isActive);
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
        	int id=in.nextInt();
        	
        	//below steps from this website:https://alvinalexander.com/java/java-mysql-delete-query-example/ 
            String sql = "delete from Hotels where id = ?";
            PreparedStatement preparedStmt = (PreparedStatement) con.prepareStatement(sql);
            preparedStmt.setInt(1, id);
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
     	 int id=in.nextInt();
     	 
     	 // similar way as update by id
        /* hotel name and hotel location i did not mention because i want to keep them same as previous value
          in database without change them.
         */
     	String sql = "update Hotels set created_date = ?, updated_date =?, is_Active =? where id = ?";
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
			System.out.println("id:"+id + ", hotel name:" + hotelName + ", hotel location:" + hotelLocation + ", created date:" + createdDate + ", updated date:" + updatedDate+ ", isActive:" + isActive);
		}
	} catch (Exception e) {
		System.out.println(e);
	}
}
      

	public static void insertIntoHotelsTable(){
		
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
	
	 // the way of creating table in database using java code
    public static boolean isHotelsTableCreated() {
		

		String url = "jdbc:mysql://localhost:3306/hoteldbms";
		 String user = "root";
	     String pass = "10@104Ar$"; 
			
		String hotelsTable = "CREATE TABLE Hotels (" 
		        + "id INT PRIMARY KEY,"  
		        + "hotel_name VARCHAR(50) NOT NULL," 
		        + "hotel_location VARCHAR(50),"  
		        + "created_date date NOT NULL,"
		        + "updated_date date,"
		        + "is_Active bit NOT NULL)"; 
		
	        Connection con = null;
	        
	        try {

	            Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
	            DriverManager.registerDriver(driver);
	            
	            con = DriverManager.getConnection(url, user,pass);
	            Statement st = con.createStatement();

	            int m = st.executeUpdate(hotelsTable);
	            if (m >=  1) {
	                System.out.println("table Hotels created successfully : " + hotelsTable);
	                return true;
	            }
	            else {
	                System.out.println("table Hotels not created, try again");
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
			isHotelsTableCreated();
			System.out.println("*******************************");
			break;
		}case 2:{
			insertIntoHotelsTable();
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
			readFromHotelsTable();
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
