package com.codegym.manager;
import com.codegym.Product;
import com.codegym.Writer;
import com.codegym.User;
import com.codegym.interfaces.HomeActions;
import com.codegym.menu.AdminMenu;

import com.codegym.menu.HomeMenu;
import com.codegym.menu.UserMenu;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import static com.codegym.Reader.readListProduct;

public class HomeManager implements HomeActions {
    private List<Product> products;
    private User user;

    public HomeManager() {
        this.products = new ArrayList<>();
    }


    @Override
    public void logOut() {
        HomeMenu homeMenu = new HomeMenu();
        homeMenu.showHome();
    }


    @Override
    public void buyProduct() {
        List<Product> productList = readListProduct();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập tên sản phẩm hoặc ID để mua hàng.: ");
        String searchTerm = scanner.nextLine().trim();

        Product selectedProduct = null;

        for (Product product : productList) {
            if (String.valueOf(product.getId()).equals(searchTerm) || product.getName().equalsIgnoreCase(searchTerm)) {
                selectedProduct = product;
                break;
            }
        }
        if (selectedProduct == null) {
            System.out.println("Sản phẩm không tồn tại");
            return;
        }
        if (selectedProduct.getQuantity() == 0) {
            System.out.println("Sản phẩm này tạm thời đang hết!");
            return;
        }
        System.out.println("Sản Phẩm: " + selectedProduct);
        System.out.print("Nhập số lượng muốn mua ");
        int quantityToPurchase = scanner.nextInt();

        if (quantityToPurchase > selectedProduct.getQuantity()) {
            System.out.println("Không đủ hàng trong kho. Hàng hiện có : " + selectedProduct.getQuantity());
            return;
        }
        selectedProduct.setQuantity(selectedProduct.getQuantity() - quantityToPurchase);
        System.out.println("Mua hàng thành công! Số lượng còn lại: " + selectedProduct.getQuantity());
        Writer.writeListProduct(productList);
        UserMenu userMenu = new UserMenu();
        userMenu.showMenuUser();
    }

    @Override
    public void searchProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập tên sản phẩm hoặc ID để tìm kiếm: ");
        String searchTerm = scanner.nextLine();

        List<Product> productList = readListProduct();
        boolean found = false;
        try {
            int id = Integer.parseInt(searchTerm.trim());
            for (Product product : productList) {
                if (product.getId() == id) {
                    System.out.println("Sản phẩm đã được tìm thấy: " + product);
                    found = true;
                    break;
                }
            }
        } catch (NumberFormatException e) {
            for (Product product : productList) {
                if (product.getName().equalsIgnoreCase(searchTerm.trim())) {
                    System.out.println("Sản phẩm đã được tìm thấy: " + product);
                    found = true;
                    break;
                }
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy sản phẩm.");
        }
    }

    @Override
    public void showProduct() {
        List<Product> productList = readListProduct();
        if (productList.isEmpty()) {
            System.out.println("Không có sản phẩm nào trong danh sách.");
        } else {
            System.out.println("Danh sách sản phẩm:");
            for (Product product : productList) {
                System.out.println(product);
            }
        }
    }

    @Override
    public void logIn() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Log In:");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        if ("admin123".equals(username) && "admin123".equals(password)) {
            user = new User("Admin", 0, "Unknown", "Default Address", username, password);
            System.out.println("Đăng nhập thành công! Bạn đã đăng nhập với tư cách Quản trị viên.");
            AdminMenu adminMenu = new AdminMenu();
             adminMenu.showAdminMenu();
            return;
        }

        File file = new File("User.txt");
        try (Scanner fileScanner = new Scanner(file)) {
            boolean found = false;
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    String fileUsername = parts[4].trim();
                    String filePassword = parts[5].trim();
                    if (fileUsername.equals(username) && filePassword.equals(password)) {
                        user = new User(parts[0].trim(), Integer.parseInt(parts[1].trim()), parts[2].trim(), parts[3].trim(), fileUsername, filePassword);
                        System.out.println("Đăng Nhập Thành Công!");
                        UserMenu userMenu = new UserMenu();
                        userMenu.showMenuUser();
                        found = true;
                        break;
                    }
                } else {
                    System.err.println("Invalid record format in user file: " + line);
                }
            }
            if (!found) {
                System.out.println("Tên người dùng hoặc mật khẩu không hợp lệ.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("User not found.");
        }
    }

    @Override
    public void sortLowToHigh() {
        List<Product> products = readListProduct();
        products.sort(Comparator.comparingDouble(Product::getPrice));
        System.out.println("Danh sách sản phẩm theo giá từ Thấp-Cao:");
        for (Product product : products) {
            System.out.println(product);
        }
    }


    @Override
    public void sortHighToLow() {
        List<Product> products = readListProduct();
        products.sort((p1, p2) -> Double.compare(p2.getPrice(), p1.getPrice()));
        System.out.println("Danh sách sản phẩm theo giá từ Cao-Thấp:");
        for (Product product : products) {
            System.out.println(product);
        }
    }

    private boolean isValidUsername(String username) {
        // có ít nhất 6 ký tự và bao gồm cả chữ và số
        String usernameRegex = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]{6,}$";
        return Pattern.matches(usernameRegex, username);
    }

    private boolean isValidPassword(String password) {
        String passwordRegex = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]{6,}$";
        return Pattern.matches(passwordRegex, password);
    }


    @Override
    public void signUp() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Đăng ký tài khoản mới:");

         System.out.print("Nhập tên người dùng: ");
        String userName = scanner.nextLine();
        while (!isValidUsername(userName)) {
            System.out.println("Tên người dùng phải có ít nhất 6 ký tự bao gồm cả chữ và số.");
            System.out.print("Vui lòng nhập lại tên người dùng: ");
            userName = scanner.nextLine();
        }
        System.out.print("Nhập mật khẩu: ");
        String password = scanner.nextLine();
        while (!isValidPassword(password)) {
            System.out.println("Mật khẩu phải có ít nhất 6 ký tự bao gồm cả chữ và số.");
            System.out.print("Vui lòng nhập lại mật khẩu: ");
            password = scanner.nextLine();
        }
        System.out.print("Nhập tên đầy đủ: ");
        String name = scanner.nextLine();
        System.out.print("Nhập tuổi: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        if (age <= 15) {
            System.out.println("Bạn chưa đủ điều kiện tuổi yêu cầu.");
            return;
        }
        String gender = null;
        while (gender == null) {
            System.out.println("Chọn giới tính (1 - Nam, 2 - Nữ):");
            System.out.print("Nhập lựa chọn của bạn: ");
            int genderChoice = scanner.nextInt();
            scanner.nextLine();

            switch (genderChoice) {
                case 1:
                    gender = "Nam";
                    break;
                case 2:
                    gender = "Nữ";
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                    break;
            }
        }
        System.out.print("Nhập địa chỉ: ");
        String address = scanner.nextLine();
        User newUser = new User(name, age, gender, address, userName, password);
        Writer.writerListUser(newUser);
        System.out.println("Đăng ký thành công!");
    }
}
