package com.codegym;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Reader {
    public Reader() {
    }

    public static List<Product> readListProduct() {
        List<Product> list = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("Product.txt"))) {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    try {
                        int id = Integer.parseInt(parts[0].trim());
                        int quantity = Integer.parseInt(parts[1].trim());
                        String name = parts[2].trim();
                        double price = Double.parseDouble(parts[3].trim());
                        String color = parts[4].trim();

                        list.add(new Product(quantity, name, id, price, color));
                    } catch (NumberFormatException e) {
                        System.err.println("Lỗi chuyển đổi dữ liệu trong dòng: " + line);
                    }
                } else {
                    System.err.println("Dòng không đúng định dạng: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }


    public static List<User> readListUser() {
        List<User> userList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("User.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts.length == 6) {
                    String name = parts[0].trim();
                    int age = Integer.parseInt(parts[1].trim());
                    String gender = parts[2].trim();
                    String address = parts[3].trim();
                    String userName = parts[4].trim();
                    String password = parts[5].trim();

                    User user = new User(name, age, gender, address, userName, password);
                    userList.add(user);
                } else {
                    System.err.println("Invalid record format: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading user file: " + e.getMessage());
        }

        return userList;
    }

}
