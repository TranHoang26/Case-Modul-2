package com.codegym.menu;

import com.codegym.manager.HomeManager;

import java.util.Scanner;

public class HomeMenu {
    HomeManager homeManager = new HomeManager();


    public void showHome() {
        while (true) {
            System.out.println("----- Home -----");
            System.out.println("1 : Danh Sách Sản Phẩm");
            System.out.println("2 : Tìm Kiếm Sản Phẩm ");
            System.out.println("3 : Sắp Xếp Sản Phẩm Theo Giá Thấp-Cao");
            System.out.println("4 : Sắp Xếp Sản Phẩm Theo Giá Cao-Thấp");
            System.out.println("5 : Mua Hàng");
            System.out.println("6 : Đăng Nhập");
            System.out.println("7 : Đăng Ký Tài Khoản");
            System.out.println("0 : Thoát Chương Trình");

            Scanner scanner = new Scanner(System.in);
            try {
                int idEdit = scanner.nextInt();
                switch (idEdit) {
                    case 0:
                        return;
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
                    case 5, 6:
                        homeManager.logIn();
                        break;
                    case 7:
                        homeManager.signUp();
                        break;
                    default:
                        System.out.println("Không Có Chức Năng Này");
                }
            } catch (Exception e) {
                System.out.println("Không có chức năng này, vui lòng chọn lại!");
            }
        }
    }
}
