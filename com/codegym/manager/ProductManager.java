package com.codegym.manager;

import com.codegym.Product;
import com.codegym.User;
import com.codegym.Writer;
import com.codegym.Reader;
import com.codegym.interfaces.AdminActions;
import com.codegym.menu.AdminMenu;
import com.codegym.menu.HomeMenu;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductManager implements AdminActions {
    private List<Product> products;

    public ProductManager() {
        products = new ArrayList<>();
        products = Reader.readListProduct();
    }

    @Override
    public void addProduct() {
        products = Reader.readListProduct();
        Scanner scanner = new Scanner(System.in);
        int maxId = 0;
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() > maxId) {
                maxId = products.get(i).getId();
            }
        }
        int newId = maxId + 1;
        System.out.println("Nhập số lượng mới cho sản phẩm: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Nhập tên mới cho sản phẩm: ");
        String name = scanner.nextLine();
        System.out.println("Nhập giá mới cho sản phẩm: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Nhập màu sắc mới cho sản phẩm: ");
        String color = scanner.nextLine();

        Product product = new Product(quantity, name, newId, price, color);
        products.add(product);
        Writer.writeListProduct(products);
        System.out.println("Thêm sản phẩm mới thành công với ID: " + newId);
    }

    @Override
    public void removeProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập ID hoặc tên sản phẩm cần xóa:");
        String keyword = scanner.nextLine();

        Product productToRemove = null;
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(keyword) || String.valueOf(product.getId()).equals(keyword)) {
                productToRemove = product;
                break;
            }
        } if (productToRemove != null) {
            System.out.println("Bạn có chắc chắn muốn xóa sản phẩm này không? (YES/NO):");
            String confirmation = scanner.nextLine();
            if (confirmation.equalsIgnoreCase("YES")) {
                products.remove(productToRemove);
                Writer.writeListProduct(products);
                System.out.println("Xóa sản phẩm thành công!");
            } else {
                System.out.println("Hủy bỏ thao tác xóa sản phẩm.");
            }
        } else {
            System.out.println("Sản phẩm không tồn tại!");
        }
    }


    @Override
    public void updateProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập ID của sản phẩm cần sửa: ");
        String keyword = scanner.nextLine();

        Product productToUpdate = null;
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(keyword) || String.valueOf(product.getId()).equals(keyword)) {
                productToUpdate = product;
                break;
            }
        }
        if (productToUpdate != null) {
            System.out.println("Nhập số lượng mới cho sản phẩm : ");
            int newQuantity = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Nhập tên mới cho sản phẩm: ");
            String newName = scanner.nextLine();
            System.out.println("Nhập giá mới cho sản phẩm: ");
            double newPrice = scanner.nextDouble();
            scanner.nextLine();
            System.out.println("Nhập màu sắc mới cho sản phẩm: ");
            String newColor = scanner.nextLine();

            productToUpdate.setQuantity(newQuantity);
            productToUpdate.setName(newName);
            productToUpdate.setPrice(newPrice);
            productToUpdate.setColor(newColor);

            Writer.writeListProduct(products);
            System.out.println("Cập nhật sản phẩm thành công!");
        } else {
            System.out.println("Sản pẩm không tồn tại");
        }
    }

    @Override
    public Product findProductById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                System.out.println("Id: " + product.getId() +
                                   " Name: " + product.getName() +
                                   " Price: " + product.getPrice() +
                                   " Color: " + product.getColor());
                return product;
            }
        }
        return null;
    }

    @Override
    public void showUser() {
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
                    String username = parts[4].trim();
                    String password = parts[5].trim();

                    User user = new User(name, age, gender, address, username, password);
                    userList.add(user);
                } else {
                    System.err.println("Invalid record format in user file: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Đã xảy ra lỗi khi đọc tệp: " + e.getMessage());
        }

        if (userList.isEmpty()) {
            System.out.println("Danh sách người dùng trống.");
        } else {
            System.out.println("Danh sách người dùng:");
            for (User user : userList) {
                System.out.println("Tên: " + user.getName() +
                        ", Tuổi: " + user.getAge() +
                        ", Giới Tính: " + user.getGender() +
                        ", Địa Chỉ: " + user.getAddress());
            }
        }
    }

}
