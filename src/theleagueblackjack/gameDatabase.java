/*
 Group: Edsel Rudy, Giancarlo Soriano, Jasmine Santos, Paprawin Boonyakida

 */
package theleagueblackjack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class gameDatabase {
    
    static final String DBNAME = "gamedata"; // Database name
    static final String URL = "jdbc:mysql://localhost/";
    static final String USER = "root";
    static final String PASSWORD = "password";
    static final String GAMEUSER = "blackJack";//
    static final String GAMEPASSWORD = "blackjackGame"; //
    static final String TABLENAME = "PlayerInfo"; // 
	
	// Important strings to be executed by a Statement
	static String createTable = "CREATE TABLE IF NOT EXISTS " + TABLENAME + "(p_id INT, name VARCHAR(25),r_win INT, r_lose INT, curr_points INT )";
	static String createDatabase = "CREATE DATABASE IF NOT EXISTS " + DBNAME;

	Connection connection = null;
		
    public void createUser(){
         Connection conn = null;
        Statement stmt = null;
        
        try {
            //Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            
            System.out.println("Creating user...");
            stmt = conn.createStatement();
            
            String userCreate = "CREATE USER '" + GAMEUSER +"'@'%' identified by '" + GAMEPASSWORD+ "';";
            stmt.executeUpdate(userCreate);
            
            String permissions = "grant select, insert, update, delete, create, create view, drop, execute, references on *.* to '"+GAMEUSER
                    +"'@'%' identified by '"+GAMEPASSWORD +"';"; 
           
            System.out.println(permissions);
            stmt.executeUpdate(permissions);
           
            permissions = "grant all privileges on *.* to '"+ GAMEUSER + "'@'%' identified by '" + GAMEPASSWORD + "';";
            stmt.executeUpdate(permissions);
             
            System.out.println("user " + GAMEUSER + " created.");
            
            
        }catch (Exception e) {
	        e.printStackTrace();
	    }finally{
	    	if(connection != null){
	    		try {
					connection.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
	    	}
	    }
    }
        
    public void createDB(){
                
	    try {
	    	// Get a connection
	        connection = DriverManager.getConnection(URL, GAMEUSER, GAMEPASSWORD);
	        System.out.println("Connected");
	
	        // Create a statement
	        Statement st = connection.createStatement();
	    
	        // Try to create a database called gamedata.
	        st.execute(createDatabase);
	        
	        // Use the database gamedata
	        st.execute("USE "+ DBNAME);
	        
	        // Create a table (if the table does not already exist)
	        st.execute(createTable);
	        
	        //Insert few sample data into the table
	        

	        String insertRow1 = "INSERT INTO " + TABLENAME +"(p_id,name,r_win,r_lose,curr_points) " 
	        		+ "VALUES(58880, 'Tammy', 40, 60, 19000)";
	       
	        st.executeUpdate(insertRow1);

	    } catch (Exception e) {
	        e.printStackTrace();
	    }finally{
	    	if(connection != null){
	    		try {
					connection.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
	    	}
	    }
    }
    
    
    
}
