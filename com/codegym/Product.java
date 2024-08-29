package com.codegym;

public class Product {
    private int quantity;
    private String name;
    private int id;
    private double price;
    private String color;

    public Product(String quantity, double price) {
    }

    public Product(int quantity, String name, int id, double price, String color) {
        this.quantity = quantity;
        this.name = name;
        this.id = id;
        this.price = price;
        this.color = color;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String toString() {
        return "Tên sản phẩm:" + this.name + ", Id:" + this.id + ", Giá:" + this.price + ", Màu sắc:" + this.color + ", Số lượng:" + this.quantity;
    }
}
