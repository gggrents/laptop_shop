package Products;

import entities.Laptop;

import java.util.Scanner;
public class Menu {

    public static void showMenu() {
        System.out.println("1.Add new laptop" );
        System.out.println("2.Show all laptops" );
        System.out.println("3.Update laptop" );
        System.out.println("4.Delete laptop" );
        System.out.println("5.Quit" );
    }

    public static void showMenuForUser(){
        System.out.println("1. Show all laptops" );
        System.out.println("2. Make a single purchase");
        System.out.println("3. Buy all from the basket");
        System.out.println("4. Collect a basket");
        System.out.println("5. Sorting");
        System.out.println("6. Show the basket");
        System.out.println("7. Delete laptop from the basket");
        System.out.println("8. Chek my balance");
        System.out.println("9. Quit" );
    }

    public static Laptop createLaptop() {
        int n;
        Scanner sc = new Scanner(System.in);
        String str;
        Double dbl;
        Laptop lap = new Laptop();

        System.out.println("Write the brand");
        str = sc.next();
        lap.setBrand(str);

        System.out.println("Write the model");
        str = sc.next();
        lap.setModel(str);

        System.out.println("Write the price");
        dbl = sc.nextDouble();
        lap.setPrice(dbl);
        return lap;
    }

    public static int updateLaptop(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Write the ID of the laptop you would like to change");
        int n = sc.nextInt();
        return n;
    }

    public static int deleteLaptop(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Write the ID of the laptop you would like to delete");
        int n = sc.nextInt();
        return n;
    }
}