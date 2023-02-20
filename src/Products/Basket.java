package Products;

import Connection.ConnectionUtil;
import entities.Laptop;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class Basket {
    private static ArrayList<Laptop> basket = new ArrayList<>();


    public static ArrayList<Laptop> getBasket() {
        return basket;
    }

    public static void addLaptop(Connection con, int id) throws SQLException {
        basket.add(ConnectionUtil.addLaptopToBasket(con, "laptopsi" ,id));
    }

    public static void showBasket () {
        for (int i = 0; i < basket.size(); i++){
            System.out.println(basket.get(i).getId() + " " + basket.get(i).getBrand() + " " + basket.get(i).getModel() + " " + basket.get(i).getPrice());
        }
    }

    public static void deleteLaptopFromBasket (int id) {
        for (int i = 0; i < basket.size(); i++){
            if (basket.get(i).getId() == id){
                basket.remove(i);
            }
        }
    }
}
