/*
 Group: Edsel Rudy, Giancarlo Soriano, Jasmine Santos, Paprawin Boonyakida

 */
package resource;

import base.Player;
import base.PlayerType;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class gameDatabase {

    static final String DBNAME = "gamedata"; // Database name
    static final String URL = "jdbc:mysql://localhost:3306/";
    static final String USER = "root";
    static final String PASSWORD = "edsel123";
    static final String GAMEUSER = "blackJack";//
    static final String GAMEPASSWORD = "blackjackGame"; //
    static final String TABLENAME = "PlayerInfo"; // 

    // Important strings to be executed by a Statement
    static String createTable = "CREATE TABLE IF NOT EXISTS " + TABLENAME + "(p_id INT NOT NULL AUTO_INCREMENT, name VARCHAR(25),r_win INT, r_lose INT, curr_points FLOAT , playerType INT , PRIMARY KEY (p_id))";
    static String createDatabase = "CREATE DATABASE IF NOT EXISTS " + DBNAME;

    Connection connection = null;

    public void createUser() {
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

            String userCreate = "CREATE USER IF NOT EXISTS '" + GAMEUSER + "'@'%' identified by '" + GAMEPASSWORD + "';";
            stmt.executeUpdate(userCreate);

            String permissions = "grant select, insert, update, delete, create, create view, drop, execute, references on *.* to '" + GAMEUSER
                    + "'@'localhost' identified by '" + GAMEPASSWORD + "';";

            System.out.println(permissions);
            stmt.executeUpdate(permissions);

            permissions = "grant all privileges on *.* to '" + GAMEUSER + "'@'%' identified by '" + GAMEPASSWORD + "';";
            stmt.executeUpdate(permissions);

            System.out.println("user " + GAMEUSER + " created.");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void createDB() {

        try {
            // Get a connection
            connection = DriverManager.getConnection(URL, GAMEUSER, GAMEPASSWORD);
            System.out.println("Connected");

            // Create a statement
            Statement st = connection.createStatement();

            // Try to create a database called gamedata.
            st.execute(createDatabase);

            // Use the database gamedata
            st.execute("USE " + DBNAME);

            // Create a table (if the table does not already exist)
            st.execute(createTable);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void insertAndUpdatePlayers(List<Player> currentPlayers, List<Player> pastPlayers) {
        try {
            boolean isPresent = false;
            connection = DriverManager.getConnection(URL+DBNAME, GAMEUSER, GAMEPASSWORD);
            Statement st = connection.createStatement();
            for (int i = 0; i < currentPlayers.size(); i++) {
                for (int j = 0; j < pastPlayers.size(); j++) {
                    if (currentPlayers.get(i).getName().equals(pastPlayers.get(j).getName())) {
                        String insertRow = "UPDATE " + TABLENAME + " SET `r_win`='" + currentPlayers.get(i).getWins() + "', `r_lose`='" + currentPlayers.get(i).getLosses() + "', `curr_points`='" + currentPlayers.get(i).getFunds() + "'  WHERE name ='" + currentPlayers.get(i).getName() + "' ;";
                        
                        st.executeUpdate(insertRow);
                        isPresent = true;
                    }
                }
                if(!isPresent)
                {
                    String insertRow1 = "INSERT INTO " + TABLENAME + "(name,r_win,r_lose,curr_points ,playerType) "
                            + "VALUES('" + currentPlayers.get(i).getName()+ "', '" + currentPlayers.get(i).getWins()+ "', '" + currentPlayers.get(i).getLosses() + "', '" + currentPlayers.get(i).getFunds()+ "', '" + currentPlayers.get(i).getType().ordinal()+ "')";
                    
                    st.executeUpdate(insertRow1);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(gameDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public List<Player> getPastPlayers()
    {
        
        List<Player> list = new ArrayList<>();
        try {
            connection = DriverManager.getConnection(URL+DBNAME, GAMEUSER, GAMEPASSWORD);
            Statement st = connection.createStatement();
            ResultSet rs=st.executeQuery("SELECT * from  " + TABLENAME );
            while (rs.next()) {
                PlayerType type;
                if(rs.getInt("playerType") == 0)
                    type = PlayerType.HUMAN;
                else
                    type = PlayerType.COMPUTER;
                
                Player player = new Player(rs.getString("name"),rs.getInt("r_win"),rs.getInt("r_lose"),rs.getFloat("curr_points"),type);
                list.add(player);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(gameDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

}
