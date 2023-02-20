package entities;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import Connection.*;

public class User {
    private int id;
    private String username;
    private String password;
    private double balance;

    public static  int id_tem;


    public User() {
        this.id = id_tem++;

    }

    public int getId() {
        return id;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getBalance() {
        return balance;
    }

    public static void createUser(ConnectionUtilUsers r, Connection noc){
        Scanner sc = new Scanner(System.in);
        System.out.println("Write your username");
        String name = sc.next();
        System.out.println("Write your password");
        String pass = sc.next();
        System.out.println("Write your balance");
        Double bal = sc.nextDouble();
        r.add_user(noc, name, pass, bal);
    }

    public static User enterUser(ConnectionUtilUsers r, Connection noc) throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Write your username");
        String name = sc.next();
        System.out.println("Write your password");
        String pass = sc.next();
        User a = r.chekUser(noc, "users", name, pass);
        if (a == null){
            System.out.println("This user was not found");
            System.exit(0);
        }
        return a;
    }
}
