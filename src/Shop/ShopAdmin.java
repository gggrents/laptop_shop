package Shop;

import Products.Menu;
import entities.Laptop;
import Connection.*;

import java.sql.Connection;

import java.sql.SQLException;
import java.util.Scanner;
public class ShopAdmin {
    private static final String login = "admin228";
    private static final String password = "qwerty";

    public static boolean adminAuthentication() {
        boolean b = false;
        Scanner s = new Scanner(System.in);
        System.out.println("Enter the login");
        String ent = s.next();
        System.out.println("Enter the password");
        String entp = s.next();
        if (ent.equals(login) && entp.equals(password)) {
            b = true;
        }
        return b;


    }

    public static void crudAdmin(ConnectionUtil s, Connection con) throws SQLException {
        int choice, chosenId;
        Laptop curLap = new Laptop();
        Scanner sc = new Scanner(System.in);
        while (true){
            Menu.showMenu();
            choice = sc.nextInt();
            if (choice == 1){
                curLap = Menu.createLaptop();
                s.insert_row(con, curLap.getBrand(), curLap.getModel(), curLap.getPrice());
            }
            else if (choice == 2){
                s.showLaptops(con, "laptops");
            }
            else if (choice == 3){
                s.showLaptops(con, "laptops");
                chosenId = Menu.updateLaptop();
                curLap = Menu.createLaptop();
                s.update(con, chosenId, curLap.getBrand(), curLap.getModel(), curLap.getPrice());
            }
            else if (choice == 4){
                s.showLaptops(con, "laptops");
                chosenId = Menu.deleteLaptop();
                s.delete(con, chosenId);
            }
            else if (choice == 5){
                System.out.println("----------------");
                break;
            }
            else {
                System.out.println("Write correct number");
            }
        }
    }
}
