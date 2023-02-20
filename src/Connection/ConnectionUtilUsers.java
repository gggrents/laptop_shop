package Connection;

import entities.User;

import java.sql.*;
import java.sql.SQLException;


public class ConnectionUtilUsers {
    public Connection connect_to_db(String dbname, String user, String pass) {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbname, user, pass);
            if (conn != null) {
                System.out.println("Connection Established");
            } else {
                System.out.println("Connection Failed");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return conn;
    }

    public void createTableUser(Connection conn, String recovery) {
        Statement statement;
        try {
            String query = "create table if not exists " + recovery + " (id SERIAL PRIMARY KEY NOT NULL, username varchar(50), password varchar(50), balance float);";
            statement = conn.createStatement();
            statement.executeUpdate(query);
        } catch (Exception e) {
            System.out.println("entering db information");
            System.out.println("please enter your social security number **1 to exit program**");
        }
    }


    public void add_user(Connection conn, String username, String password, double balance) {
        Statement statement;
        try {
            String query = "INSERT INTO users (username, password, balance) VALUES ('" + username + "', '" + password + "', " + balance +")";
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("user added");
            System.out.println("--------------");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public User chekUser (Connection conn, String recovery, String username, String password) throws SQLException{
        Statement statement;
        User t = new User();
        try {
            String query = "SELECT * FROM " + recovery + ";";
            statement = conn.createStatement();

            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                int id = result.getInt("id");
                String userName = result.getString("username");
                String pass = result.getString("password");
                double bal = result.getFloat("balance");
                if (userName.equals(username) && pass.equals(password)){
                    t.setUsername(userName);
                    t.setPassword(pass);
                    t.setBalance(bal);
                    return t;
                }
            }
        } catch (Exception e){
            System.out.println(e);
            System.out.println("-------------");
        }
        return t;
    }
}