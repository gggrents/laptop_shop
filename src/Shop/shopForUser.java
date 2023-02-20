package Shop;

import Connection.*;
import Products.Basket;
import Products.Menu;
import entities.*;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class shopForUser {
    public static void crudUser(ConnectionUtil s, ConnectionUtilUsers r, Connection con, User user) throws SQLException {
        int choice, chosenId;
        double sumOfPrices = 0;
        double price;
        double bal = user.getBalance();
        Scanner sc = new Scanner(System.in);
        while (true){
            Menu.showMenuForUser();
            choice = sc.nextInt();
            if (choice == 1){
                s.showLaptops(con, "laptops");
            }
            else if (choice == 2){
                s.showLaptops(con, "laptops");
                System.out.println("Choose laptop");
                chosenId = sc.nextInt();
                double currentPrice = ConnectionUtil.getPrice(con, chosenId);
                if (currentPrice == 0){
                    System.out.println("Enter correct id");
                    System.out.println("------------------");
                }
                else if (bal >= currentPrice) {
                    ConnectionUtil.delete(con, chosenId);
                } else {
                    System.out.println("Insufficient funds");
                }
            }
            else if (choice == 3){
                if (Basket.getBasket().size() == 0){
                    System.out.println("--------");
                    System.out.println("You did not add anything to the basket");
                    System.out.println("--------");
                    continue;
                }
                for (int i = 0; i < Basket.getBasket().size(); i++){
                    sumOfPrices += Basket.getBasket().get(i).getPrice();
                }
                if (sumOfPrices >= sumOfPrices){
                    bal = bal - sumOfPrices;
                    user.setBalance(bal);
                    ConnectionUtil.buyFromBasket(con, "laptops", Basket.getBasket());
                } else{
                    System.out.println("Insufficient funds");
                }

            } else if (choice == 4) {
                s.showLaptops(con, "laptops");
                System.out.println("Choose a laptop");
                chosenId = sc.nextInt();
                Basket.addLaptop(con, chosenId);
            } else if (choice == 5) {
                System.out.println("Choose sorting type");
                System.out.println("1.Sort by brand");
                System.out.println("2.Sort by price");
                choice = sc.nextInt();
                if (choice == 1) {
                    System.out.println("----------------");
                    System.out.println("Choose the brand");
                    String brand = sc.next();
                    ConnectionUtil.showSortedLaptopsByBrand(con, "laptops", brand);
                    System.out.println("----------------");
                }
                 else if (choice == 2) {
                    System.out.println("----------------");
                    System.out.println("Enter min price");
                    double min = sc.nextDouble();
                    System.out.println("Enter max price");
                    double max = sc.nextDouble();
                    ConnectionUtil.showSortedLaptopsByPrice(con, "laptops", min, max);
                    System.out.println("----------------");
                }
            } else if (choice == 6) {
                Basket.showBasket();
            } else if (choice == 7) {
                Basket.showBasket();
                System.out.println("Choose basket");
                chosenId = sc.nextInt();
                Basket.deleteLaptopFromBasket(chosenId);
            }
            else if (choice == 8){
                System.out.println("----------------");
                System.out.println(bal);
                System.out.println("----------------");
            }
            else if (choice == 9){
                System.out.println("----------------");
                break;
            }
            else {
                System.out.println("----------------");
                System.out.println("Write correct number");
                System.out.println("----------------");
            }
        }
    }
}
