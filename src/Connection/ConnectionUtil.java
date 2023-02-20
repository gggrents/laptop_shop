package Connection;

import entities.Laptop;

import java.sql.*;
import java.util.ArrayList;


public class ConnectionUtil {
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

    public void createTable(Connection conn, String recovery) {
        Statement statement;
        try {
            String query = "create table if not exists " + recovery + " (id SERIAL PRIMARY KEY NOT NULL, brand varchar(50), model varchar(50), price float);";
            statement = conn.createStatement();
            statement.executeUpdate(query);
        } catch (Exception e) {
            System.out.println("entering db information");
            System.out.println("please enter your social security number **1 to exit program**");
            System.out.println("--------------");
        }
    }

    public void insert_row(Connection conn, String brand, String model, double price) {
        Statement statement;
        try {
            String query = "INSERT INTO laptops (brand, model, price) VALUES ('" + brand + "', '" + model + "', " + price +")";
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Laptop was added");
            System.out.println("----------------");
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("--------------");
        }
    }

    public void showLaptops(Connection conn, String recovery) throws SQLException {
        Statement statement;
        try {
            String query = "SELECT * FROM " + recovery + ";";
            statement = conn.createStatement();

            ResultSet result = statement.executeQuery(query);
            System.out.println("--------------");
            while (result.next()) {
                int id = result.getInt("id");
                String brandName = result.getString("brand");
                String modelName = result.getString("model");
                double pprice = result.getFloat("price");

                System.out.println(id + " " + brandName + " " + modelName + " " + pprice);
            }
            System.out.println("--------------");
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("--------------");
        }
    }

    public void update(Connection conn, int idm, String brand, String model, double price) {
        Statement statement;
        try {
            String query = "UPDATE laptops SET brand = '" + brand + "', model = '" + model + "', price = " + price + " WHERE id=" + idm + ";";
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("laptop with id " +idm+" updated" + "\n");
            System.out.println("--------------");
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("--------------");
        }
    }

    public static void delete(Connection conn, int idm) {
        Statement statement;
        try {
            String query = "DELETE FROM laptops WHERE id = " + idm + ";";
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("laptop with id " +idm+" deleted" + "\n");
            System.out.println("--------------");
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("--------------");
        }

    }

    public static void showSortedLaptopsByBrand(Connection conn, String recovery, String brand) throws SQLException {
        Statement statement;
        try {
            String query = "SELECT * FROM " + recovery + " WHERE brand = '" + brand + "';";
            statement = conn.createStatement();

            ResultSet result = statement.executeQuery(query);
            System.out.println("--------------");
            while (result.next()) {
                int id = result.getInt("id");
                String brandName = result.getString("brand");
                String modelName = result.getString("model");
                double pprice = result.getFloat("price");

                System.out.println(id + " " + brandName + " " + modelName + " " + pprice);

            }
            System.out.println("--------------");
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("--------------");
        }
    }

    public static void showSortedLaptopsByPrice(Connection conn, String recovery, double min, double max) throws SQLException {
        Statement statement;
        try {
            String query = "SELECT * FROM " + recovery + " WHERE price >= " + min + " AND " + "price <= " + max + ";";
            statement = conn.createStatement();

            ResultSet result = statement.executeQuery(query);
            System.out.println("--------------");
            while (result.next()) {
                int id = result.getInt("id");
                String brandName = result.getString("brand");
                String modelName = result.getString("model");
                double pprice = result.getFloat("price");

                System.out.println(id + " " + brandName + " " + modelName + " " + pprice);
            }
            System.out.println("--------------");
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("--------------");
        }
    }

    public static Laptop addLaptopToBasket(Connection conn, String recovery, int curid) throws SQLException {
        Statement statement;
        Laptop p = new Laptop();
        try {
            String query = "SELECT * FROM " + recovery + ";";
            statement = conn.createStatement();

            ResultSet result = statement.executeQuery(query);
            System.out.println("--------------");
            while (result.next()) {
                int id = result.getInt("id");
                String brandName = result.getString("brand");
                String modelName = result.getString("model");
                double pprice = result.getFloat("price");
                if (id == curid){
                    p.setId(id);
                    p.setBrand(brandName);
                    p.setModel(modelName);
                    p.setPrice(pprice);
                }
            }
            System.out.println("--------------");
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("--------------");
        }
        return p;
    }

    public static void buyFromBasket(Connection conn, String recovery, ArrayList<Laptop> basket) throws SQLException {
        Statement statement;
        try {
            for (int i = 0; i < basket.size(); i++){
                String w = "DELETE FROM laptops WHERE id = " + basket.get(i).getId() + ";";
                statement = conn.createStatement();
                statement.executeUpdate(w);
            }
            System.out.println("--------------");
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("--------------");
        }
    }

    public static double getPrice(Connection conn, int cid) throws SQLException {
        Statement statement;
        double pprice = 0;
        try {
            String query = "SELECT * FROM laptops;";
            statement = conn.createStatement();

            ResultSet result = statement.executeQuery(query);
            System.out.println("--------------");
            while (result.next()) {
                int id = result.getInt("id");
                String brandName = result.getString("brand");
                String modelName = result.getString("model");
                pprice = result.getFloat("price");
                if (id == cid) {
                    return pprice;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("--------------");
        }
        return pprice;
    }
}