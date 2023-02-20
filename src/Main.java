import Shop.ShopAdmin;
import Shop.shopForUser;
import Connection.*;
import entities.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        ConnectionUtil s = new ConnectionUtil();
        ConnectionUtilUsers r = new ConnectionUtilUsers();
        Connection con = s.connect_to_db("project", "postgres", "2222");
        Connection noc = r.connect_to_db("project", "postgres", "2222");
        s.createTable(con, "laptops");
        r.createTableUser(noc, "users");

        Scanner sc = new Scanner(System.in);
        int choice;
        while (true) {
            System.out.println("1.Running as an administrator");
            System.out.println("2.Running as an User");
            System.out.println("3. Exit");
            choice = sc.nextInt();
            if (choice == 1) {
                if (ShopAdmin.adminAuthentication()) {
                    System.out.println("Authentication was successful");
                    System.out.println("--------------");
                    ShopAdmin.crudAdmin(s, con);
                } else {
                    System.out.println("Login or password incorrect");
                }
            } else if (choice == 2) {
                while (true) {
                    System.out.println();
                    System.out.println("1. Sign up");
                    System.out.println("2. Log in");
                    System.out.println("3. Exit");
                    choice = sc.nextInt();
                    if (choice == 1) {
                        User.createUser(r, noc);
                    } else if (choice == 2) {
                        User user = new User();
                        user = User.enterUser(r, noc);
                        shopForUser.crudUser(s, r, noc, user);
                    } else if (choice == 3) {
                        System.out.println("-------------");
                        break;
                    }
                    else {
                        System.out.println("--------------");
                        System.out.println("Enter correct number");
                        System.out.println("--------------");
                    }
                }
            } else if (choice == 3) {
                System.out.println("-------------");
                break;
            } else {
                System.out.println("--------------");
                System.out.println("Enter correct number");
                System.out.println("--------------");
            }
        }

    }

}
