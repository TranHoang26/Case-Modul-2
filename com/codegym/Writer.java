package com.codegym;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class Writer {
    public Writer() {
    }

    public static void writeListProduct(List<Product> list) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("Product.txt"));
            Throwable var2 = null;

            try {
                Iterator var3 = list.iterator();

                while(var3.hasNext()) {
                    Product product = (Product)var3.next();
                    bufferedWriter.write(product.getId() + "," + product.getQuantity() + "," + product.getName() + "," + product.getPrice() + "," + product.getColor());
                    bufferedWriter.newLine();
                }
            } catch (Throwable var13) {
                var2 = var13;
                throw var13;
            } finally {
                if (bufferedWriter != null) {
                    if (var2 != null) {
                        try {
                            bufferedWriter.close();
                        } catch (Throwable var12) {
                            var2.addSuppressed(var12);
                        }
                    } else {
                        bufferedWriter.close();
                    }
                }

            }
        } catch (IOException var15) {
            IOException e = var15;
            e.printStackTrace();
        }

    }

    public static void writerListUser(User user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("User.txt", true))) {
             writer.write(user.getName() + "," +
                    user.getAge() + "," +
                    user.getGender() + "," +
                    user.getAddress() + "," +
                    user.getUserName() + "," +
                    user.getPassword());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Đã xảy ra lỗi khi ghi vào tệp: " + e.getMessage());
        }
    }

}
