package entities;

public class Laptop {
    private int id ;
    private String brand;
    private String model;
    private double price;
    static int id_imp = 1;

    public Laptop(){
        this.id = id_imp++;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
    void ToString() {
        System.out.println(this.id + " " + this.brand+ " " + this.model + " " +this.price);
    }
}
