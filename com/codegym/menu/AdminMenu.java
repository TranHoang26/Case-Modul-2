package com.codegym.menu;

import com.codegym.manager.HomeManager;
import com.codegym.manager.ProductManager;

import java.util.Scanner;

public class AdminMenu {
    ProductManager productManager = new ProductManager();
    HomeManager homeManager = new HomeManager();


    public AdminMenu() {
        this.productManager = productManager;
    }

    public void showAdminMenu() {
        while (true) {
            System.out.println("Chọn Chức Năng");
            System.out.println("1 : Danh Sách Sản Phẩm");
            System.out.println("2 : Tìm Kiếm Sản Phẩm ");
            System.out.println("3 : Sắp Xếp Sản Phẩm Giá Thấp-Cao ");
            System.out.println("4 : Sắp Xếp Sản Phẩm Giá Cao-Thấp  ");
            System.out.println("5 : Thêm Sản Phẩm ");
            System.out.println("6 : Xóa Sản Phẩm ");
            System.out.println("7 : Sửa Sản Phẩm");
            System.out.println("8 : Tài Khoản Khách Hàng");
            System.out.println("9 : Đăng Xuất");

            Scanner scanner = new Scanner(System.in);
            try {
                int idEdit = scanner.nextInt();
                switch (idEdit) {
                    case 1:
                        homeManager.showProduct();
                        break;
                    case 2:
                        homeManager.searchProduct();
                        break;
                    case 3:
                        homeManager.sortLowToHigh();
                        break;
                    case 4:
                        homeManager.sortHighToLow();
                        break;
                    case 5:
                        productManager.addProduct();
                        break;
                    case 6:
                        productManager.removeProduct();
                        break;
                    case 7:
                        productManager.updateProduct();
                        break;
                    case 8:
                        productManager.showUser();
                        break;
                    case 9:
                         homeManager.logOut();
                     default:
                        System.out.println("Không Có Chức Năng Này");
                }
            } catch (Exception e) {
                System.out.println("Không có chức năng này, vui lòng chọn lại!");
            }
        }
    }
}
