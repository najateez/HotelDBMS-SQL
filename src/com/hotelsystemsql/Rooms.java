package com.hotelsystemsql;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Statement;

public class Rooms {
	
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

public void insertIntoTable(){

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
		
		isRoomsTableCreated();

	}

}
